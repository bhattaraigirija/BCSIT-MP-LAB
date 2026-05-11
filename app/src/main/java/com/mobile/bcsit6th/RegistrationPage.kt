package com.mobile.bcsit6th

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.interaction.HoverInteraction
import com.google.android.material.appbar.MaterialToolbar

class RegistrationPage : AppCompatActivity() {

    lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_page)
        databaseHelper = DatabaseHelper(this)

        val toolbar = findViewById<MaterialToolbar>(R.id.registrationToolbar)
        setSupportActionBar(toolbar)

        // Enable back button in toolbar
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        //form registration and validation logic
        val name = findViewById<EditText>(R.id.etName)
        val email = findViewById<EditText>(R.id.etEmail)
        val address = findViewById<EditText>(R.id.etAddress)
        val phone = findViewById<EditText>(R.id.etPhone)
        val radioGroup = findViewById<RadioGroup>(R.id.rgGender)
        val button = findViewById<Button>(R.id.btnSubmit)

        button.setOnClickListener {
            val nameStr = name.text.toString()
            val emailStr = email.text.toString()
            val addressStr = address.text.toString()
            val phoneStr =  phone.text.toString()

            if(nameStr.isEmpty()) {
                name.error = "Name is required"
                return@setOnClickListener
            }
            if(emailStr.isEmpty()) {
                email.error = "Email is required"
                return@setOnClickListener
            }
            if(addressStr.isEmpty()) {
                address.error = "Address is required"
                return@setOnClickListener
            }
            if(phoneStr.isEmpty()) {
                phone.error = "Phone is required"
                return@setOnClickListener
            }

            val selectedId = radioGroup.checkedRadioButtonId
            if(selectedId == -1) {
                Toast.makeText(this, "Gender is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val gender = findViewById<RadioButton>(selectedId).text.toString()


            //pass data to another activity
//            val intent = Intent(this, ProfileActivity::class.java)
//            intent.putExtra("name", nameStr)
//            intent.putExtra("email", emailStr)
//            intent.putExtra("address", addressStr)
//            intent.putExtra("phone", phoneStr)
//            intent.putExtra("gender", gender)
//            startActivity(intent)

            // Save data in SQLite
            val result = databaseHelper.insertStudent(
                nameStr,
                emailStr,
                addressStr,
                phoneStr,
                gender
            )
            if(result) {
                Toast.makeText(
                    this, "Registration Successful",  Toast.LENGTH_SHORT).show()
                startActivity(
                    Intent(this, StudentListActivity::class.java)
                )
            } else {
                Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show()
            }
        }


    }
}