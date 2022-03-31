package com.example.todo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.todo.R
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout

class setting : Fragment() {
    lateinit var autoCompleteTextView: MaterialAutoCompleteTextView
    val modes= arrayOf("Light","Dark")
    lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        autoCompleteTextView=view.findViewById(R.id.modeSelected)
        arrayAdapter= ArrayAdapter(requireContext(),R.layout.mode_list,modes)
        autoCompleteTextView.setAdapter(arrayAdapter)
        autoCompleteTextView.setOnItemClickListener { adapterView, View, pos, id ->
            val item=adapterView.getItemIdAtPosition(pos).toInt()
            if(item==0){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }else if (item==1){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }

        }


    }
}