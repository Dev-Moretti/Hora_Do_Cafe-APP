package com.davimoretti.horadocafe.model
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.davimoretti.horadocafe.R

class CafeAdapter(private val cafes: List<Cafe>) : RecyclerView.Adapter<CafeAdapter.CafeViewHolder>() {

    class CafeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCafeName: TextView = itemView.findViewById(R.id.tvCafeName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cafe_recycler, parent, false)
        return CafeViewHolder(view)
    }

    override fun onBindViewHolder(holder: CafeViewHolder, position: Int) {
        val cafe = cafes[position]
        holder.tvCafeName.text = cafe.nome // Substitua conforme o modelo do seu objeto

    }

    override fun getItemCount(): Int = cafes.size
}

