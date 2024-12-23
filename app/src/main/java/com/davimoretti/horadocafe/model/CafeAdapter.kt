package com.davimoretti.horadocafe.model
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.davimoretti.horadocafe.R
import retrofit2.Response

class CafeAdapter(private var cafes: List<Cafe>) : RecyclerView.Adapter<CafeAdapter.CafeViewHolder>() {

    class CafeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nome = view.findViewById<TextView>(R.id.tvNome)
        val tipo = view.findViewById<TextView>(R.id.tvTipo)
        val estoque = view.findViewById<TextView>(R.id.tvEstoque)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cafe_recycler, parent, false)
        return CafeViewHolder(view)
    }

    override fun onBindViewHolder(holder: CafeViewHolder, position: Int) {
        val cafe = cafes[position]
        holder.nome.text = cafe.nome
        holder.tipo.text = "Tipo: ${cafe.tipo}"
        holder.estoque.text = "Estoque: ${cafe.estoque}"
    }

    override fun getItemCount() = cafes.size

    // Metodo para atualizar a lista de cafés
    fun updateCafes(newCafes: List<Cafe>) {
        this.cafes = newCafes
        notifyDataSetChanged()
    }
}

