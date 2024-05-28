package com.ksv.hw16architect.entity

interface UsefulActivity {
    val activity: String
    val availability: Float
    val type: String
    val participants: Int
    val price: Float
    val accessibility: String
    val duration: String
    val kidFriendly: Boolean
    val link: String
    val key: Int
}