package com.vasilets.navigationcomponenttest

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.core.view.forEach
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.ref.WeakReference

object BottomNavConfigurator {

    private const val UPLOAD_ICON_WIDTH = 80f
    private const val UPLOAD_ICON_HEIGHT = 64f
    private const val INDEX_MENU_VIEW = 0
    private const val INDEX_UPLOAD_ITEM = 2

    fun BottomNavigationView.setUpNewContentItem() {
        itemIconTintList = null

        val menuView = getChildAt(INDEX_MENU_VIEW) as BottomNavigationMenuView
        val menuItem = menu.getItem(INDEX_UPLOAD_ITEM)
        val menuItemView = menuView.getChildAt(INDEX_UPLOAD_ITEM) as BottomNavigationItemView

        menuItem.isCheckable = false
        menuItemView.updateIconSize(UPLOAD_ICON_WIDTH, UPLOAD_ICON_HEIGHT)
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

    fun BottomNavigationView.setBottomNav(navController: NavController) {
        setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_upload -> {
                    navController.navigate(item.itemId)
                    true
                }
                else -> NavigationUI.onNavDestinationSelected(item, navController)
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

    fun BottomNavigationView.handleBottomNavVisibility(navController: NavController) {
        navController.addOnDestinationChangedListener { _, _, bundle ->
            visibility = if (bundle?.getBoolean("overlay", false) == true) {
                View.GONE
            } else {
                View.VISIBLE
            }
        }
    }

    internal fun NavDestination.matchDestination(@IdRes destId: Int): Boolean =
        hierarchy.any { it.id == destId }
}