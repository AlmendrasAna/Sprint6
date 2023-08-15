package com.example.sprint6.view.recycler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.sprint6.databinding.FragmentProductsBinding
import com.example.sprint6.view.PhoneVM


class ProductsFragment : Fragment() {

    lateinit var  binding: FragmentProductsBinding
    private val phoneVM : PhoneVM by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        phoneVM.getAllProducts()
        initAdapter()
        return binding.root
    }

    private fun initAdapter() {
        val adapter = AdapterProduct()
        binding.recyclerView.adapter = adapter
        phoneVM.phoneProductsLiveData().observe(viewLifecycleOwner){
            adapter.setData(it)
        }

    }

}
