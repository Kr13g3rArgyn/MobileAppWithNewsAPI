package com.example.apitraining

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerAdapter(private val listOfNews: MutableList<News>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycle_card,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return  listOfNews.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.get().load(
            listOfNews[position]
            .getImage())
            .into(holder.itemImage)

        holder.itemAuthor.text = listOfNews[position].getAuthor()

        holder.itemTitle.text = listOfNews[position].getTitle()

        if(!listOfNews[position].getFavorite()){
            holder.itemButton.setImageResource(R.drawable.favorite_icon)
        }else if(listOfNews[position].getFavorite()){
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
                intent.putExtra("text",listOfNews[position].getText())
                intent.putExtra("title",listOfNews[position].getTitle())
                intent.putExtra("image",listOfNews[position].getImage())
                intent.putExtra("url",listOfNews[position].getUrl())
                intent.putExtra("date",listOfNews[position].getDate())
                itemView.context.startActivity(intent)
            }

            itemButton.setOnClickListener {
                if(!listOfNews[position].getFavorite()){
                    itemButton.setImageResource(R.drawable.favorite_icon_colored)
                    listOfNews[position].setFavorite(true)

                    val fragmentFragment: FavoriteFragment = FavoriteFragment()
                    fragmentFragment.AddNewFavoriteAnime(listOfNews[position],0)



                }else{
                    itemButton.setImageResource(R.drawable.favorite_icon)
                    listOfNews[position].setFavorite(false)
                    val fragmentFragment: FavoriteFragment = FavoriteFragment()
                    fragmentFragment.RemoveFavoriteAnime(listOfNews[position],0)
                }
            }






        }


    }



}