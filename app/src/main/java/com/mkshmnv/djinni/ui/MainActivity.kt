package com.mkshmnv.djinni.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mkshmnv.djinni.R
import com.mkshmnv.djinni.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)
            setSupportActionBar(appBarMain.toolbar)
            navController = findNavController(R.id.nav_host_fragment_content_main)
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
            bottomNavMenu.setupWithNavController(navController)
        }

        // Show/Hide bottom menu and app bar for auth fragments
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in appBarConfiguration.topLevelDestinations) {
                showAppBarWithBottomMenu(true)
            } else {
                showAppBarWithBottomMenu(false)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    // Show/Hide bottom menu and app bar
    private fun showAppBarWithBottomMenu(show: Boolean) {
        if (show) {
            binding.bottomNavMenu.visibility = View.VISIBLE
            this.supportActionBar?.show()
        } else {
            this.supportActionBar?.hide()
            binding.bottomNavMenu.visibility = View.GONE
        }
    }

    // Show/Hide logo/button save for fragments TODO: change this
    fun showSaveButton(show: Boolean) {
        if (show) {
            binding.appBarMain.ivLogo.visibility = View.GONE
        } else {
            binding.appBarMain.ivLogo.visibility = View.VISIBLE
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        exitProcess(0)
    }
}