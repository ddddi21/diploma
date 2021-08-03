package com.technokratos.app.presentation

import android.content.Context
import android.content.Intent
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.navigation.navigation.NavControllerProvider
import com.technokratos.app.R
import com.technokratos.app.di.deps.findComponentDependencies
import com.technokratos.app.di.main.MainComponent
import com.technokratos.common.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var navigator: NavControllerProvider

    private var navController = NavController(this)

    override fun layoutResource() = R.layout.activity_launch

    override fun inject() {
        MainComponent
            .init(this, findComponentDependencies())
            .inject(this)
    }

    override fun initViews() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.launch_nav_host_fragment) as NavHostFragment? ?: return
        navController = navHostFragment.navController
        navigator.attachNavController(navController, R.navigation.auth_nav_graph)
    }

    override fun subscribe(viewModel: MainViewModel) {
        // TODO
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.detachNavController(navController)
    }
}