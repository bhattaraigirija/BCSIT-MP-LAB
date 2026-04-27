package com.mobile.bcsit6th

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView

class HomeFragment : Fragment() {

    private lateinit var btnSignUp: Button
    private lateinit var cardProfile: MaterialCardView
    private lateinit var cardCourses: MaterialCardView
    private lateinit var cardGrades: MaterialCardView
    private lateinit var cardSettings: MaterialCardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize views
        btnSignUp = view.findViewById(R.id.btnSignUp)
        cardProfile = view.findViewById(R.id.cardProfile)
        cardCourses = view.findViewById(R.id.cardCourses)
        cardGrades = view.findViewById(R.id.cardGrades)
        cardSettings = view.findViewById(R.id.cardSettings)

        // Set click listeners
        btnSignUp.setOnClickListener {
            showDialog()
        }

        cardProfile.setOnClickListener {
            Toast.makeText(requireContext(), "Profile Clicked", Toast.LENGTH_SHORT).show()
        }

        cardCourses.setOnClickListener {
            Toast.makeText(requireContext(), "Courses Clicked", Toast.LENGTH_SHORT).show()
        }

        cardGrades.setOnClickListener {
            Toast.makeText(requireContext(), "Grades Clicked", Toast.LENGTH_SHORT).show()
        }

        cardSettings.setOnClickListener {
            Toast.makeText(requireContext(), "Settings Clicked", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Registration")
        builder.setMessage("Are you sure you want to register?")

        builder.setPositiveButton("Yes") { dialog, _ ->
            startActivity(Intent(requireContext(), RegistrationPage::class.java))
            dialog.dismiss()
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }
}