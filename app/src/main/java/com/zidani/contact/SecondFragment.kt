package com.zidani.contact

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation

class SecondFragment : Fragment() {
    private lateinit var TextTwo : TextView
    private lateinit var TextThree : TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view : View = inflater.inflate(R.layout.fragment_second, container, false)
        TextTwo = view.findViewById(R.id.numberTwoTextView)
        TextThree = view.findViewById(R.id.numberThreeTextView)

        TextTwo.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_fristFragment)
        }
        TextThree.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_contactListFragment)
        }

        // Inflate the layout for this fragment
        return view
    }


}