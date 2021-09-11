package com.outside.oshabelist.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.outside.oshabelist.databinding.FragmentHomeBinding
import com.outside.oshabelist.edit.ThemeListActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), HomeNavigator {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            viewModel = homeViewModel
            lifecycleOwner = this@HomeFragment
        }
        homeViewModel.navigator = this
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showNextPage() {
        startActivity(ThemeListActivity.createIntent(requireContext()))
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}