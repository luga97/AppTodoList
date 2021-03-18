package com.example.AppTodoList.ui.edittask

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
import com.example.AppTodoList.databinding.EditItemFragmentBinding
import com.example.AppTodoList.utils.Resource
import com.example.AppTodoList.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragmento para editar las tareas ya existentes
 * @author Luis Garcia
 */
@AndroidEntryPoint
class EditTaskFragment : Fragment() {

    private var binding: EditItemFragmentBinding by autoCleared()
    private val viewModel: EditTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditItemFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val taskId: String = arguments?.getString("id")?.apply {
            if(this != "0" ) viewModel.start(this)
        } ?: "0"

        viewModel.task.observe(viewLifecycleOwner){ task ->
            binding.titleInput.setText(task.title)
            binding.descriptionInput.setText(task.description)
        }
        binding.submitButton.setOnClickListener {
            setTask(taskId)
        }
    }

    private fun setTask(id: String) {
        viewModel.setTask(Task(
                id,
                binding.titleInput.text.toString(),
                binding.descriptionInput.text.toString()
        )).observe(viewLifecycleOwner) {
            when(it.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.container.visibility = View.GONE
                }

                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.container.visibility = View.VISIBLE
                    Toast.makeText(context, "Se edito la tarea exitosamente", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.edit_to_tasks)
                }

                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.container.visibility = View.VISIBLE
                    Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}
