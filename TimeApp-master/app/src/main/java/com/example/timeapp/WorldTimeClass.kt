package com.example.timeapp

import android.os.Parcel
import android.os.Parcelable

data class WorldTimeClass(
    var nameCity: String,
    var nameCountry: String,
    var countryTime: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nameCity)
        parcel.writeString(nameCountry)
        parcel.writeString(countryTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WorldTimeClass> {
        override fun createFromParcel(parcel: Parcel): WorldTimeClass {
            return WorldTimeClass(parcel)
        }

        override fun newArray(size: Int): Array<WorldTimeClass?> {
            return arrayOfNulls(size)
        }
    }
}