package com.ichwan.rest.quranapi.domain.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.rest.quranapi.databinding.ListChapterBinding
import com.ichwan.rest.quranapi.models.ChapterResponse
import com.ichwan.rest.quranapi.models.ChaptersItem

class ChapterAdapter: RecyclerView.Adapter<ChapterAdapter.ViewHolder>() {

    private val list = ArrayList<ChaptersItem>()

    inner class ViewHolder (var binding: ListChapterBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListChapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ChapterAdapter.ViewHolder, position: Int) {
        with(holder){
            with(list[position]){
                binding.apply {
                    chapterName.text = nameSimple
                    translatedChapter.text = translatedName?.name
                    revelationCity.text = revelationPlace
                    verses.text = "${versesCount.toString()} Ayat"
                    revOrder.text = "Diturunkan ke-${revelationOrder.toString()}"
                }
            }
        }
    }

    override fun getItemCount(): Int = list.size

    fun setChapter(list: List<ChaptersItem>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}