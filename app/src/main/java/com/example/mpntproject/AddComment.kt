package com.example.mpntproject

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AddComment: ViewModel() {
    var comments = mutableStateOf(listOf<Comment>())

    fun addComment(comment: Comment)
    {
    val newComment = comments.value.toMutableList()
        newComment.add(comment)
        comments.value = newComment
    }
}