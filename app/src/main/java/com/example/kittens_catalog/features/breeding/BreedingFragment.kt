package com.example.kittens_catalog.features.breeding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.kittens_catalog.R
import com.example.kittens_catalog.databinding.FragmentAuthBinding
import com.example.kittens_catalog.databinding.FragmentBreedingBinding
import com.example.kittens_catalog.features.base.BaseFragment
import com.example.kittens_catalog.features.main.MainFragmentDirections
import com.example.kittens_catalog.features.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BreedingFragment : BaseFragment<FragmentBreedingBinding>(R.layout.fragment_breeding) {

    private val viewModel: BreedingViewModel by viewModels()
    override val binding: FragmentBreedingBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseViewModel.doRefresh()
        subscribeUi()
        binding.logOutButton.setOnClickListener {
            viewModel.logOut()
            baseViewModel.logOut().observe(viewLifecycleOwner) {
                if (!it) {
                    navController.navigate(R.id.mainFragment)
                } else {
                    navigate(R.id.breeding_fragment, MainFragmentDirections.actionMainFragmentToBreedingFragment())
                }
            }
        }
    }

    private fun subscribeUi() {
        baseViewModel.personalData.observe(viewLifecycleOwner) {
            if (it != null) {
                Glide.with(this).load(it.picture).into(binding.avatar);
                binding.username.text = "${it?.firstName} ${it?.lastName}"
            }
        }
        binding.kittensCatalog.setOnClickListener {
            navigate(R.id.breeding_fragment, BreedingFragmentDirections.actionBreedingFragmentToKittenListFragment())
        }
        binding.myCatsButton.setOnClickListener {
            navigate(R.id.breeding_fragment, BreedingFragmentDirections.actionBreedingFragmentToKittenListFragment(true))
        }
    }

}