package com.example.recipeapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class Settings : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var fragmentViewModel: FragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentViewModel = ViewModelProvider(requireActivity()).get(FragmentViewModel::class.java)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        val btnEdit: Button = view.findViewById(R.id.editButton)

        btnEdit.setOnClickListener{
            val editSurname: EditText = view.findViewById(R.id.editSurname)
            val editPhone: EditText = view.findViewById(R.id.editPhone)
            val editEmail: EditText = view.findViewById(R.id.editEmail)

            val inputSurname = editSurname.text.toString()
            val inputPhone = editPhone.text.toString()
            val inputEmail = editEmail.text.toString()

            editSurname.setText(inputSurname)
            editPhone.setText(inputPhone)
            editEmail.setText(inputEmail)

            fragmentViewModel.surname.value = inputSurname
            fragmentViewModel.phone.value = inputPhone
            fragmentViewModel.email.value = inputEmail

            fragmentViewModel.surname.value?.let { editSurname.setText(it) }
            fragmentViewModel.phone.value?.let { editPhone.setText(it) }
            fragmentViewModel.email.value?.let { editEmail.setText(it) }


            val bundle = Bundle()
            bundle.putString("dataSurname", inputSurname)
            bundle.putString("dataPhone", inputPhone)
            bundle.putString("dataEmail", inputEmail)

            val fragment = Profile()
            fragment.arguments = bundle

            fragmentManager?.beginTransaction()

                ?.add(R.id.frame_layout, fragment)
                ?.addToBackStack(null) // Add to back stack
                ?.commit()

        }
        return view
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Settings().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}