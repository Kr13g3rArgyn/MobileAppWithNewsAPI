package com.example.apitraining

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray

private var currentJsonArray: JSONArray? = null
private var adapter: RecyclerView.Adapter<RecyclerFavoriteAdapter.ViewHolder>? = null
class FavoriteFragment : Fragment() {
    private var layoutManager: RecyclerView.LayoutManager?= null
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyText: TextView


    companion object{
        private var listOfFavorite: MutableList<News> = mutableListOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        var view = inflater.inflate(R.layout.fragment_favorite, container, false)
        layoutManager = LinearLayoutManager(view.context)
        recyclerView = view.findViewById(R.id.recycler_view_favorite)
        recyclerView.layoutManager = layoutManager


        emptyText = view.findViewById(R.id.emptyText)
        if(listOfFavorite.isNullOrEmpty()){
            emptyText.visibility = View.VISIBLE
        } else{
            emptyText.visibility = View.INVISIBLE
        }

        adapter = RecyclerFavoriteAdapter(listOfFavorite)
        recyclerView.adapter = adapter
        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    fun AddNewFavoriteAnime(news: News, type: Int){

        listOfFavorite.add(news)

        if(type==1){

            adapter?.notifyDataSetChanged()
        }
    }

    fun RemoveFavoriteAnime(news: News,type: Int){


        if(!listOfFavorite.isNullOrEmpty()){

            for(i in listOfFavorite){
                if(i.getId() == news.getId()){

                    listOfFavorite.remove(i)
                    break

                }
            }
        }


        if(type==1){

            adapter?.notifyDataSetChanged()
        }
    }

    fun getListOfFavorite(): MutableList<News>{
        return listOfFavorite
    }
}