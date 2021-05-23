package com.sallyjayz.contactlistwithroomdatabasecategory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sallyjayz.contactlistwithroomdatabasecategory.R
import com.sallyjayz.contactlistwithroomdatabasecategory.model.ContactCategory

class CategoryAdapter(private val itemClickListener: OnItemClickListener ): ListAdapter<ContactCategory, CategoryAdapter.CategoryViewHolder>(CategoryComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
    return CategoryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, itemClickListener)
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var categoryItemView: TextView = itemView.findViewById(R.id.nameTv)

        fun bind(category: ContactCategory?, clickListener: OnItemClickListener) {
            categoryItemView.text = category?.categoryName
            itemView.setOnClickListener {
                clickListener.onItemClicked(category)
            }
        }

        companion object {
            fun create(parent: ViewGroup): CategoryViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.category_item, parent, false)
                return CategoryViewHolder(view)
            }
        }

    }

    class CategoryComparator : DiffUtil.ItemCallback<ContactCategory>() {
        override fun areItemsTheSame(oldItem: ContactCategory, newItem: ContactCategory): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ContactCategory, newItem: ContactCategory): Boolean {
            return oldItem.categoryName == newItem.categoryName
        }
    }


}

interface OnItemClickListener{
    fun onItemClicked(category: ContactCategory?)
}