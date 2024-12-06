package com.example.studentportal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BranchSubjectsAdapter(
    private val branchSubjectsList: List<BranchSubjects>,
    private val listener: OnSubjectClickListener
) : RecyclerView.Adapter<BranchSubjectsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val branchTextView: TextView = view.findViewById(R.id.branchTextView)
        val subjectsTextView: TextView = view.findViewById(R.id.subjectsTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_branch_subjects_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val branchSubjects = branchSubjectsList[position]
        holder.branchTextView.text = branchSubjects.branch
        holder.subjectsTextView.text = branchSubjects.subjects.joinToString(separator = "\n")

        // Set click listener for each subject
        holder.subjectsTextView.setOnClickListener {
            branchSubjects.subjects.forEach { subject ->
                holder.subjectsTextView.setOnClickListener {
                    listener.onSubjectClick(branchSubjects, branchSubjects.subjects[position])  // Pass the clicked subject name
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return branchSubjectsList.size
    }

    interface OnSubjectClickListener {
        fun onSubjectClick(branchSubjects: BranchSubjects, subjectName: String)
    }
}
