package com.technokratos.app.presentation

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.navigation.navigation.NavControllerProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.technokratos.app.R
import com.technokratos.app.di.deps.findComponentDependencies
import com.technokratos.app.di.main.MainComponent
import com.technokratos.common.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    @Inject
    lateinit var navigator: NavControllerProvider

    private var navController = NavController(this)

    override fun layoutResource() = R.layout.activity_main

    override fun inject() {
        MainComponent
            .init(this, findComponentDependencies())
            .inject(this)
    }

    override fun initViews() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment? ?: return
        navController = navHostFragment.navController
        navigator.attachNavController(navController, R.navigation.main_nav_graph)
        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        NavigationUI.setupWithNavController(bottomNavView, navController)
        viewModel.onSplashAnimationFinished()
    }
    // вынесу мейн штуки в отдельный модуль в другом пр и там же буду настраивать visibility y bottomNav

    override fun subscribe(viewModel: MainViewModel) {
        // TODO
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.detachNavController(navController)
    }
}