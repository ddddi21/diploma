package com.example.feature_main.presentation.nestedFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feature_main.R
import com.example.feature_main.databinding.PatientsListFragmentBinding
import com.example.feature_main.di.PatientFeatureComponent
import com.example.feature_main.di.PatientFeatureKey
import com.example.feature_main.presentation.diagnos.CollectionMenuItemMode
import com.example.feature_main.presentation.diagnos.DiagnosCheckViewModel
import com.example.feature_main_api.domain.model.ViewPagerFragmentType
import com.medicalSystem.common.base.BaseFragment
import com.medicalSystem.common.base.adapter.BaseAdapter
import com.medicalSystem.common.di.FeatureUtils
import com.medicalSystem.common.utils.changeVisibility
import com.medicalSystem.common.utils.setDivider

private const val GRID_LAYOUT_SPAN_COUNT = 3

private const val VIEW_PAGER_FRAGMENT_TYPE_KEY = "VIEW_PAGER_FRAGMENT_TYPE_KEY"

class PatientsListsFragment : BaseFragment<PatientsListsViewModel>() {

    companion object {
        fun getInstance(viewPagerFragmentType: ViewPagerFragmentType) = PatientsListsFragment().apply {
            arguments = bundleOf(VIEW_PAGER_FRAGMENT_TYPE_KEY to viewPagerFragmentType)
        }
    }

    private lateinit var binding: PatientsListFragmentBinding

    private val diagnosCheckViewModel by viewModels<PatientsListsViewModel>({ requireParentFragment() })


    private val linearPatientsAdapter = BaseAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PatientsListFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun inject() {
        FeatureUtils.getFeature<PatientFeatureComponent>(this, PatientFeatureKey::class.java)
            .willWatchLaterFilmsFactory()
            .create(this)
            .inject(this)
    }

    override fun initViews() {
        setLinearLayout()
        setSwipeOnRefreshListener()
    }

    override fun subscribe(viewModel: PatientsListsViewModel) {
//        diagnosCheckViewModel.collectionMenuItemMode.observe(viewLifecycleOwner) { itemMode ->
//            when (itemMode) {
//                CollectionMenuItemMode.LINEAR_LIST -> setLinearLayout()
//            }
//        }
//        viewModel.gridList.observe(viewLifecycleOwner) { list ->
//            gridFilmsAdapter.update(list)
//        }

        viewModel.linearList.observe(viewLifecycleOwner) { list ->
            linearPatientsAdapter.update(list)
            binding.patientsRecyclerView.changeVisibility(list.isNotEmpty())
            binding.noPatientTextView.changeVisibility(list.isEmpty())
        }
    }

//    private fun setGridLayout() = with(binding.patientsRecyclerView) {
//        layoutManager = GridLayoutManager(context, GRID_LAYOUT_SPAN_COUNT)
//        adapter = gridFilmsAdapter
//        removeItemDecorations()
//    }

    private fun setLinearLayout() = with(binding.patientsRecyclerView) {
        layoutManager = LinearLayoutManager(context)
        adapter = linearPatientsAdapter
        setDivider(R.drawable.list_item_divider)
    }

    private fun setSwipeOnRefreshListener() {
        binding.swipeToRefreshFilmsList.setOnRefreshListener {
            binding.swipeToRefreshFilmsList.isRefreshing = false
            viewModel.onRefreshSwiped()
        }
    }
}