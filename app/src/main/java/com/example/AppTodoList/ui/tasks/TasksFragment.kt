package com.example.AppTodoList.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.AppTodoList.R
import com.example.AppTodoList.databinding.TasksFragmentBinding
import com.example.AppTodoList.utils.Resource
import com.example.AppTodoList.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class TasksFragment : Fragment(), ItemsAdapter.ItemListener {

    private var binding: TasksFragmentBinding by autoCleared()
    private val viewModel: TasksViewModel by viewModels()
    private lateinit var adapter: ItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TasksFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.create_task_action)
        }
    }

    private fun setupRecyclerView() {

        adapter = ItemsAdapter(this)
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter
    }

    private fun setupObservers() {

        viewModel.characters.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Timber.d(it.data.toString())
                    binding.progressBar.visibility = View.GONE
                    binding.container.visibility = View.VISIBLE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }

                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.container.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

                Resource.Status.LOADING ->{
                    binding.progressBar.visibility = View.VISIBLE
                    binding.container.visibility = View.GONE
                }
            }
        })
    }

    override fun editItemOnCLick(characterId: String) {
        findNavController().navigate(
            R.id.edit_task_action,
            bundleOf("id" to characterId)
        )
    }
}
