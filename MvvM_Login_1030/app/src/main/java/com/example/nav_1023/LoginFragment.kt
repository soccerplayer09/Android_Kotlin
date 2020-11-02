package com.example.nav_1023

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.nav_1023.databinding.ActivityMainBinding
import com.example.nav_1023.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    lateinit var bind : FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        bind.viewModel = viewModel

        bt_Login.setOnClickListener() {
            viewModel.findUserWithID(viewModel.user.Info_Id.toString()).observe(viewLifecycleOwner, Observer { find_userinfo ->
                if (viewModel.user.Info_Id.isEmpty() || viewModel.user.Password.isEmpty()){
                    Toast.makeText(activity, "Empty bin exists, Join fails.", Toast.LENGTH_SHORT).show()
                    return@Observer
                }
                if (find_userinfo == null) {
                    Toast.makeText(activity, "Login, fails", Toast.LENGTH_SHORT).show()
                    return@Observer
                } else {
                    if (viewModel.user.Password.toString() == find_userinfo.Password.toString()) {
                        Toast.makeText(activity, "Login, success", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_loginFragment_to_infoFragment)
                    }
                    else {
                        Toast.makeText(activity, "Login, fails", Toast.LENGTH_SHORT).show()
                        return@Observer
                    }
                }
            })
        }

        bt_Join1.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_joinFragment)
        }
    }
}