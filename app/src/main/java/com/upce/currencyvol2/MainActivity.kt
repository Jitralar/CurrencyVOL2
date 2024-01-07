package com.upce.currencyvol2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.upce.currencyvol2.data.main.MainViewModel
import com.upce.currencyvol2.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            viewModel.convert(
                binding.etFrom.text.toString(), //FIXME: a dej spravny imput
                binding.spFromCurrency.selectedItem.toString(),
                binding.spToCurrency.selectedItem.toString(),
            )
        }

        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { event ->
                when(event) {
                    is MainViewModel.CurrencyEvent.Success -> {
                        binding.textViewRESULT.setTextColor(Color.BLACK)
                        binding.textViewRESULT.text = event.resultText
                    }
                    is MainViewModel.CurrencyEvent.Failure -> {
                        //TODO: error something
                    }
                    is MainViewModel.CurrencyEvent.Loading -> {
                        //TODO: loading something
                    }
                    else -> Unit
                }
            }
        }
    }
}
