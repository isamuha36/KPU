package com.isamuha.kpu

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.isamuha.kpu.database.Voter
import com.isamuha.kpu.database.VoterViewModel
import com.isamuha.kpu.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding
    private val voterViewModel: VoterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val voterId = intent.getIntExtra("voterId", -1)
        if (voterId == -1) {
            Toast.makeText(this, "Invalid Voter ID", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Load data pemilih dari database untuk diedit
        voterViewModel.allVoters.observe(this) { voters ->
            val voter = voters.find { it.id == voterId }
            if (voter != null) {
                binding.inputNamaPemilih.setText(voter.name)
                binding.inputNik.setText(voter.nik)
                if (voter.gender == "Laki Laki") {
                    binding.rbMale.isChecked = true
                } else {
                    binding.rbFemale.isChecked = true
                }
                binding.inputAlamat.setText(voter.address)
            }
        }

        binding.buttonSimpan.setOnClickListener {
            val name = binding.inputNamaPemilih.text.toString().trim()
            val nik = binding.inputNik.text.toString().trim()
            val gender = if (binding.rbMale.isChecked) "Laki Laki" else "Perempuan"
            val address = binding.inputAlamat.text.toString().trim()

            if (name.isNotEmpty() && nik.isNotEmpty() && address.isNotEmpty()) {
                val voter = Voter(id = voterId, name = name, nik = nik, gender = gender, address = address)
                voterViewModel.update(voter)
                Toast.makeText(this, "Voter updated successfully!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}