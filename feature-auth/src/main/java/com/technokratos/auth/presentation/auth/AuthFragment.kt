package com.technokratos.auth.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.feature_auth.databinding.FragmentLoginBinding
import com.technokratos.auth.di.AuthFeatureKey
import com.technokratos.auth.di.AuthFeatureComponent
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.di.FeatureUtils

class AuthFragment : BaseFragment<AuthViewModel>() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun inject() {
        FeatureUtils.getFeature<AuthFeatureComponent>(this, AuthFeatureKey::class.java)
            .authComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun initViews() {
        with(binding) {
            registrationTextView.setOnClickListener {
                viewModel.onRegistrationClicked()
            }
            loginEnterButton.setOnClickListener {
                viewModel.onEnterButtonClicked("test@gmail.com", "12345") // временный вариант, потом дата биндинг подключу
            }
        }
    }

    override fun subscribe(viewModel: AuthViewModel) {
        // TODO
    }
}