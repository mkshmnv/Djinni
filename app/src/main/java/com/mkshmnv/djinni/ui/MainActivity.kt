package com.mkshmnv.djinni.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)
            setSupportActionBar(appBarMain.toolbar)
            val navController = findNavController(R.id.nav_host_fragment_content_main)
            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_profile_pager_fragment,
                    R.id.nav_dashboard_web_view,
                    R.id.nav_inbox,
                    R.id.nav_jobs,
//                    R.id.nav_salaries TODO: impl SalaryFragment
                )
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    // Show/Hide action bar and bottom menu for fragments
    fun showMenuAndActionBar(show: Boolean) {
        if (show) {
            binding.navView.visibility = View.VISIBLE
            this.supportActionBar?.show()
        } else {
            this.supportActionBar?.hide()
            binding.navView.visibility = View.GONE
        }
    }

    // Show/Hide logo/button save for fragments
    fun showSaveButton(show: Boolean) {
        if (show) {
            binding.appBarMain.ivLogo.visibility = View.GONE
            binding.appBarMain.btnSave.visibility = View.VISIBLE
        } else {
            binding.appBarMain.ivLogo.visibility = View.VISIBLE
            binding.appBarMain.btnSave.visibility = View.GONE
        }
    }
}