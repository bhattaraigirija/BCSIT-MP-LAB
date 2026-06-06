package com.mobile.bcsit6th

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.appbar.MaterialToolbar
import java.util.Locale

class RegistrationPage : AppCompatActivity(), OnMapReadyCallback {

    lateinit var databaseHelper: DatabaseHelper
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var mGoogleMap: GoogleMap? = null
    private lateinit var ivProfile: ImageView

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            ivProfile.setImageURI(it)
        }
    }

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                fetchCurrentLocation()
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                fetchCurrentLocation()
            }
            else -> {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

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

        // Image Picker Logic
        ivProfile = findViewById(R.id.ivProfile)
        val btnPickImage = findViewById<Button>(R.id.btnPickImage)
        btnPickImage.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        // Google Maps & Location Logic
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val btnFetchLocation = findViewById<Button>(R.id.btnFetchLocation)
        btnFetchLocation.setOnClickListener {
            requestLocationPermissions()
        }

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

    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap
        
        // Initial position (Kathmandu)
        val kathmandu = LatLng(27.7172, 85.3240)
        mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(kathmandu, 12f))

        // Map Click Listener to pick location
        mGoogleMap?.setOnMapClickListener { latLng ->
            mGoogleMap?.clear()
            mGoogleMap?.addMarker(MarkerOptions().position(latLng).title("Selected Location"))
            updateAddressFromLatLng(latLng)
        }
    }

    private fun requestLocationPermissions() {
        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ))
    }

    private fun fetchCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && 
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                val currentLatLng = LatLng(it.latitude, it.longitude)
                mGoogleMap?.clear()
                mGoogleMap?.addMarker(MarkerOptions().position(currentLatLng).title("Current Location"))
                mGoogleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
                updateAddressFromLatLng(currentLatLng)
            }
        }
    }

    private fun updateAddressFromLatLng(latLng: LatLng) {
        val geocoder = Geocoder(this, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            if (addresses?.isNotEmpty() == true) {
                val addressStr = addresses[0].getAddressLine(0)
                findViewById<EditText>(R.id.etAddress).setText(addressStr)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
