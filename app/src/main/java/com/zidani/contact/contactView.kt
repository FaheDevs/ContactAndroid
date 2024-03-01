package com.zidani.contact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide


class contactView : Fragment() {

    val args: contactViewArgs by navArgs() // Correct usage of navArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_contact_view, container, false)

        // Initialize UI components and set their values based on args
        val userNameDetail = view.findViewById<TextView>(R.id.userNameDetail)
        val userImageDetail = view.findViewById<ImageView>(R.id.userImageDetail)
        val backButton = view.findViewById<Button>(R.id.backButton)

        // Set the values from args
        userNameDetail.text = args.userName
        Glide.with(this).load(args.userImageUrl).into(userImageDetail)

        // Set the click listener for the back button
        backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        return view
    }

}