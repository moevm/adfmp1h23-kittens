package com.example.kittens_catalog.features.breeding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.kittens_catalog.R
import com.example.kittens_catalog.databinding.FragmentBreedingBinding
import com.example.kittens_catalog.features.base.BaseFragment
import com.example.kittens_catalog.features.main.MainFragmentDirections
import com.example.kittens_catalog.features.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BreedingFragment : BaseFragment() {
    private var _binding: FragmentBreedingBinding? = null
    private val  binding get() = _binding!!

    private val viewModel: BreedingViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreedingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.doRefresh()
        subscribeUi()
        binding.logOutButton.setOnClickListener {
            viewModel.logOut()
            mainViewModel.logOut().observe(viewLifecycleOwner) {
                if (!it) {
                    navController.navigate(R.id.mainFragment)
                } else {
                    navigate(R.id.breeding_fragment, MainFragmentDirections.actionMainFragmentToBreedingFragment())
                }
            }
        }
    }
    fun subscribeUi() {
        mainViewModel.personalData.observe(viewLifecycleOwner) {
            if (it != null) {
                Glide.with(this).load(it.picture).into(binding.avatar);
                binding.username.text = "${it?.firstName} ${it?.lastName}"
            }
        }
    }
}