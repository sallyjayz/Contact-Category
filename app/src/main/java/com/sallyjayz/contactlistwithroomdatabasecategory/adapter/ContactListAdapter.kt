package com.sallyjayz.contactlistwithroomdatabasecategory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sallyjayz.contactlistwithroomdatabasecategory.R
import com.sallyjayz.contactlistwithroomdatabasecategory.model.ContactDetail

class ContactListAdapter : ListAdapter<ContactDetail, ContactListAdapter.ContactViewHolder>(ContactsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameItemView: TextView = itemView.findViewById(R.id.nameTv)
        private val numberItemView: TextView = itemView.findViewById(R.id.numberTv)
        private val categoryItemView: TextView = itemView.findViewById(R.id.categoryTv)

        fun bind(contact: ContactDetail?) {
            nameItemView.text = contact?.name
            numberItemView.text = contact?.number
            categoryItemView.text = contact?.category
        }

        companion object {
            fun create(parent: ViewGroup): ContactViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.contact_item, parent, false)
                return ContactViewHolder(view)
            }
        }
    }

    class ContactsComparator : DiffUtil.ItemCallback<ContactDetail>() {
        override fun areItemsTheSame(oldItem: ContactDetail, newItem: ContactDetail): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ContactDetail, newItem: ContactDetail): Boolean {
            return oldItem.number == newItem.number
        }
    }
}