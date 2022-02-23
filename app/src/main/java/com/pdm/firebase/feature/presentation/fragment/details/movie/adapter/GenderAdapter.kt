package com.pdm.firebase.feature.presentation.fragment.details.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.pdm.firebase.R
import com.pdm.firebase.feature.domain.model.gender.Gender
import com.pdm.firebase.feature.presentation.fragment.details.movie.adapter.GenderAdapter.ViewHolder

class GenderAdapter(
    private val mutableList: List<Gender>
) : RecyclerView.Adapter<ViewHolder>() {

    private lateinit var mClickListener: ClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(
                R.layout.item_gender,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mutableList = mutableList[position]
        holder.name.text = mutableList.name
    }

    override fun getItemCount(): Int = mutableList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var name: AppCompatTextView = itemView.findViewById(R.id.nameGender)

        init { itemView.setOnClickListener(this) }

        override fun onClick(view: View?) {
            mClickListener.onItemClickListener(
                gender = mutableList[absoluteAdapterPosition]
            )
        }
    }

    fun setOnItemClickListener(aClickListener: ClickListener) {
        mClickListener = aClickListener
    }

    interface ClickListener {
        fun onItemClickListener(gender: Gender)
    }
}