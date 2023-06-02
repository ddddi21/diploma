package com.example.feature_main.data.network.response

import com.google.gson.annotations.SerializedName

data class FilmDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("posterId")
    val posterUrl: String?
)

data class FilmResponse(
    @SerializedName("size")
    val size: Int,
    @SerializedName("pageCount")
    val pageCount: Int,
    @SerializedName("filmDtos")
    val filmDtos: List<FilmDto>
)