package com.example.feature_main.presentation.diagnos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.feature_main.R
import com.example.feature_main.databinding.KidneyCheckFragmentBinding
import com.example.feature_main.di.PatientFeatureComponent
import com.example.feature_main.di.PatientFeatureKey
import com.medicalSystem.common.base.BaseFragment
import com.medicalSystem.common.di.FeatureUtils
import com.medicalSystem.common.utils.makeGone
import com.medicalSystem.common.utils.makeVisible
import com.medicalSystem.common.utils.textChanges
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn

class KidneyCheckFragment : BaseFragment<DiagnosCheckViewModel>() {

    private lateinit var binding: KidneyCheckFragmentBinding

    private lateinit var menu: MenuItem

    private var isCheckAvailable = false
    private var isTextDone = false
    private var isRadioButtonChecked = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = KidneyCheckFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val fragmentWillWatch = WillWatchLaterFilmsFragment.getInstance(ViewPagerFragmentType.WILL_WATCH)
//        val fragmentWatched = WillWatchLaterFilmsFragment.getInstance(ViewPagerFragmentType.WATCHED)
//        val collectionList = listOf(
//            resources.getString(R.string.collection_will_watch_tab) to fragmentWillWatch,
//            resources.getString(R.string.collection_already_watched_tab) to fragmentWatched
//        )
//        binding.viewPager.adapter = CollectionAdapter(
//            collectionList = collectionList,
//            fragment = this
//        )
//        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
//            tab.text = collectionList[position].first
//        }.attach()
    }

    override fun inject() {
        FeatureUtils.getFeature<PatientFeatureComponent>(this, PatientFeatureKey::class.java)
            .patientComponentFactory()
            .create(this)
            .inject(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun initViews() {
        initChangeTextListener()
        binding.back.setOnClickListener {
            viewModel.onBackClicked()
        }
    }

    override fun subscribe(viewModel: DiagnosCheckViewModel) {

        binding.kidneyCheckFragmentPeNo.setOnClickListener { onRadioButtonClicked(it) }
        binding.kidneyCheckFragmentPeYes.setOnClickListener { onRadioButtonClicked(it) }

        binding.kidneyCheckFragmentCheckResult.setOnClickListener {
            it.makeGone()
            binding.kidneyCheckFragmentResult.makeVisible()
        }

    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.kidney_check_fragment_pe_yes ->
                    if (checked) {
                        isRadioButtonChecked = true
                    }
                R.id.kidney_check_fragment_pe_no ->
                    if (checked) {
                        isRadioButtonChecked = true
                    }
            }

            if (isTextDone) {
                binding.kidneyCheckFragmentCheckResult.isEnabled = true
            }
        }
    }

    @ExperimentalCoroutinesApi
    private fun initChangeTextListener() = with(binding) {
        combine(
            kidneyCheckFragmentSgInput.textChanges(),
            kidneyCheckFragmentHemoInput.textChanges(),
            kidneyCheckFragmentBpInput.textChanges(),
            kidneyCheckFragmentAlInput.textChanges(),
            kidneyCheckFragmentBgrInput.textChanges(),
        ) { sg, hemo, bp, al, bgr ->
//            viewModel.onTextChanged(sg.toString(),hemo.toString(), bp.toString(), al.toString(), bgr.toString())
            if (!sg.isNullOrEmpty() && !hemo.isNullOrEmpty() && !bp.isNullOrEmpty() && !al.isNullOrEmpty() && !bgr.isNullOrEmpty() && isRadioButtonChecked) {
                isCheckAvailable = true
                isTextDone = true
                kidneyCheckFragmentCheckResult.isEnabled = true
            }
        }.launchIn(viewLifecycleOwner.lifecycle.coroutineScope)
    }
}

class CollectionAdapter(
    private val collectionList: List<Pair<String, Fragment>>,
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = collectionList.size

    override fun createFragment(position: Int) = collectionList[position].second
}