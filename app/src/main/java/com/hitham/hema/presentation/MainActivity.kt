package com.hitham.hema.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.hitham.hema.domain.model.News
import com.hitham.hema.presentation.viewmodel.NewsViewModel
import com.hitham.hema.util.InternetConnection
import dagger.hilt.android.AndroidEntryPoint
import hitham.hema.R
import hitham.hema.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),RecyclerAdapter.MyViewHolder.OnClickListener{
    @Inject
    lateinit var connectivityManager: com.hitham.hema.util.ConnectivityManager
    private lateinit var internetConnection: InternetConnection
    private lateinit var actionBar: ActionBar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var viewModel: NewsViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar()
        initNavigationView()

        recyclerAdapter = RecyclerAdapter(this,this)
        recyclerView.adapter = recyclerAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        internetConnection= InternetConnection(this)
        internetConnection.observe(this) { isNetworkAvailable ->
            if (isNetworkAvailable) {
                viewModel.getNewsForCaching()
                viewModel.cachingList.observe(this) {
                    viewModel.deleteNews()
                    viewModel.insertNews(it)
                }
                viewModel.getNews()
                viewModel.newsList.observe(this) {
                    recyclerAdapter.setList(it as MutableList<News>)
                }
            } else {
                viewModel.getCachedNews()
                viewModel.cachedList.observe(this) {
                    recyclerAdapter.setList(it as MutableList<News>)
                    Log.d("shiiit", "onCreate: $it")
                }
                Toast.makeText(this, "no internet", Toast.LENGTH_LONG).show()
                Log.d("TAG", "onCreate: no internet ")
            }
        }
    }

    private fun initNavigationView() {
        var navView = binding.navigationView
        drawerLayout = binding.drawerLayout
        var toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.isDrawerIndicatorEnabled = true
        toggle.syncState()
    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        var fragment: Fragment? = null
//        when (item.title){
//            "about"->{
//                fragment = AboutFragment()
//            }
//        }
//        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
//        if (fragment != null) {
//            transaction.replace(binding.id, fragment)
//            transaction.addToBackStack(null)
//            transaction.commit()
//        }
//        drawerLayout.closeDrawer(GravityCompat.START)
//        Toast.makeText(this, "Working..${item.title}", Toast.LENGTH_SHORT).show()
//        return true
//    }

    private fun initToolbar() {
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)
//        actionBar.setDisplayHomeAsUpEnabled(true)
//        actionBar.setTitle(R.string.main_drawer)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

    var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, R.string.onBackPressed, Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed({ doubleBackToExitPressedOnce = false }, 2690)
    }
    override fun onDestroy() {
        viewModel.onDestroy()
        connectivityManager.unregisterConnectionObserver(this)
        super.onDestroy()
    }

    override fun onItemClicked(news: News) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("news",news)
        startActivity(intent)
    }
}