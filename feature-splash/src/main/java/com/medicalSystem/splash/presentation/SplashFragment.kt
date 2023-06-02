package com.medicalSystem.splash.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.medicalSystem.common.base.BaseFragment
import com.medicalSystem.common.di.FeatureUtils
import com.medicalSystem.splash.databinding.SplashFragmentBinding
import com.medicalSystem.splash.di.SplashFeatureKey
import com.medicalSystem.splash.di.SplashFeatureComponent

private const val FIRST_TRANSLATION_X_VALUE = 40f
private const val SECOND_TRANSLATION_X_VALUE = -55f
private const val THIRD_TRANSLATION_X_VALUE = 1f
private const val ANIMATION_DURATION = 800L

class SplashFragment : BaseFragment<SplashViewModel>() {

    private lateinit var binding: SplashFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = SplashFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun inject() {
        FeatureUtils.getFeature<SplashFeatureComponent>(this, SplashFeatureKey::class.java)
            .splashComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun initViews() {
        startSplashAnimation()
    }

    private fun startSplashAnimation() {
        val endActionTwo = Runnable {
            binding.title.animate()
                .translationX(THIRD_TRANSLATION_X_VALUE)
                .setDuration(ANIMATION_DURATION)
                .withEndAction(viewModel::onAnimationFinished)
        }

        val endActionOne = Runnable {
            binding.title.animate()
                .translationX(SECOND_TRANSLATION_X_VALUE)
                .setDuration(ANIMATION_DURATION)
                .withEndAction(endActionTwo)
        }

        binding.title.animate()
            .translationX(FIRST_TRANSLATION_X_VALUE)
            .setDuration(ANIMATION_DURATION)
            .withEndAction(endActionOne)
    }

    override fun subscribe(viewModel: SplashViewModel) {
        // TODO
    }
}