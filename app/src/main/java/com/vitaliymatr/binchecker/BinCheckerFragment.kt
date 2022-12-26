package com.vitaliymatr.binchecker


import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.vitaliymatr.binchecker.databinding.FragmetBinCheckerBinding
import java.lang.NullPointerException

private const val TAG = "BinCheckerFragment"

class BinCheckerFragment : Fragment() {
    private var _binding: FragmetBinCheckerBinding? = null
    private val binding get() = _binding!!
    lateinit var binNumber: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmetBinCheckerBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.button.setOnClickListener { showData() }
        return view

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        fun newInstance() = BinCheckerFragment()
    }


    private fun showData() {

        binNumber = binding.editTextNumber.text.toString()
        Log.d(TAG, "Response received: $binNumber")
        val binListLiveData = BinListFetch().fetchContents(binNumber)

        binListLiveData.observe(
            viewLifecycleOwner,
            Observer { binList ->
                try {
                    binding.schemeTextView.text = binList.scheme
                    binding.schemeTextView.setTextColor(Color.BLACK)
                    binding.brandTextView.text = binList.brand
                    binding.brandTextView.setTextColor(Color.BLACK)
                    if (binList.number?.length == null) {
                        binding.lengthTextView.text = "-"
                    } else {
                        binding.lengthTextView.text = binList.number?.length.toString()
                        binding.lengthTextView.setTextColor(Color.BLACK)
                    }

                    if (binList.number?.luhn == true) {
                        binding.yesTextView.setTypeface(null, Typeface.BOLD)
                        binding.yesTextView.setTextColor(Color.BLACK)
                        binding.noTextView.setTypeface(null, Typeface.NORMAL)
                        binding.noTextView.setTextColor(Color.GRAY)
                    } else {
                        binding.noTextView.setTypeface(null, Typeface.BOLD)
                        binding.noTextView.setTextColor(Color.BLACK)
                        binding.yesTextView.setTypeface(null, Typeface.NORMAL)
                        binding.yesTextView.setTextColor(Color.GRAY)
                    }
                    if (binList.type == "debit") {
                        binding.debitTextView.setTypeface(null, Typeface.BOLD)
                        binding.debitTextView.setTextColor(Color.BLACK)
                        binding.creditTextView.setTypeface(null, Typeface.NORMAL)
                        binding.creditTextView.setTextColor(Color.GRAY)
                    } else if (binList.type == "credit") {
                        binding.creditTextView.setTypeface(null, Typeface.BOLD)
                        binding.creditTextView.setTextColor(Color.BLACK)
                        binding.debitTextView.setTypeface(null, Typeface.NORMAL)
                        binding.debitTextView.setTextColor(Color.GRAY)
                    }
                    if (binList.prepaid == true) {
                        binding.prepaidYesTextView.setTypeface(null, Typeface.BOLD)
                        binding.prepaidYesTextView.setTextColor(Color.BLACK)
                        binding.prepaidNoTextView.setTypeface(null, Typeface.NORMAL)
                        binding.prepaidNoTextView.setTextColor(Color.GRAY)
                    } else {
                        binding.prepaidNoTextView.setTypeface(null, Typeface.BOLD)
                        binding.prepaidNoTextView.setTextColor(Color.BLACK)
                        binding.prepaidYesTextView.setTypeface(null, Typeface.NORMAL)
                        binding.prepaidYesTextView.setTextColor(Color.GRAY)
                    }
                    binding.emojiTextView.text = binList.country?.emoji
                    binding.nameTextView.text = binList.country?.name
                    binding.nameTextView.setTextColor(Color.BLACK)
                    if ((binList.country?.latitude == null) || (binList.country?.longitude == null)) {
                        binding.latitudeTextView.text = "-"
                        binding.longitudeTextView.text = "-"
                    } else {
                        binding.latitudeTextView.text = binList.country?.latitude.toString()
                        binding.latitudeTextView.setTextColor(Color.BLACK)
                        binding.longitudeTextView.text = binList.country?.longitude.toString()
                        binding.longitudeTextView.setTextColor(Color.BLACK)
                    }
                    if ((binList.bank?.city == "") || (binList.bank?.name == "")) {
                        binding.commaTextView.text = ""
                    } else {
                        binding.commaTextView.text = ", "
                    }
                    binding.bankNameTextView.text = binList.bank?.name
                    binding.bankNameTextView.setTextColor(Color.BLACK)
                    binding.cityTextView.text = binList.bank?.city
                    binding.cityTextView.setTextColor(Color.BLACK)
                    binding.siteTextView.text = binList.bank?.url
                    binding.siteTextView.setTextColor(Color.BLACK)
                    binding.phoneTextView.text = binList.bank?.phone
                    binding.phoneTextView.setTextColor(Color.BLACK)
                } catch (e: NullPointerException) {
                    Toast.makeText(this@BinCheckerFragment.requireActivity(), "there is no information for the entered number", Toast.LENGTH_LONG).show()
                }

            }
        )

    }
}