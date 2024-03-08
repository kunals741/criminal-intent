package com.example.criminalintent

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.criminalintent.databinding.ListItemCrimeBinding
import com.example.criminalintent.databinding.ListItemCrimeWithPoliceBinding
import com.example.criminalintent.models.Crime
import java.util.UUID

class CrimeListAdapter(private val crimes: List<Crime>, private val onCrimeClicked: (crimeId : UUID) -> Unit) :
    RecyclerView.Adapter<CrimeListAdapter.CrimeHolder>() {

    class CrimeHolder(
        private val binding: ListItemCrimeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(crime: Crime,
                 onCrimeClicked : (crimeID : UUID) -> Unit
                 ) {
            binding.crimeTitle.text = crime.title
            binding.crimeDate.text = crime.date.toString()
            binding.root.setOnClickListener {
                onCrimeClicked(crime.id)
            }
            binding.crimeSolved.isVisible = crime.isSolved
        }
    }

    class CrimeHolderWithPolice(
        private val binding: ListItemCrimeWithPoliceBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(crime: Crime) {
            binding.crimeTitle.text = crime.title
            binding.crimeDate.text = crime.date.toString()
            binding.root.setOnClickListener {
                Toast.makeText(
                    binding.root.context,
                    "${crime.title} clicked!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
//
//    override fun getItemViewType(position: Int): Int {
//        if (crimes[position].requiresPolice) {
//            return 1
//        }
//        return 0
//    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
        val inflater = LayoutInflater.from(parent.context)
//        return if (viewType == 0) {
//            val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
//            CrimeHolder(binding)
//        } else {
//            val binding = ListItemCrimeWithPoliceBinding.inflate(inflater, parent, false)
//            CrimeHolderWithPolice(binding)
//        }
        val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
        return CrimeHolder(binding)

    }

    override fun getItemCount() = crimes.size

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        val crime = crimes[position]
//        when (holder.itemViewType) {
//            0 -> (holder as CrimeHolder).bind(crime)
//            1 -> (holder as CrimeHolderWithPolice).bind(crime)
//        }
        holder.bind(crime, onCrimeClicked)

    }
}