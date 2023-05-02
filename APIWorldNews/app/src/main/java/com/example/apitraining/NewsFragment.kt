package com.example.apitraining

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apitraining.util.NetworkUtils
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.net.URL

private lateinit var numberPicker: NumberPicker
private lateinit var recyclerView: RecyclerView
private lateinit var loading: ProgressBar
private lateinit var errorText: TextView
private lateinit var noResultText: TextView

private var currentJsonArray: JSONArray? = null
private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
class NewsFragment : Fragment() {
    private var layoutManager: RecyclerView.LayoutManager?= null
    private lateinit var recyclerView: RecyclerView

    class QueryTask(private var recyclerView: RecyclerView):  AsyncTask<URL, Void, String>() {
        private var listOfNews: MutableList<News> = mutableListOf()

        override fun onPreExecute() {

            loading.visibility = View.VISIBLE
            noResultText.visibility = View.INVISIBLE
            errorText.visibility = View.INVISIBLE
        }

        override fun doInBackground(vararg p0: URL?): String? {

            var response : String? = NetworkUtils.getResponseFromURL(p0[0]!!)

            return response
        }

        override fun onPostExecute(result: String?) {
            var info: String? = null


            if(result != null && result != "" ) {
                try {
                    val jsonResponse: JSONObject = JSONObject(result)
                    val jsonArray: JSONArray = jsonResponse.getJSONArray("news")
                    currentJsonArray = jsonArray

                    val favoriteFragment = FavoriteFragment()
                    val favoriteList = favoriteFragment.getListOfFavorite()

                    for(i in 0 until jsonArray.length()){

                        if(!favoriteList.isNullOrEmpty()){

                            if(favoriteList.any{it.getId() == jsonArray.getJSONObject(i).getString("id")}){
                                var news =News(jsonArray.getJSONObject(i).getString("id"),
                                    jsonArray.getJSONObject(i).getString("title"),
                                    jsonArray.getJSONObject(i).getString("image"),
                                    jsonArray.getJSONObject(i).getString("author"),
                                    jsonArray.getJSONObject(i).getString("text"),
                                    jsonArray.getJSONObject(i).getString("publish_date"),
                                    jsonArray.getJSONObject(i).getString("url"),
                                    true)

                                listOfNews.add(news)
                            }else if(favoriteList.all{it.getId() != jsonArray.getJSONObject(i).getString("id")}){
                                var news =News(jsonArray.getJSONObject(i).getString("id"),
                                    jsonArray.getJSONObject(i).getString("title"),
                                    jsonArray.getJSONObject(i).getString("image"),
                                    jsonArray.getJSONObject(i).getString("author"),
                                    jsonArray.getJSONObject(i).getString("text"),
                                    jsonArray.getJSONObject(i).getString("publish_date"),
                                    jsonArray.getJSONObject(i).getString("url"),
                                    false)

                                listOfNews.add(news)
                            }
                        }else {
                            var news =News(jsonArray.getJSONObject(i).getString("id"),
                                jsonArray.getJSONObject(i).getString("title"),
                                jsonArray.getJSONObject(i).getString("image"),
                                jsonArray.getJSONObject(i).getString("author"),
                                jsonArray.getJSONObject(i).getString("text"),
                                jsonArray.getJSONObject(i).getString("publish_date"),
                                jsonArray.getJSONObject(i).getString("url"),
                                false)

                            listOfNews.add(news)
                        }


                    }
                    adapter = RecyclerAdapter(listOfNews)
                    recyclerView.adapter = adapter

                } catch (e: JSONException){
                    recyclerView.adapter = null
                    noResultText.visibility = View.VISIBLE
                }

            } else if(result == null || result == ""){
                errorText.visibility = View.VISIBLE
                recyclerView.adapter = null
            }

            loading.visibility = View.INVISIBLE

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        var view  = inflater.inflate(R.layout.fragment_news, container, false)

        loading = view.findViewById(R.id.loading)
        recyclerView = view.findViewById(R.id.recycler_view)
        numberPicker = view.findViewById(R.id.textNumPicker)
        errorText = view.findViewById(R.id.errorText)
        noResultText = view.findViewById(R.id.noResoultText)

        numberPicker.minValue=0
        numberPicker.maxValue=6
        var list = arrayOf<String>("tesla","Kazakhstan","America","football","business","TechCrunch","university")
        numberPicker.displayedValues = list


        layoutManager = LinearLayoutManager(view.context)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager
        var generatedURL: URL = NetworkUtils.generateURL("tesla")
        numberPicker.setOnValueChangedListener { _, _, i ->
            when(i) {
                0-> generatedURL = NetworkUtils.generateURL("tesla")
                1-> generatedURL = NetworkUtils.generateURL("Kazakhstan")
                2-> generatedURL = NetworkUtils.generateURL("America")
                3-> generatedURL = NetworkUtils.generateURL("football")
                4-> generatedURL = NetworkUtils.generateURL("business")
                5-> generatedURL = NetworkUtils.generateURL("TechCrunch")
                6-> generatedURL = NetworkUtils.generateURL("university")
            }

            QueryTask(recyclerView).execute(generatedURL)
            errorText.visibility = View.INVISIBLE

        }

        QueryTask(recyclerView).execute(generatedURL)
        return view
    }

}