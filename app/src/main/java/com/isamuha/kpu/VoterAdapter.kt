package com.isamuha.kpu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.isamuha.kpu.database.Voter
import com.isamuha.kpu.databinding.ItemVoterBinding

class VoterAdapter(private val onItemClicked: (Voter, ActionType) -> Unit) :
    RecyclerView.Adapter<VoterAdapter.VoterViewHolder>() {

    private var votersList = emptyList<Voter>()

    inner class VoterViewHolder(val binding: ItemVoterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoterViewHolder {
        val binding = ItemVoterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VoterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VoterViewHolder, position: Int) {
        val voter = votersList[position]
        with(holder.binding) {
            tvNamaPemilih.text = voter.name
            btnEdit.setOnClickListener { onItemClicked(voter, ActionType.EDIT) }
            btnDelete.setOnClickListener { onItemClicked(voter, ActionType.DELETE) }
            btnView.setOnClickListener { onItemClicked(voter, ActionType.VIEW) }
        }
    }

    override fun getItemCount() = votersList.size

    fun setVoters(voters: List<Voter>) {
        votersList = voters
        notifyDataSetChanged()
    }

    enum class ActionType {
        EDIT, DELETE, VIEW
    }
}