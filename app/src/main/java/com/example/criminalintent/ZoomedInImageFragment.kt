package com.example.criminalintent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.example.criminalintent.databinding.FragmentZoomedInImageBinding
import com.example.criminalintent.utils.getScaledBitmap
import java.io.File

class ZoomedInImageFragment : DialogFragment() {

    private val args: ZoomedInImageFragmentArgs by navArgs()
    private var _binding: FragmentZoomedInImageBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot acces binding, bcoz it is null"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentZoomedInImageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updatePhoto(args.photoFileName)
    }

    private fun updatePhoto(photoFileName: String) {
        val photoFile = File(requireContext().applicationContext.filesDir, photoFileName)
        if (photoFile.exists()) {
            binding.ivZoomedInImage.doOnLayout { measuredView ->
                val scaledBitmap = getScaledBitmap(
                    photoFile.path,
                    measuredView.width,
                    measuredView.height
                )
                binding.ivZoomedInImage.setImageBitmap(scaledBitmap)
            }
        }
    }
}