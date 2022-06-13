package com.pipel.mypost.type

data class NonEmptyString(val value: String) {

    init {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Value must be not empty")
        }
    }

    override fun toString(): String = value

}