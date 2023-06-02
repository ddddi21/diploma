package com.example.feature_main.presentation.diagnos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feature_main.R
import com.example.feature_main.databinding.MainDiagnosCheckFragmentBinding
import com.example.feature_main.di.PatientFeatureComponent
import com.example.feature_main.di.PatientFeatureKey
import com.medicalSystem.common.base.BaseFragment
import com.medicalSystem.common.base.adapter.BaseAdapter
import com.medicalSystem.common.di.FeatureUtils
import com.medicalSystem.common.utils.changeVisibility
import com.medicalSystem.common.utils.setDivider

class MainDiagnosCheckListFragment: BaseFragment<DiagnosCheckViewModel>() {

    private lateinit var binding: MainDiagnosCheckFragmentBinding

    private val diagnosCheckViewModel by viewModels<DiagnosCheckViewModel>({ requireParentFragment() })

    private val linearIllnessesAdapter = BaseAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = MainDiagnosCheckFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun inject() {
        FeatureUtils.getFeature<PatientFeatureComponent>(this, PatientFeatureKey::class.java)
            .patientComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun initViews() {
        setLinearLayout()
        setSwipeOnRefreshListener()
    }

    override fun subscribe(viewModel: DiagnosCheckViewModel) {
        viewModel.linearList.observe(viewLifecycleOwner) { list ->
            linearIllnessesAdapter.update(list)
            binding.diagnosiesRecyclerView.changeVisibility(list.isNotEmpty())
        }
    }

//    private fun setGridLayout() = with(binding.patientsRecyclerView) {
//        layoutManager = GridLayoutManager(context, GRID_LAYOUT_SPAN_COUNT)
//        adapter = gridFilmsAdapter
//        removeItemDecorations()
//    }

    private fun setLinearLayout() = with(binding.diagnosiesRecyclerView) {
        layoutManager = LinearLayoutManager(context)
        adapter = linearIllnessesAdapter
        setDivider(R.drawable.list_item_divider)
    }

    private fun setSwipeOnRefreshListener() {
        binding.swipeToRefreshFilmsList.setOnRefreshListener {
            binding.swipeToRefreshFilmsList.isRefreshing = false
            viewModel.onRefreshSwiped()
        }
    }
}