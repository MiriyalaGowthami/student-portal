package com.example.studentportal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    private var textmsz: TextView? = null
    private var logoutButton: Button? = null
    private var branchesRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        textmsz = findViewById(R.id.txt)
        logoutButton = findViewById(R.id.btn)
        branchesRecyclerView = findViewById(R.id.branchesRecyclerView)

        val sharedPref = getSharedPreferences("myprefs", Context.MODE_PRIVATE)
        val username = sharedPref.getString("Student Name", "")
        val branch = sharedPref.getString("Selected Branch", "")

        textmsz?.text = if (!username.isNullOrEmpty()) {
            "Hello, welcome $username!"
        } else {
            "Hello, welcome!"
        }

        branchesRecyclerView?.layoutManager = LinearLayoutManager(this)

        val adapter = BranchSubjectsAdapter(getBranchesWithSubjects(branch ?: ""), object : BranchSubjectsAdapter.OnSubjectClickListener {
            override fun onSubjectClick(branchSubjects: BranchSubjects, subjectName: String) {
                // Handle subject click to redirect to SubjectDetailActivity
                val intent = Intent(this@HomeActivity, SubjectDetailActivity::class.java)
                intent.putExtra("branchName", branchSubjects.branch)
                intent.putExtra("subjectName", subjectName)
                startActivity(intent)
            }
        })
        branchesRecyclerView?.adapter = adapter

        logoutButton?.setOnClickListener {
            val editor = sharedPref.edit()
            editor.putBoolean("isLoggedIn", false)
            editor.apply()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getBranchesWithSubjects(selectedBranch: String): List<BranchSubjects> {
        return listOf(
            BranchSubjects("CSE", listOf("Data Structures","Algorithms", "Operating Systems", "Database Management Systems")),
            BranchSubjects("ECE", listOf("Analog Electronics", "Digital Signal Processing", "Microprocessors", "Control Systems")),
            BranchSubjects("EEE", listOf("Circuit Theory", "Electrical Machines", "Power Systems", "Control Systems")),
            BranchSubjects("MECH", listOf("Thermodynamics", "Fluid Mechanics", "Machine Design", "Manufacturing Processes")),
            BranchSubjects("CIVIL", listOf("Structural Analysis", "Fluid Mechanics", "Geotechnical Engineering", "Transportation Engineering")),
            BranchSubjects("IT", listOf("Web Technologies", "Software Engineering", "Data Structures", "Database Management Systems"))
        ).filter { it.branch == selectedBranch }
    }
}
