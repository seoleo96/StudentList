package com.example.studentslist.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentslist.adapter.MyAdapter
import com.example.studentslist.databinding.ActivityStudentListBinding
import com.example.studentslist.model.Student
import com.example.studentslist.simplelist.SimpleStudentList

private lateinit var mBinding: ActivityStudentListBinding
private lateinit var mStudentAdapter: MyAdapter

class StudentListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityStudentListBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setAdapter()
        setList()
    }

    private fun setAdapter(){
        mStudentAdapter = MyAdapter()
        mBinding.rvStudents.adapter = mStudentAdapter
    }

    private fun setList() {
        if (SimpleStudentList.mList.isEmpty())
            mStudentAdapter.setStudentList(mutableListOf())
        else
            mStudentAdapter.setStudentList(SimpleStudentList.mList.toList() as MutableList<Student>)
    }
}