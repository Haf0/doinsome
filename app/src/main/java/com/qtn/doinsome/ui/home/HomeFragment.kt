package com.qtn.doinsome.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.qtn.doinsome.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentHomeBinding.inflate(inflater, container, false).root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //binding.groupButton.check(binding.button1.id)

    }

}