package com.isamuha.kpu

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.isamuha.kpu.database.VoterViewModel
import com.isamuha.kpu.databinding.ActivityDetailBinding
import com.isamuha.kpu.databinding.ActivityEditBinding

class DetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val voterViewModel: VoterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val voterId = intent.getIntExtra("voterId", -1)
        if (voterId == -1) {
            finish()
            return
        }

        // Observasi data voter untuk ditampilkan
        voterViewModel.allVoters.observe(this) { voters ->
            val voter = voters.find { it.id == voterId }
            if (voter != null) {
                binding.labelNama.text = "Nama: ${voter.name}"
                binding.labelNik.text = "NIK: ${voter.nik}"
                binding.labelGender.text = "Gender: ${voter.gender}"
                binding.labelAlamat.text = "Alamat: ${voter.address}"
            }
        }

        // Tombol Kembali
        binding.buttonKembali.setOnClickListener {
            finish()
        }
    }
}