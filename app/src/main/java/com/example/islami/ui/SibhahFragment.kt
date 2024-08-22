package com.example.islami.ui


import android.os.Bundle
import android.view.View
import com.example.islami.R
import com.example.islami.base.BaseFragment
import com.example.islami.databinding.FragmentSibhahBinding

class SibhahFragment : BaseFragment<FragmentSibhahBinding>(FragmentSibhahBinding::inflate) {

    private var doaaList = mutableListOf<String>()
    private var tasbehCounter = 1
    private var currentRotation = 0f
    private var listCounter = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDoaaList(doaaList)
        onClicks()
    }

    override fun onClicks() {
        binding.imgBodyOfSebha.setOnClickListener {
            rotateImage()
            setCountWithDoaa()
        }
    }

    private fun setDoaaList(doaaList: MutableList<String>) {
        doaaList.addAll(
            listOf(
                getString(R.string.alhamdulillah),
                getString(R.string.la_elah_ela_allah),
                getString(R.string.allah_akbar),
                getString(R.string.sobhan_allah)
            )
        )
    }

    private fun setTasbehCounter() {
        binding.tvCounter.text = tasbehCounter.toString()
    }

    private fun setCountWithDoaa() {
        if (tasbehCounter <= 33) {
            setTasbehCounter()
            tasbehCounter++
        } else {
            tasbehCounter = 0
            if (listCounter < doaaList.size) {
                binding.tvTasbih.text = doaaList[listCounter]
                setTasbehCounter()
                listCounter++
            } else {
                listCounter = 0
                binding.tvTasbih.text = doaaList[listCounter]
                setTasbehCounter()
            }
        }
    }

    private fun rotateImage() {
        currentRotation += 20f
        if (currentRotation >= 360) {
            currentRotation = 0f
        }
        binding.imgBodyOfSebha.animate()
            .rotation(currentRotation)
            .setDuration(500)
            .start()
    }
}