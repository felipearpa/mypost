package com.felipearpa.mypost.type

data class Email(val value: String) {

    init {
        checkEmpty()
        checkPattern()
    }

    private fun checkPattern() {
        val pattern = Regex("[a-z0-9]+@[a-z]+\\.[a-z]")
        if (!pattern.containsMatchIn(value)) {
            throw IllegalArgumentException("Value must be a valid email")
        }
    }

    private fun checkEmpty() {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Value must be not empty")
        }
    }

    override fun toString(): String = value

}