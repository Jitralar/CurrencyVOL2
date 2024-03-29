package com.upce.currencyvol2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.upce.currencyvol2.data.main.MainViewModel
import com.upce.currencyvol2.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            viewModel.convert(
                binding.editTextNumberIMPUT.text.toString(),
                binding.spinner1L.selectedItem.toString(),
                binding.spinner2R.selectedItem.toString()

            )
        }

        //why this old shit
        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { event ->
                when(event) {
                    is MainViewModel.CurrencyEvent.Success -> {
                        binding.textViewRESULT.text = event.resultText
                    }
                    is MainViewModel.CurrencyEvent.Failure -> {
                        println("error fail")
                    }
                    is MainViewModel.CurrencyEvent.Loading -> {
                        println("error loading")
                    }
                    else -> Unit
                }
            }
        }
    }
}
