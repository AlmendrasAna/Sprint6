package com.example.sprint6.view.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sprint6.R
import com.example.sprint6.data.local.PhoneProductsEntity
import com.example.sprint6.databinding.ItemBinding


class AdapterProduct : RecyclerView.Adapter<AdapterProduct.ViewHolder>() {


    private var listProduct: List<PhoneProductsEntity> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val martHotel = listProduct[position]
        holder.bind(martHotel)
    }

    fun setData(productsEntity: List<PhoneProductsEntity>) {

        this.listProduct = productsEntity

        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(phoneProductsEntity: PhoneProductsEntity) {


            binding.nameProduct.text = phoneProductsEntity.name.toString()
            binding.priceProduct.text = "$ " + phoneProductsEntity.price.toString()
            binding.imagePhone.load(phoneProductsEntity.image)

            binding.cardItem.setOnClickListener {

                var bundle = Bundle()
                bundle.putInt("id", phoneProductsEntity.id)

                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_productsFragment_to_detailsFragment, bundle)
            }

        }


    }

}
