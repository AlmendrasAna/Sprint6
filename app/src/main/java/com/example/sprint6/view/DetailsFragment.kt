package com.example.sprint6.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import coil.load
import com.example.sprint6.R
import com.example.sprint6.databinding.FragmentDetailsBinding
import com.example.sprint6.toPesos
import okhttp3.internal.http.hasBody
import retrofit2.http.Path

private const val ARG_PARAM1 = "id"

class DetailsFragment : Fragment() {

    private var param1: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    lateinit var binding: FragmentDetailsBinding
    private val phoneVM: PhoneVM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        init()


        return binding.root
    }

    private fun init() {
        val id = param1
        phoneVM.getOneDetails(id)

        val detailsPhone = phoneVM.getOneDetailsPhoneId(id)

        detailsPhone.observe(viewLifecycleOwner) { detailPhone ->
            if (detailPhone != null) {
                binding.nameDetailsTxt.text = detailPhone.name
                binding.imageDetails.load(detailPhone.image)
                binding.lastPriceTxt.text = detailPhone.lastPrice.toPesos()
                binding.priceDetaisTxt.text = detailPhone.price.toPesos()
                binding.descriptionTxt.text = detailPhone.description
                var txt: String
                if (detailPhone.credit) {
                    txt = getString(R.string.acepta_credito)
                } else {
                    txt = getString(R.string.no_acepta_credito)
                }
                binding.creditTxt.text = txt

                binding.sendMailB.setOnClickListener {


                     val mail = getString(R.string.destinatario_msn)
                    val intentEmail = Intent(Intent.ACTION_SEND, Uri.parse(mail))
                    val bodyMsn=getString(R.string.body_msn,detailPhone.name,detailPhone.id)
                    val asunt = getResources().getString(R.string.asunt,detailPhone.name,detailPhone.id)

                    intentEmail.type = "plain/text"
                    intentEmail.putExtra(Intent.EXTRA_EMAIL, arrayOf(mail))

                    intentEmail.putExtra(Intent.EXTRA_SUBJECT,asunt )

                    intentEmail.putExtra(Intent.EXTRA_TEXT, bodyMsn)

                    startActivity(Intent.createChooser(intentEmail, "Consulta producto"))

                }
            }

        }
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

}

