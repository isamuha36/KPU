package com.isamuha.kpu

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.viewModelFactory
import com.isamuha.kpu.database.Voter
import com.isamuha.kpu.database.VoterViewModel
import com.isamuha.kpu.databinding.ActivityCreateBinding

class CreateActivity: AppCompatActivity() {
    private lateinit var binding: ActivityCreateBinding
    private val voterViewModel: VoterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSimpan.setOnClickListener {
            val name = binding.inputNamaPemilih.text.toString().trim()
            val nik = binding.inputNik.text.toString().trim()
            val gender = if (binding.rbMale.isChecked) "Laki Laki" else "Perempuan"
            val address = binding.inputAlamat.text.toString().trim()

            if (name.isNotEmpty() && nik.isNotEmpty() && address.isNotEmpty()) {
                val voter = Voter(name = name, nik = nik, gender = gender, address = address)
                voterViewModel.insert(voter)
                Toast.makeText(this, "Voter added successfully!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}