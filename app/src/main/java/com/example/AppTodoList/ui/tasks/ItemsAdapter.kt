package com.example.AppTodoList.ui.tasks

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.AppTodoList.data.entities.Task
import com.example.AppTodoList.databinding.ItemTaskFragmentBinding

class ItemsAdapter(private val listener: ItemListener) : RecyclerView.Adapter<CharacterViewHolder>() {

    interface ItemListener {
        fun editItemOnCLick(characterId: String)
    }

    private val items = ArrayList<Task>()

    fun setItems(tasks: ArrayList<Task>) {
        this.items.clear()
        this.items.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding: ItemTaskFragmentBinding = ItemTaskFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) = holder.bind(items[position])
}

class CharacterViewHolder(private val itemBinding: ItemTaskFragmentBinding, private val listener: ItemsAdapter.ItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var task: Task

    init {
        itemBinding.editButton.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(task: Task) {
        this.task = task
        itemBinding.title.text = task.title
        itemBinding.description.text = task.description
    }

    override fun onClick(v: View?) {
        listener.editItemOnCLick(task.id)
    }
}

