package com.example.islami.ui.radio

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.islami.R
import com.example.islami.base.BaseFragment
import com.example.islami.databinding.FragmentRadioBinding
import com.example.islami.pojo.Radio
import com.example.islami.util.AR_LANGUAGE
import com.example.islami.util.Resource
import com.example.islami.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RadioFragment : BaseFragment<FragmentRadioBinding>(FragmentRadioBinding::inflate) {

    private val viewModel: RadioViewModel by viewModels()
    private var isprepared = false
    private val mediaPlayer = MediaPlayer()
    private var rList: List<Radio>? = null
    private var possition = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveStream(AR_LANGUAGE)
        onClicks()
    }

    override fun onClicks() {
        binding.apply {
            btnPlay.setOnClickListener {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                    setViews(rList!!)
                } else {
                    observe()
                    mediaPlayer.start()
                }

                btnNext.setOnClickListener {
                    if (isprepared && possition < rList!!.size - 1) {
                        possition++
                        observe()
                        mediaPlayer.start()
                        initMediaPlayer(rList!![possition].url)
                        showToast("${rList!!.size} / $possition")
                    } else {
                        showToast(getString(R.string.end))
                    }
                }
                btnPrevious.setOnClickListener {
                    if (isprepared && possition > 0) {
                        possition--
                        observe()
                        mediaPlayer.start()
                        initMediaPlayer(rList!![possition].url)
                        showToast("${rList!!.size} / $possition")
                    } else {
                        showToast(getString(R.string.start))
                    }
                }
            }
        }
    }

    private fun observe() {
        viewModel.radioLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    rList = it.data!!
                    initMediaPlayer(rList!![possition].url)
                }

                is Resource.Error -> {
                    it.message?.let { message ->
                        showToast(message)
                    }
                }
            }
        }
    }


    private fun initMediaPlayer(url: String) {
        mediaPlayer.apply {
            reset()
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(url)
            prepareAsync()
            setOnPreparedListener {
                isprepared = true
                mediaPlayer.start()
                setViews(rList!!)
            }
        }
    }

    private fun setViews(radioList: List<Radio>) {
        binding.tvRadioTitle.text = if (mediaPlayer.isPlaying && isprepared)
            radioList[possition].name
        else radioList[possition].name

        binding.btnPlay.icon = if (mediaPlayer.isPlaying && isprepared)
            ContextCompat.getDrawable(
                this.requireContext(),
                R.drawable.ic_pause

            ) else ContextCompat.getDrawable(
            this.requireContext(),
            R.drawable.ic_play)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}
