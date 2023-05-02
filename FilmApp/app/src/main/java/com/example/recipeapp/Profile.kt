package com.example.recipeapp

import android.content.Intent.getIntent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Profile : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentViewModel = ViewModelProvider(requireActivity()).get(FragmentViewModel::class.java)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private var inputSurname: String? = null
    private var inputPhone: String? = null
    private var inputEmail: String? = null

    private lateinit var fragmentViewModel: FragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val surnameP: TextView = view.findViewById(R.id.surnameProfile)
        val phoneP: TextView = view.findViewById(R.id.phone)
        val emailP: TextView = view.findViewById(R.id.email)

        val bundle = arguments
        inputSurname = bundle?.getString("dataSurname")
        inputPhone = bundle?.getString("dataPhone")
        inputEmail = bundle?.getString("dataEmail")

        this.fragmentViewModel.surname.observe(viewLifecycleOwner) { inputSurname ->
            if (inputSurname != null) {
                surnameP.text = inputSurname
            }
        }

        this.fragmentViewModel.phone.observe(viewLifecycleOwner) { inputPhone ->
            if (inputPhone != null) {
                phoneP.text = inputPhone
            }
        }

        this.fragmentViewModel.email.observe(viewLifecycleOwner) { inputEmail ->
            if (inputEmail != null) {
                emailP.text = inputEmail
            }
        }


        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Profile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}