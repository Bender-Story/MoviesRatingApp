package com.android.rahul.movies.viewmodel

import com.android.rahul.movies.model.Result

// View model for the related list(row view)
class MoviewRelatedRowViewModel(
    val result: Result,
    private val onSelect: (Result) -> Unit
) {
    fun onClick() {
        onSelect.invoke(result)
    }
}