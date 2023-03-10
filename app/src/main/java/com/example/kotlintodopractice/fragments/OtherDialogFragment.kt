package com.example.kotlintodopractice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.kotlintodopractice.databinding.FragmentOtherDialogBinding
import com.example.kotlintodopractice.utils.model.ToDoData
import com.google.android.material.textfield.TextInputEditText


class OtherDialogFragment : DialogFragment() {

    private lateinit var binding:FragmentOtherDialogBinding
    private var listener : OnDialogNextBtnClickListener? = null
    private var toDoData: ToDoData? = null


    fun setListener(listener: OnDialogNextBtnClickListener) {
        this.listener = listener
    }

    companion object {
        const val TAG = "DialogFragment"
        @JvmStatic
        fun newInstance(taskId: String, task: String) =
            OtherDialogFragment().apply {
                arguments = Bundle().apply {
                    putString("taskId", taskId)
                    putString("task", task)
                }
            }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentOtherDialogBinding.inflate(inflater , container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null){

            toDoData = ToDoData(arguments?.getString("taskId").toString() ,arguments?.getString("task").toString())
            binding.todoEt.setText(toDoData?.task!!.split("/delimiter")[0])
            binding.otherEt.setText(toDoData?.task!!.split("/delimiter")[1])
            binding.mcq1Et.setText(toDoData?.task!!.split("/delimiter")[2])
            binding.mcq2Et.setText(toDoData?.task!!.split("/delimiter")[3])
            binding.mcq3Et.setText(toDoData?.task!!.split("/delimiter")[4])
            binding.mcq4Et.setText(toDoData?.task!!.split("/delimiter")[5])
        }


        binding.todoClose.setOnClickListener {
            dismiss()
        }

        binding.todoNextBtn.setOnClickListener {

            val ques = binding.todoEt.text.toString()
            val ans = binding.otherEt.text.toString()
            val optionA = binding.mcq1Et.text.toString()
            val optionB = binding.mcq2Et.text.toString()
            val optionC = binding.mcq3Et.text.toString()
            val optionD = binding.mcq4Et.text.toString()

            if(!(ans.isNotEmpty()&&(optionA.isNotEmpty()||optionB.isNotEmpty()||optionC.isNotEmpty()||optionD.isNotEmpty()))) {
                val todoTask =
                    "$ques/delimiter$ans/delimiter$optionA/delimiter$optionB/delimiter$optionC/delimiter$optionD"
                if (todoTask.isNotEmpty()) {
                    if (toDoData == null) {
                        listener?.saveTask(todoTask, binding.todoEt)
                    } else {
                        toDoData!!.task = todoTask
                        listener?.updateTask(toDoData!!, binding.todoEt)
                    }

                }
            } else Toast.makeText(context,"Invaild Question Format", Toast.LENGTH_LONG).show()
        }
    }

    interface OnDialogNextBtnClickListener{
        fun saveTask(todoTask:String , todoEdit:TextInputEditText)
        fun updateTask(toDoData: ToDoData , todoEdit:TextInputEditText)
    }

}