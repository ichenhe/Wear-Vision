package me.chenhe.wearvisiondemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class SimpleAdapter(
    private val onItemClickListener: ((position: Int) -> Unit)
) : ListAdapter<String, VH>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater.from(parent.context).inflate(R.layout.item_main_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.setText(getItem(position))
        holder.textView.setOnClickListener {
            onItemClickListener.invoke(holder.absoluteAdapterPosition)
        }
    }
}

class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView: TextView = itemView.findViewById(R.id.itemText)

    fun setText(text: CharSequence) {
        textView.text = text
    }
}

class DiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem
}