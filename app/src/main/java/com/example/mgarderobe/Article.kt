package com.example.mgarderobe

import java.io.Serializable

class Article (val id: Int, val name: String, val description: String, val image: Int): Serializable {

    override fun toString(): String {
        return name
    }
}