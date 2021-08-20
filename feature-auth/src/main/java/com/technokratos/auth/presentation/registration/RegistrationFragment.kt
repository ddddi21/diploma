package com.technokratos.auth.presentation.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.coroutineScope
import com.example.feature_auth.R
import com.example.feature_auth.databinding.FragmentRegistrationBinding
import com.technokratos.auth.di.AuthFeatureComponent
import com.technokratos.auth.di.AuthFeatureKey
import com.technokratos.auth.presentation.auth.AuthViewState
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.di.FeatureUtils
import com.technokratos.common.utils.makeVisible
import com.technokratos.common.utils.setColor
import com.technokratos.common.utils.textChanges
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn

class RegistrationFragment : BaseFragment<RegistrationViewModel>() {

    private lateinit var binding: FragmentRegistrationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegistrationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun inject() {
        FeatureUtils.getFeature<AuthFeatureComponent>(this, AuthFeatureKey::class.java)
            .registrationComponentFactory()
            .create(this)
            .inject(this)
    }

    @ExperimentalCoroutinesApi
    override fun initViews() {
        initChangeTextListener()
        with(binding.registrationToolbar) {
            setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            setNavigationIcon(R.drawable.ic_navigate_back_arrow)
            setNavigationOnClickListener {
                viewModel.onBackToLoginScreenClicked()
            }
        }
        binding.registrationEnterButton.setOnClickListener {
            val email = binding.emailInputEditText.text.toString()
            val password = binding.passwordInputEditText.text.toString()
            val repeatPassword = binding.passwordRepeatInputEditText.text.toString()
            viewModel.onEnterButtonClicked(email, password, repeatPassword)
        }
    }

    override fun subscribe(viewModel: RegistrationViewModel) {
        viewModel.authViewState.observe(viewLifecycleOwner, this::render)
    }

    private fun render(state: AuthViewState) = with(binding) {
        with(state) {
            registrationEnterButton.isEnabled = isLoginButtonEnabled
            registrationErrorMessageTextView.text = loginErrorMessage
            registrationErrorMessageTextView.makeVisible(isLoginErrorMessageVisible)
            passwordInputEditText.setColor(ContextCompat.getColor(requireContext(), R.color.colorError), isSamePassword)
            passwordRepeatInputEditText.setColor(ContextCompat.getColor(requireContext(), R.color.colorError), isSamePassword)
            progressBar.makeVisible(isNeedToShowProgress)
            registrationEnterButton.makeVisible(!isNeedToShowProgress)
        }
    }

    @ExperimentalCoroutinesApi
    private fun initChangeTextListener() = with(binding) {
        combine(
            emailInputEditText.textChanges(),
            passwordInputEditText.textChanges(),
            passwordRepeatInputEditText.textChanges()
        ) { email, password, repeatPassword ->
            viewModel.onTextChanged(email.toString(), password.toString(), repeatPassword.toString())
        }.launchIn(viewLifecycleOwner.lifecycle.coroutineScope)
    }
}