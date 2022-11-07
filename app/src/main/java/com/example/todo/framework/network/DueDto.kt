package com.example.todo.framework.network

data class DueDto(
    val string: String,
    val date: String,
    val is_recurring: Boolean,
    val datetime: String,
    val timezone: String
)