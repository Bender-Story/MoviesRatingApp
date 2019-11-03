package com.android.rahul.movies.viewmodel

import com.android.rahul.movies.model.Result

// view model for the main movie list
class MainRowViewModel(
    val result: Result?,
    private val onSelect: (Result) -> Unit
) {
    fun onClick() {
        result?.let { onSelect.invoke(it) }
    }
}