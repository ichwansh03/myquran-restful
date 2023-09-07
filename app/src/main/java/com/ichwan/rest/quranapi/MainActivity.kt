package com.ichwan.rest.quranapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.ichwan.rest.quranapi.databinding.ActivityMainBinding
import com.ichwan.rest.quranapi.domain.adapter.ChapterAdapter
import com.ichwan.rest.quranapi.utils.NetworkState
import com.ichwan.rest.quranapi.viewmodels.ChapterViewModels

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var adapter: ChapterAdapter? = null
    private var layoutManager: LayoutManager? = null

    private val viewModel: ChapterViewModels by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getChapter()
        observeGetChapter()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = ChapterAdapter()
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.apply {
            rvChapters.adapter = adapter
            rvChapters.layoutManager = layoutManager
        }
    }

    private fun observeGetChapter() {
        viewModel.liveChapterResponse.observe(this) {
            if (it != null) {
                when(it) {
                    is NetworkState.Loading -> showLoading()
                    is NetworkState.Success -> {
                        hideLoading()
                        it.data?.chapters?.let { data -> adapter?.setChapter(data) }
                    }
                    is NetworkState.Error -> {
                        hideLoading()
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun hideLoading() {
        binding.loading.hide()
    }

    private fun showLoading() {
        binding.loading.show()
    }
}