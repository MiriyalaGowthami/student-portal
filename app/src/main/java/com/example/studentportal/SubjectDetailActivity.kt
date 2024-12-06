package com.example.studentportal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class SubjectDetailActivity : AppCompatActivity() {
    private lateinit var subjectNameTextView: TextView
    private lateinit var subjectDescriptionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject_detail)

        subjectNameTextView = findViewById(R.id.subjectNameTextView)
        subjectDescriptionTextView = findViewById(R.id.subjectDescriptionTextView)

        // Retrieve the subject name from Intent
        val subjectName = intent.getStringExtra("subjectName") ?: "Unknown Subject"

        // Update the subject name in the TextView
        subjectNameTextView.text = subjectName

        // Retrieve and set the subject description based on the subject name
        subjectDescriptionTextView.text = getSubjectDescription(subjectName)
    }

    // Helper function to return subject descriptions based on the subject name
    private fun getSubjectDescription(subjectName: String): String {
        return when (subjectName) {
            "Data Structures" -> "Data structures are used to store and organize data efficiently."
            "Algorithms" -> "Algorithms are step-by-step instructions for performing tasks or solving problems."
            "Operating Systems" -> "Operating systems manage hardware resources and provide services for computer programs."
            "Database Management Systems" -> "DBMS is software for storing, retrieving, and managing data in databases."
            "Analog Electronics" -> "Analog electronics deal with continuously variable signals."
            "Digital Signal Processing" -> "Digital Signal Processing manipulates information signals to improve accuracy and reliability."
            "Microprocessors" -> "Microprocessors are integrated circuits that perform computing tasks."
            "Control Systems" -> "Control systems regulate the behavior of other devices or systems."
            "Circuit Theory" -> "Circuit theory studies how electrical circuits work and behave."
            "Electrical Machines" -> "Electrical machines convert electrical energy into mechanical energy and vice versa."
            "Power Systems" -> "Power systems generate and distribute electrical energy."
            "Thermodynamics" -> "Thermodynamics studies heat and energy transfer in systems."
            "Fluid Mechanics" -> "Fluid mechanics studies the behavior of fluids and their interactions with forces."
            "Machine Design" -> "Machine design focuses on creating mechanical systems to perform tasks."
            "Manufacturing Processes" -> "Manufacturing processes involve converting raw materials into finished products."
            "Structural Analysis" -> "Structural analysis ensures that structures can withstand loads and forces."
            "Geotechnical Engineering" -> "Geotechnical engineering studies soil and its behavior in construction."
            "Transportation Engineering" -> "Transportation engineering involves planning and managing transportation systems."
            "Web Technologies" -> "Web technologies are used to create and manage websites and web applications."
            "Software Engineering" -> "Software engineering applies engineering principles to software development."
            else -> "No description available for this subject."
        }
    }
}
