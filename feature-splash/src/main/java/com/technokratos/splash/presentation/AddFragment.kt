package com.technokratos.splash.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.feature_film_details.databinding.FilmDetailsFragmentBinding
import com.technokratos.common.utils.setChip

// пишу пока в данном фрагменте, чтобы просто отслеживать верстку и логику ее поведения. потом создам отдельный
// фрагмент в нужном модуле с di, vm и тд
class AddFragment : Fragment() {

    private lateinit var binding: FilmDetailsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FilmDetailsFragmentBinding.inflate(layoutInflater)
        setChips()
        binding.addToWillWatchButton.setOnClickListener { addToWillWatchButton ->
            addToWillWatchButton.visibility = View.GONE
            binding.watchedChipGroup.visibility = View.VISIBLE
        }
        return binding.root.rootView
    }

    private fun setChips() {
        binding.genreChipGroup.setChip("Test") // будем брать текст из вью модели
        binding.genreChipGroup.setChip("Testttt")
        binding.genreChipGroup.setChip("Testklmbklml")
        binding.genreChipGroup.setChip("Testggg")
        binding.genreChipGroup.setChip("Test")
        binding.genreChipGroup.setChip("Test")
        binding.genreChipGroup.setChip("Test")
    } // временный вариант
}
// не ругайся на кривой фрагмент, это просто чтобы bottom nav протестить
// я потом перемещу в другой модуль и сделаю нормально в отдельном пр