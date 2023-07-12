package com.vasilets.navigationcomponenttest

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vasilets.navigationcomponenttest.databinding.ActivityMainBinding
import com.vasilets.navigationcomponenttest.ui.upload.UploadFragment
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_upload,
                R.id.navigation_notifications,
                R.id.navigation_channel
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setBottomNav(navController)
        navView.setUpNewContentItem()
    }

    private fun BottomNavigationView.setUpNewContentItem() {
        itemIconTintList = null

        val menuView = getChildAt(0) as BottomNavigationMenuView
        val menuItem = menu.getItem(2)
        val menuItemView = menuView.getChildAt(2) as BottomNavigationItemView

        menuItem.isCheckable = false
        menuItemView.updateIconSize(80f, 64f)
    }

    private fun BottomNavigationItemView.updateIconSize(width: Float, height: Float) {
        findViewById<View>(com.google.android.material.R.id.navigation_bar_item_icon_view)
            .apply {
                val layoutParams: ViewGroup.LayoutParams = layoutParams
                val displayMetrics: DisplayMetrics = resources.displayMetrics

                layoutParams.height = TypedValue
                    .applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        height,
                        displayMetrics
                    ).toInt()

                layoutParams.width = TypedValue
                    .applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        width,
                        displayMetrics
                    ).toInt()

                setLayoutParams(layoutParams)
                requestLayout()
            }
    }

    private fun BottomNavigationView.setBottomNav(navController: NavController) {
        setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    NavigationUI.onNavDestinationSelected(
                        item,
                        navController
                    )
                }

                R.id.navigation_dashboard -> {
                    NavigationUI.onNavDestinationSelected(
                        item,
                        navController
                    )
                }

                R.id.navigation_upload -> {
                    navController.navigate(item.itemId)
                    true
                }

                R.id.navigation_notifications -> {
                    NavigationUI.onNavDestinationSelected(
                        item,
                        navController
                    )
                }

                R.id.navigation_channel -> {
                    NavigationUI.onNavDestinationSelected(
                        item,
                        navController
                    )
                }

                else -> {NavigationUI.onNavDestinationSelected(
                    item,
                    navController
                )}
            }
        }
        val weakReference = WeakReference(this)
        navController.addOnDestinationChangedListener(
            object : NavController.OnDestinationChangedListener {
                override fun onDestinationChanged(
                    controller: NavController,
                    destination: NavDestination,
                    arguments: Bundle?
                ) {
                    val view = weakReference.get()
                    if (view == null) {
                        navController.removeOnDestinationChangedListener(this)
                        return
                    }
                    view.menu.forEach { item ->
                        if (destination.matchDestination(item.itemId)) {
                            item.isChecked = true
                        }
                    }
                }
            })
    }

    internal fun NavDestination.matchDestination(@IdRes destId: Int): Boolean =
        hierarchy.any { it.id == destId }
}