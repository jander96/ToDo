package com.example.todo.framework.network

data class LabelDto(
    val id: String,
    val name: String,
    val color: String,
    val order: Int,
    val is_favorite: Boolean
)