package com.zidani.contact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation


class FristFragment : Fragment() {
    private lateinit var TextOne : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_frist, container, false)
        TextOne = view.findViewById(R.id.numberOneTextView)

        TextOne.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_secondFragment)
        }
        return view
    }

}