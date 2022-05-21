package cl.cdum.therickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import cl.cdum.therickandmorty.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        binding.apply {
            includeToolbar.apply {
                val navHostFragment =
                    supportFragmentManager.findFragmentById(navHostFragment.id) as NavHostFragment
                navController = navHostFragment.findNavController()

                setSupportActionBar(toolbar)
                supportActionBar?.setDisplayShowTitleEnabled(false)

                setupActionBarWithNavController(navController)

                navController.addOnDestinationChangedListener { _, destination, _ ->
                    toolbarTitle.text = destination.label
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() or super.onSupportNavigateUp()
    }
}