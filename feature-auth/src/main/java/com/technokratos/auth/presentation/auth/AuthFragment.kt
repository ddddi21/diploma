package com.technokratos.auth.presentation.auth

import com.technokratos.auth.di.auth.AuthFeatureKey
import com.technokratos.auth.di.auth.AuthFeatureComponent
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.di.FeatureUtils

class AuthFragment : BaseFragment<AuthViewModel>() {

    override fun inject() {
        FeatureUtils.getFeature<AuthFeatureComponent>(this, AuthFeatureKey::class.java)
            .authComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun initViews() {
        TODO("Not yet implemented")
    }

    override fun subscribe(viewModel: AuthViewModel) {
        TODO("Not yet implemented")
    }
}