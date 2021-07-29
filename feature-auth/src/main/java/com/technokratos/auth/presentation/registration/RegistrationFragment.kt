package com.technokratos.auth.presentation.registration

import com.technokratos.auth.di.registration.RegistrationFeatureKey
import com.technokratos.auth.di.registration.RegistrationFeatureComponent
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.di.FeatureUtils

class RegistrationFragment : BaseFragment<RegistrationViewModel>() {

    override fun inject() {
        FeatureUtils.getFeature<RegistrationFeatureComponent>(this, RegistrationFeatureKey::class.java)
            .registrationComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun initViews() {
        TODO("Not yet implemented")
    }

    override fun subscribe(viewModel: RegistrationViewModel) {
        TODO("Not yet implemented")
    }
}