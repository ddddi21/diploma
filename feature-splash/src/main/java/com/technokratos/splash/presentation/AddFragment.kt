package com.technokratos.splash.presentation

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.feature_film_details.databinding.FilmDetailsFragmentBinding
import com.technokratos.splash.R

// пишу пока в данном фрагменте, чтобы просто отслеживать верстку и логику ее поведения. потом создам отдельный
// фрагмент в нужном модуле с di, vm и тд
class AddFragment : Fragment() {

    private lateinit var binding: FilmDetailsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FilmDetailsFragmentBinding.inflate(layoutInflater)
        with(binding) {
            willWatchButton.setOnClickListener {
                setSelectedWatchButton(willWatchButton)
            }
            watchedButton.setOnClickListener {
                setSelectedWatchButton(watchedButton)
            }
        }
        return binding.root
    }

    private fun setSelectedWatchButton(selectedButton: Button){
        var button: Button ?= null
        with(binding) {
            with(watchedButton) {
                setTextColor(ContextCompat.getColor(requireContext(), R.color.lightGrey))
                background.mutate().setTint(ContextCompat.getColor(requireContext(), R.color.white))
            }
            with(willWatchButton) {
                setTextColor(ContextCompat.getColor(requireContext(), R.color.lightGrey))
                background.mutate().setTint(ContextCompat.getColor(requireContext(), R.color.white))
            }
        }
        button?.backgroundTintList = null
        button = selectedButton
        with(button) {
            setTextColor(ContextCompat.getColor(requireContext(), R.color.blueAccent))
            background.mutate().setTint(ContextCompat.getColor(requireContext(), R.color.loginEnterButtonLightGrey))
        }
    }
}
// не ругайся на кривой фрагмент, это просто чтобы bottom nav протестить
// я потом перемещу в другой модуль и сделаю нормально в отдельном пр