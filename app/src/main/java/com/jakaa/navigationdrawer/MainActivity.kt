package com.jakaa.navigationdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    //Step: Declare and initialize drawer Layout
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)

        //Step 2: Initialize toolbar and setSupportActionBar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        //Step 3: Extend and Initialize Navigation Drawer
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        //Step 4: Set the Toggle (Responsible interaction between navigation drawer and App actionbar)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_nav,
            R.string.close_nav
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        //Step 7: Set default Fragment
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
            navigationView.setCheckedItem(R.id.nav_home)
        }

    }

    //Step 8: Create item selected method.
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
      when(item.itemId){
          R.id.nav_home -> replaceFragment(HomeFragment())
          R.id.nav_info -> replaceFragment(InfoFragment())
          R.id.nav_share -> replaceFragment(ShareFragment())
          R.id.nav_settings -> replaceFragment(SettingsFragment())
          R.id.nav_logout -> Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()

      }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    //Step 5: Create Replace Fragment functions
    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    //Step 6: Override onBackPressed method
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }

    }


}























































































