package com.example.nav_1023

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nav_1023.databinding.FragmentJoinBinding
import com.example.nav_1023.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_join.*
import kotlinx.android.synthetic.main.fragment_login.*


class JoinFragment : Fragment() {
    lateinit var bind: FragmentJoinBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_join, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        bind.viewModel = viewModel

        bt_Join.setOnClickListener(){
            if (viewModel.user.Info_Id.isEmpty() || viewModel.user.Password.isEmpty() || viewModel.user.Info_name.isEmpty()) {
                Toast.makeText(activity, "Empty bin exists, Join fails.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.findUserWithID(viewModel.user.Info_Id.toString()).observe( viewLifecycleOwner, Observer { find_userinfo ->
                if(find_userinfo == null){
                    viewModel.insert()
                    Toast.makeText(activity, "Join, Success", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_joinFragment_to_loginFragment2)
                }
                else{
                    Toast.makeText(activity, "ID already exists, Join fails", Toast.LENGTH_SHORT).show()
                }
            })
        }

        bt_Login2.setOnClickListener{
            findNavController().navigate(R.id.action_joinFragment_to_loginFragment2)
        }
    }
}