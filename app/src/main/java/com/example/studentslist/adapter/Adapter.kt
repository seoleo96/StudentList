package com.example.studentslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentslist.databinding.RowStudentBinding
import com.example.studentslist.model.Student

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var studentList = mutableListOf<Student>()

    fun setStudentList(list: MutableList<Student>) {
        this.studentList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: RowStudentBinding =
            RowStudentBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentStudent = studentList[position]
        holder.setStudentInfo(currentStudent)
    }

    override fun getItemCount(): Int {
        if (studentList.size == 0)
            return 0
        return studentList.size
    }

    inner class MyViewHolder(val mBinding: RowStudentBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun setStudentInfo(currentStudent: Student) {
            with(mBinding) {
                firstName.text = currentStudent.firstName.capitalize()
                lastName.text = currentStudent.lastName.capitalize()
            }
        }
    }
}