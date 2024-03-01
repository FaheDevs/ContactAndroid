package com.zidani.contact

import UsersItem
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ContactListFragment : Fragment() {
    private lateinit var rvMain: RecyclerView
    private lateinit var myAdapter: MyAdapter

    private val BASE_URL = "https://api.github.com"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the toolbar as the action bar
        val toolbar = view.findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.toolbar)
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)

        // Show the Up button in the action bar
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set up the toolbar's navigation click listener
        toolbar.setNavigationOnClickListener {
            // This line navigates up in the app's navigation hierarchy.
            // You can customize this action, for example, using NavController to navigate up:
            findNavController().navigateUp()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_contact_list, container, false)

        rvMain = view.findViewById(R.id.contact_list_recyclerview)
        rvMain.layoutManager = LinearLayoutManager(view.context)
        getAllData()

        return view
    }

    private fun getAllData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retroData = retrofit.getPosts()

        retroData.enqueue(object : Callback<List<UsersItem>> {
            override fun onResponse(call: Call<List<UsersItem>>, response: Response<List<UsersItem>>) {
                if (response.isSuccessful) {
                    val list = response.body()!!
                    Log.d("TAG", "onResponse: $list")
                    myAdapter = MyAdapter(requireContext(), list) { userItem ->
                        val action = ContactListFragmentDirections.actionContactListFragmentToContactView(userItem.login, userItem.avatar_url)
                        findNavController().navigate(action)
                    }
                    rvMain.adapter = myAdapter
                } else {
                    Toast.makeText(requireContext(), "Failed to retrieve items", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
