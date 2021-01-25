package com.example.studentslist.activities

import android.content.Intent
import android.os.Bundle
import android.view.View.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentslist.R
import com.example.studentslist.databinding.ActivityMainBinding
import com.example.studentslist.model.Student
import com.example.studentslist.simplelist.SimpleStudentList
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        showImage()
        totalNumberOfStudents()

        with(mBinding){
            addButton.setOnClickListener {
                addStudent()
            }

            showButton.setOnClickListener {
                startAcivity()
            }
        }
    }

    private fun totalNumberOfStudents() {
        mBinding.countSize.text = SimpleStudentList.mList.size.toString()
    }

    private fun startAcivity() {
        startActivity(Intent(this@MainActivity, StudentListActivity::class.java))
    }

    private fun addStudent() {
        val firstName: String
        val lastName: String
        with(mBinding) {
            firstName = studentFirstNameEt.text.toString()
            lastName = studentLastNameEt.text.toString()
        }

        if (validateNames(firstName, lastName)) {
            val student = Student(firstName, lastName)
            if (notContains(student)) {
                SimpleStudentList.mList.add(student)
                totalNumberOfStudents()
                showToast(
                    this,
                    getString(R.string.student_has_added)
                )
                clearEditTexts()
            } else {
                showToast(
                    this,
                    resources.getString(
                        R.string.contains_student
                    )
                )
            }
        } else {
            showToast(
                this,
                resources.getString(R.string.field_empty)
            )
        }

    }

    private fun clearEditTexts() {
        with(mBinding){
            studentFirstNameEt.text.clear()
            studentLastNameEt.text.clear()
        }
    }

    private fun showToast(mainActivity: MainActivity, message: String) {
        Toast.makeText(mainActivity, message, Toast.LENGTH_SHORT).show()
    }

    private fun notContains(student: Student): Boolean {
        if (SimpleStudentList.mList.contains(student))
            return false
        return true
    }

    private fun validateNames(firstName: String, lastName: String): Boolean {
        if (firstName.isEmpty())
            return false
        if (lastName.isEmpty())
            return false
        return true
    }

    private fun showImage() {
        GlobalScope.launch(context = Dispatchers.Main) {
            delay(3000)
            mBinding.imageSarcazm.visibility = GONE
            mBinding.constraintStudents.visibility = VISIBLE
        }
    }
}


