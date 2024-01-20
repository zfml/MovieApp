package com.zfml.tmdbapimovie.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class PopularMovieKeysEntity (
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)