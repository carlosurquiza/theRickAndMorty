package cl.cdum.therickandmorty.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cl.cdum.therickandmorty.R
import cl.cdum.therickandmorty.api.character.CharacterListData
import cl.cdum.therickandmorty.api.character.Result
import cl.cdum.therickandmorty.databinding.FragmentCharacterListBinding
import cl.cdum.therickandmorty.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CharacterListFragment :
    Fragment(R.layout.fragment_character_list),
    CharacterListAdapter.OnItemClickListener {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    private val characterListViewModel: CharacterListViewModel by viewModels()
    private lateinit var characterListAdapter: CharacterListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupListener()
        initObservers()
    }

    private fun setupView() {
        characterListAdapter = CharacterListAdapter(this)

        binding.apply {
            rvCharacter.apply {
                adapter = characterListAdapter
                layoutManager = LinearLayoutManager(context)
            }

            srLayout.setOnRefreshListener {
                characterListViewModel.getCharacterList()
            }
        }

        characterListViewModel.getCharacterList()
    }

    private fun setupListener() {
        binding.apply {
            btnRetry.setOnClickListener {
                characterListViewModel.getCharacterList()
            }
        }
    }

    private fun initObservers() {
        characterListViewModel.liveListData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Error -> showErrorView(result.error)
                is Resource.Loading -> showLoadingView()
                is Resource.Success -> showSuccessView(result.data)
            }
        })

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            characterListViewModel.event.collect { event ->
                when (event) {
                    is CharacterEvent.NavigateToDetailScreen -> {
                        val action =
                            CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                                identifier = event.identifier
                            )
                        findNavController().navigate(action)
                    }
                }
            }
        }
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
        }
    }

    private fun showSuccessView(data: CharacterListData?) {
        binding.apply {
            progressBar.isVisible = false
            srLayout.isRefreshing = false

            characterListAdapter.submitList(data?.result)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(result: Result) {
        characterListViewModel.onItemSelected(result.id)
    }
}