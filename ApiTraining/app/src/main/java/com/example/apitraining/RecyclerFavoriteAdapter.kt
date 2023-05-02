package com.example.apitraining

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerFavoriteAdapter(private val listOfFavorite: MutableList<News>): RecyclerView.Adapter<RecyclerFavoriteAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycle_card,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return  listOfFavorite.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.get()
            .load(listOfFavorite[position].getImage())
            .into(holder.itemImage)

        holder.itemAuthor.text = listOfFavorite[position].getAuthor()

        holder.itemTitle.text = listOfFavorite[position].getTitle()

        if(!listOfFavorite[position].getFavorite()){
            holder.itemButton.setImageResource(R.drawable.favorite_icon)
        }else if(listOfFavorite[position].getFavorite()){
            holder.itemButton.setImageResource(R.drawable.favorite_icon_colored)
        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemAuthor: TextView
        var itemContainer: RelativeLayout
        var itemButton: ImageView



        init {
            itemImage = itemView.findViewById(R.id.image_news)
            itemTitle = itemView.findViewById(R.id.news_title)
            itemAuthor = itemView.findViewById(R.id.news_author)
            itemButton = itemView.findViewById(R.id.favorite_btn)
            itemContainer = itemView.findViewById(R.id.container)



            itemContainer.setOnClickListener {
                val intent = Intent(itemView.context, NewsPage::class.java)
                intent.putExtra("text",listOfFavorite[position].getText())
                intent.putExtra("title",listOfFavorite[position].getTitle())
                intent.putExtra("image",listOfFavorite[position].getImage())
                intent.putExtra("url",listOfFavorite[position].getUrl())
                intent.putExtra("date",listOfFavorite[position].getDate())
                itemView.context.startActivity(intent)
            }

            itemButton.setOnClickListener {
                if(!listOfFavorite[position].getFavorite()){
                    itemButton.setImageResource(R.drawable.favorite_icon_colored)
                    listOfFavorite[position].setFavorite(true)

                    val fragmentFragment: FavoriteFragment = FavoriteFragment()
                    fragmentFragment.AddNewFavoriteAnime(listOfFavorite[position],1)
                }else{
                    itemButton.setImageResource(R.drawable.favorite_icon)
                    listOfFavorite[position].setFavorite(false)
                    val fragmentFragment: FavoriteFragment = FavoriteFragment()
                    fragmentFragment.RemoveFavoriteAnime(listOfFavorite[position],1)
                }
            }


        }


    }



}