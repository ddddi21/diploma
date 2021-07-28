package com.technokratos.splash.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.di.FeatureUtils
import com.technokratos.splash.databinding.SplashFragmentBinding
import com.technokratos.splash.di.SplashFeatureApi
import com.technokratos.splash.di.SplashFeatureComponent

class SplashFragment : BaseFragment<SplashViewModel>() {

    private lateinit var binding: SplashFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = SplashFragmentBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun initViews() {

        val endActionTwo = Runnable {
            binding.eyeIcon.animate()
                .translationX(1f)
                .setDuration(800)
        }

        val endActionOne = Runnable {
            binding.eyeIcon.animate()
                .translationX(-55f)
                .setDuration(800)
                .withEndAction(endActionTwo)
        }

        binding.eyeIcon.animate()
            .translationX(40f)
            .setDuration(800)
            .withEndAction(endActionOne)
    }

    override fun inject() {
        FeatureUtils.getFeature<SplashFeatureComponent>(this, SplashFeatureApi::class.java)
            .splashComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun subscribe(viewModel: SplashViewModel) {
    }
}