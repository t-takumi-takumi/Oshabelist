package com.outside.oshabelist.edit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.outside.oshabelist.data.TalkThemeModel
import com.outside.oshabelist.databinding.ItemTalkThemeListBinding

class ThemeListAdapter(private val themeList: List<TalkThemeModel>) :
    RecyclerView.Adapter<ThemeListAdapter.ViewHolder>() {
    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTalkThemeListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val talkTheme = themeList[position]
        holder.binding.themeModel = talkTheme
        holder.binding.num = position + 1

        holder.binding.themeItem.setOnClickListener {
            listener.onClick(it, talkTheme)
        }
    }

    override fun getItemCount(): Int {
        return themeList.size
    }

    interface OnItemClickListener {
        fun onClick(view: View, talkThemeModel: TalkThemeModel)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    class ViewHolder(var binding: ItemTalkThemeListBinding) :
        RecyclerView.ViewHolder(binding.root) {}
}