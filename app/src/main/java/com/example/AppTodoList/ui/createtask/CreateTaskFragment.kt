package com.example.AppTodoList.ui.createtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.AppTodoList.R
import com.example.AppTodoList.data.entities.Task
import com.example.AppTodoList.databinding.CreateTaskFragmentBinding
import com.example.AppTodoList.utils.Resource
import com.example.AppTodoList.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

/**
 * fragmento para crear tareas nuevas
 * @author Luis Garcia
 */
@AndroidEntryPoint
class CreateTaskFragment : Fragment() {

    private var binding: CreateTaskFragmentBinding by autoCleared()
    private val viewModel: CreateTaskViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = CreateTaskFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitButton.setOnClickListener {
            createTask()
        }
    }

    private fun createTask() {
        viewModel.createTask(Task(
                title = binding.titleInput.text.toString(),
                description =  binding.descriptionInput.text.toString()
        )).observe(viewLifecycleOwner) {
            when(it.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.containerCt.visibility = View.GONE
                }

                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.containerCt.visibility = View.VISIBLE
                    Toast.makeText(context, "Se añadío la tarea exitosamente", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.create_to_tasks)
                }

                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.containerCt.visibility = View.VISIBLE
                    Toast.makeText(context,it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}