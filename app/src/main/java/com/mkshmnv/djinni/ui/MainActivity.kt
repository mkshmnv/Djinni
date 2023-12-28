package com.mkshmnv.djinni.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
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
            val drawerLayout: DrawerLayout = drawerLayout
            navController = findNavController(R.id.nav_host_fragment_content_main)
            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_profile_pager_fragment,
                    R.id.nav_dashboard_web_view,
                    R.id.nav_inbox,
                    R.id.nav_jobs,
                    R.id.nav_exit
//                    R.id.nav_salaries TODO: impl SalaryFragment
                ), drawerLayout
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)

            val header = navView.getHeaderView(0)
            header.setOnClickListener {
                navController.navigate(R.id.nav_profile_pager_fragment)
                drawerLayout.closeDrawers()
            }
        }

        // Show/Hide app bar for auth fragments
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in appBarConfiguration.topLevelDestinations) {
                this.supportActionBar?.show()
            } else {
                this.supportActionBar?.hide()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        @Suppress("DEPRECATION")
        super.onBackPressed()
        exitProcess(0)
    }
}