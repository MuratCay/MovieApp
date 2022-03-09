package com.muratcay.movieapp.core.model


import com.google.gson.annotations.SerializedName

data class FilmListResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)