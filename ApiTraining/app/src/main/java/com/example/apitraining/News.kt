package com.example.apitraining

class News(private var id: String,
           private var title: String,
           private var image: String,
           private var author: String,
           private var text: String,
           private var date: String,
           private var url: String,
           private var favorite: Boolean = false
        ) {

    fun getId():String{
        return this.id
    }

    fun setFavorite(isfavorite: Boolean){
        this.favorite = isfavorite
    }

    fun getImage(): String{
        return this.image
    }

    fun getTitle(): String{
        return this.title
    }
    fun getAuthor(): String{
        return this.author
    }

    fun getUrl(): String{
        return this.url
    }
    fun getDate(): String{
        return this.date
    }
    fun getText(): String{
        return this.text
    }

    fun getFavorite(): Boolean{
        return this.favorite
    }
}
