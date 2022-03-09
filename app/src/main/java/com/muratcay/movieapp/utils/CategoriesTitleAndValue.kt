package com.muratcay.movieapp.utils

import com.muratcay.movieapp.R

sealed class Category(
    val title: String,
    val originalLanguage: String = "",
    val notOriginalLanguage: String = "",
    val releaseDateLte: String = "",
    val releaseDateGte: String = "",
    val voteAverageGte: String = ""
) {
    class Domestic(_title: String = "Yerli Filmler", _originalLanguage: String = "tr") :
        Category(title = _title, originalLanguage = _originalLanguage)

    class Foreign(_title: String = "Yabancı Filmler", _notOriginalLanguage: String = "tr") :
        Category(title = _title, notOriginalLanguage = _notOriginalLanguage)

    class Popular(_title: String = "Beğenilen Filmler", _voteAverageGte: String = "8") :
        Category(title = _title, voteAverageGte = _voteAverageGte)

    class OldTurkish(
        _title: String = "Yeşilçam Filmleri",
        _originalLanguage: String = "tr",
        _releaseDateLte: String = "1985"
    ) :
        Category(
            title = _title,
            originalLanguage = _originalLanguage,
            releaseDateLte = _releaseDateLte
        )

    class News(_title: String = "Bu Senenin Filmleri", _releaseDateGte: String = "2021") :
        Category(title = _title, releaseDateGte = _releaseDateGte)
}

val categories: List<Category> = listOf(
    Category.Domestic(),
    //Category.Foreign(),
    Category.Popular(),
    Category.OldTurkish(),
    Category.News()
)

