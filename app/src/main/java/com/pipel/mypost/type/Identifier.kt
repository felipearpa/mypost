package com.pipel.mypost.type

data class Identifier(val value: Int) {

    init {
        if (value < 0) {
            throw IllegalArgumentException("Value must be a positive number")
        }
    }

    override fun toString(): String = value.toString()

}