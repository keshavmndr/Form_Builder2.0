package com.example.kotlintodopractice.utils.adapter

import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintodopractice.databinding.EachOtherItemBinding
import com.example.kotlintodopractice.utils.model.ToDoData

class TaskAdapter(private val list: MutableList<ToDoData>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private  val TAG = "TaskAdapter"
    private var listener:TaskAdapterInterface? = null
    fun setListener(listener:TaskAdapterInterface){
        this.listener = listener
    }
    class TaskViewHolder(val binding: EachOtherItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding =
            EachOtherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.todoTask.text = this.task.split("/delimiter")[0]

                binding.answer.visibility = View.VISIBLE
                if (this.task.split("/delimiter")[1].isNotEmpty()){
                    binding.answer.hint = "Answer Hint: " + this.task.split("/delimiter")[1]
                } else binding.answer.hint = "No Answer Provided"

                if (this.task.split("/delimiter")[2].isNotEmpty()){
                    binding.mcq1.visibility = View.VISIBLE
                    binding.mcq1.text = this.task.split("/delimiter")[2]
                    binding.answer.visibility = View.GONE
                } else binding.mcq1.visibility = View.GONE

                if (this.task.split("/delimiter")[3].isNotEmpty()){
                    binding.mcq2.visibility = View.VISIBLE
                    binding.mcq2.text = this.task.split("/delimiter")[3]
                    binding.answer.visibility = View.GONE
                } else binding.mcq2.visibility = View.GONE

                if (this.task.split("/delimiter")[4].isNotEmpty()){
                    binding.mcq3.visibility = View.VISIBLE
                    binding.mcq3.text = this.task.split("/delimiter")[4]
                    binding.answer.visibility = View.GONE
                } else binding.mcq3.visibility = View.GONE

                if (this.task.split("/delimiter")[5].isNotEmpty()){
                    binding.mcq4.visibility = View.VISIBLE
                    binding.mcq4.text = this.task.split("/delimiter")[5]
                    binding.answer.visibility = View.GONE
                } else binding.mcq4.visibility = View.GONE

                Log.d(TAG, "onBindViewHolder: "+this)
                binding.editTask.setOnClickListener {
                    listener?.onEditItemClicked(this , position)
                }

                binding.deleteTask.setOnClickListener {
                    listener?.onDeleteItemClicked(this , position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface TaskAdapterInterface{
        fun onDeleteItemClicked(toDoData: ToDoData , position : Int)
        fun onEditItemClicked(toDoData: ToDoData , position: Int)
    }

}