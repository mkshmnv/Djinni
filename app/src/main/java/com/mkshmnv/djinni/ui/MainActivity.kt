package com.mkshmnv.djinni.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
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
                    R.id.nav_dashboard_web_view,
                    R.id.nav_inbox,
                    R.id.nav_jobs,
                    R.id.nav_salaries, // TODO: impl SalaryFragment
                    R.id.nav_account_pager_fragment, // Settings
                    R.id.nav_sign_out,
                    R.id.nav_delete
                ), drawerLayout
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
            // Setup the NavigationView item click listener
            navView.setNavigationItemSelectedListener { menuItem ->
                val builder = AlertDialog.Builder(this@MainActivity)
                when (menuItem.itemId) {
                    R.id.nav_sign_out -> {
                        val title = getString(R.string.auth_sign_out_alert_title)
                        val message = getString(R.string.auth_sign_out_alert_message)
                        val positive = getString(R.string.auth_alert_positive)
                        val negative = getString(R.string.auth_alert_negative)
                        builder.setTitle(title).setMessage(message)
                        builder.setPositiveButton(positive) { _, _ ->
                            menuItem.onNavDestinationSelected(navController)
                        }
                        builder.setNegativeButton(negative) { _, _ ->
                            // Close alert dialog
                        }
                        val alertDialog = builder.create()
                        alertDialog.show()
                    }

                    R.id.nav_delete -> {
                        val title = getString(R.string.auth_delete_alert_title)
                        val message = getString(R.string.auth_delete_alert_message)
                        val positive = getString(R.string.auth_alert_positive)
                        val negative = getString(R.string.auth_alert_negative)
                        builder.setTitle(title).setMessage(message)
                        builder.setPositiveButton(positive) { _, _ ->
                            menuItem.onNavDestinationSelected(navController)
                        }
                        builder.setNegativeButton(negative) { _, _ ->
                            // Close alert dialog
                        }
                        val alertDialog = builder.create()
                        alertDialog.show()
                    }

                    R.id.nav_settings -> navController.navigate(R.id.nav_account_pager_fragment)

                    else -> menuItem.onNavDestinationSelected(navController)
                }
                drawerLayout.closeDrawers()
                true
            }
        }

        // Show/Hide app bar for auth fragments
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.nav_auth_pager_fragment) {
                this.supportActionBar?.hide()
            } else {
                this.supportActionBar?.show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        @Suppress("DEPRECATION") super.onBackPressed()
        exitProcess(0)
    }
}