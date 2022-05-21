package cl.cdum.therickandmorty.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import cl.cdum.therickandmorty.R
import cl.cdum.therickandmorty.api.character.CharacterDetailData
import cl.cdum.therickandmorty.databinding.FragmentCharacterDetailBinding
import cl.cdum.therickandmorty.utils.Resource
import cl.cdum.therickandmorty.utils.functions.dateTimeFormat
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {
    private val args: CharacterDetailFragmentArgs by navArgs()

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    private val characterDetailViewModel: CharacterDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupListener()
        initObservers()
    }

    private fun setupView() {
        characterDetailViewModel.getCharacterDetail(args.identifier)
    }

    private fun setupListener() {
        binding.apply {
            btnRetry.setOnClickListener {
                characterDetailViewModel.getCharacterDetail(args.identifier)
            }
        }
    }

    private fun initObservers() {
        characterDetailViewModel.liveListData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Error -> showErrorView(result.error)
                is Resource.Loading -> showLoadingView()
                is Resource.Success -> showSuccessView(result.data)
            }
        })
    }

    private fun showErrorView(error: Throwable?) {
        binding.apply {
            progressBar.isVisible = false
            layoutError.isVisible = true

            tvError.text = error?.localizedMessage
        }
    }

    private fun showLoadingView() {
        binding.apply {
            progressBar.isVisible = true
            layoutError.isVisible = false
            svMain.isVisible = false
        }
    }

    private fun showSuccessView(data: CharacterDetailData?) {
        binding.apply {
            progressBar.isVisible = false
            svMain.isVisible = true

            data?.apply {
                Glide.with(requireContext())
                    .load(image)
                    .into(ivImage)

                val status = getString(R.string.status_item, status)
                val species = getString(R.string.species_item, species)
                val gender = getString(R.string.gender_item, gender)
                val origin = getString(R.string.origin_item, origin?.name)
                val locationName = getString(R.string.location_name_item, location?.name)

                val localTime = dateTimeFormat(created!!)
                val created = getString(R.string.created_item, localTime)

                tvName.text = name
                tvStatus.text = status
                tvSpecies.text = species
                tvGender.text = gender
                tvOrigin.text = origin
                tvLocationName.text = locationName
                tvCreated.text = created
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}