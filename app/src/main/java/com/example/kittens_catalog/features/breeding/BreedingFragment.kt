package com.example.kittens_catalog.features.breeding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.kittens_catalog.databinding.FragmentBreedingBinding
import com.example.kittens_catalog.features.main.MainActivity
import com.example.kittens_catalog.features.main.MainViewModel

class BreedingFragment : Fragment() {
    private var _binding: FragmentBreedingBinding? = null
    private val  binding get() = _binding!!
    private val viewModel: BreedingViewModel by viewModels()
    private val sharedViewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreedingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logOutButton.setOnClickListener {
            viewModel.logOut()
            sharedViewModel.onStart()
        }
    }
}