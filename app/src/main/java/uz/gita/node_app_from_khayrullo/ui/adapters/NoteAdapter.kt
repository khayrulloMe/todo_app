package uz.gita.node_app_from_khayrullo.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.node_app_from_khayrullo.R
import uz.gita.node_app_from_khayrullo.data.room.entity.NoteEntity

import uz.gita.node_app_from_khayrullo.databinding.ListItemBinding
import uz.gita.node_app_from_khayrullo.utils.DateChange

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private val dataNotes = ArrayList<NoteEntity>()
    private lateinit var listener: (NoteEntity) -> Unit

    fun setOnClicked(listener: (NoteEntity) -> Unit) {
        this.listener = listener
    }

    fun setData(items: List<NoteEntity>) {
        dataNotes.clear()
        dataNotes.addAll(items)
        notifyDataSetChanged()
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ListItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val dateChange = DateChange()
        val item = dataNotes[position]
        holder.apply {
            binding.textViewTitle.text = item.title
            binding.tvDate.text = dateChange.changeFormatDate(item.date)
            binding.textViewBody.text = item.body
            itemView.setOnClickListener {
                listener.invoke(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataNotes.size
    }
}