package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Adapter(val articulos:List<Resultado>,val listener: OnItemClickListener): RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        return MainViewHolder(inflater.inflate(R.layout.item_layout,parent,false),listener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

       val articulo:Resultado= articulos.get(position)
        holder.render(articulo)
       // holder.itemView.setOnClickListener{

       // }
    }

    override fun getItemCount(): Int {
     return  articulos.size
    }
}

class MainViewHolder(val view: View,val listener: OnItemClickListener) : RecyclerView.ViewHolder(view) {
     fun render(articulo:Resultado){
           view.findViewById<TextView>(R.id.title).text = articulo.title
           view.findViewById<TextView>(R.id.date).text = articulo.published_date
        Picasso.get().load(articulo.media[0].mediaMetadata[0].url).into(view.findViewById<ImageView>(R.id.image_picture))

         view.findViewById<LinearLayout>(R.id.itemView).setOnClickListener {
             listener.onItemClick(articulo)

         }

     }

}





