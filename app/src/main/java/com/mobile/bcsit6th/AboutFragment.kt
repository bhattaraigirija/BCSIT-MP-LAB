package com.mobile.bcsit6th

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        // Find views
        val llPhone = view.findViewById<LinearLayout>(R.id.llPhone)
        val llEmail = view.findViewById<LinearLayout>(R.id.llEmail)
        val llWebsite = view.findViewById<LinearLayout>(R.id.llWebsite)
        val llLocation = view.findViewById<LinearLayout>(R.id.llLocation)

        // Phone Click - Redirect to Dialer
        llPhone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:+9779800000000")
            startActivity(intent)
        }

        // Email Click - Redirect to Email Client
        llEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:info@bcsit6th.com")
            intent.putExtra(Intent.EXTRA_SUBJECT, "Query from BCSIT App")
            startActivity(intent)
        }

        // Website Click - Redirect to Browser
        llWebsite.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.bcsit6th.com")
            startActivity(intent)
        }

        // Location Click - Redirect to Google Maps
        llLocation.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:0,0?q=Kathmandu, Nepal")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            if (mapIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(mapIntent)
            } else {
                // Fallback if Google Maps is not installed
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=Kathmandu, Nepal"))
                startActivity(browserIntent)
            }
        }

        return view
    }
}
