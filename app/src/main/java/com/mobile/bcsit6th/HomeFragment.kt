package com.mobile.bcsit6th

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.compose.material3.Button

class HomeFragment : Fragment() {

    private lateinit var btnSignUp: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        btnSignUp = view.findViewById(R.id.btnSignUp)

        btnSignUp.setOnClickListener {
            showDialog()
        }

        return view
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Registration")
        builder.setMessage("Are you sure?")

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