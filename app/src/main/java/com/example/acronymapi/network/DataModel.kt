package com.example.acronymapi.network

data class DataModel(
    val sf: String,
    val lfs: List<AcronymResults>
)

data class AcronymResults(
    val lf: String,
    val freq: Int,
    val since: Int
)
