package com.christidischristos.kinandcarta.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christidischristos.kinandcarta.repository.CaseStudyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: CaseStudyRepository
) : ViewModel() {

    val caseStudies = repository.caseStudies

    init {
        refreshStudies()
    }

    private fun refreshStudies() {
        viewModelScope.launch {
            try {
                repository.fetchStudiesFromNetwork()
            } catch (e: IOException) {
            }
        }
    }
}
