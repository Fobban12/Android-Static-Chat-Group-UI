package com.example.mpntproject

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AddComment: ViewModel() {
    var comments = mutableStateOf(listOf<Comment>())

    fun addComment(comment: Comment)
    {
    var newComment = comments.value.toMutableList()
        newComment.add(comment)
        comments.value = newComment
    }

    fun deleteComment(comment: Comment)
    {
    var newComment = comments.value.toMutableList()
        newComment.remove(comment)
        comments.value = newComment
    }



}