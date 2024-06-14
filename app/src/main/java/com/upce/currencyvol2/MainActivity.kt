import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
                binding.editTextNumberIMPUT.text.toString(),
                binding.spinner1L.selectedItem.toString(),
                binding.spinner2R.selectedItem.toString()
            )
        }

        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { event ->
                when(event) {
                    is MainViewModel.CurrencyEvent.Success -> {
                        binding.textViewRESULT.text = event.result.toString()
                    }
                    is MainViewModel.CurrencyEvent.Failure -> {
                        println("error: ${event.errorText}")
                    }
                    is MainViewModel.CurrencyEvent.Loading -> {
                        println("loading")
                    }
                    else -> Unit
                }
            }
        }
    }
}
