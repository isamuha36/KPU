package com.isamuha.kpu

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.isamuha.kpu.database.VoterViewModel
import com.isamuha.kpu.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val voterViewModel: VoterViewModel by viewModels()
    private lateinit var voterAdapter: VoterAdapter
    private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menggunakan ViewBinding untuk mengakses layout
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi PrefManager - Inisialisasi ini harus dilakukan sebelum digunakan
        prefManager = PrefManager(this)

        // Setup RecyclerView
        voterAdapter = VoterAdapter { voter, action ->
            when (action) {
                VoterAdapter.ActionType.EDIT -> {
                    val intent = Intent(this, EditActivity::class.java)
                    intent.putExtra("voterId", voter.id)
                    startActivity(intent)
                }
                VoterAdapter.ActionType.DELETE -> {
                    voterViewModel.delete(voter)
                }
                VoterAdapter.ActionType.VIEW -> {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("voterId", voter.id)
                    startActivity(intent)
                }
            }
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = voterAdapter
        }

        // Observasi data dari ViewModel
        voterViewModel.allVoters.observe(this) { voters ->
            voters?.let {
                voterAdapter.setVoters(it)
            }
        }

        // Tombol Tambah Data
        binding.addDataButton.setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }

        // Tombol Logout
        binding.logoutButton.setOnClickListener {
            // Memastikan prefManager telah diinisialisasi sebelum digunakan
            prefManager.logout()

            // Navigasi ke LoginActivity setelah logout
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}