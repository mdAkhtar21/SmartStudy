package com.example.smartstudy.ui.theme.subject

import androidx.lifecycle.ViewModel
import com.example.smartstudy.ui.theme.domain.repository.SubjectRepository
import javax.inject.Inject

class SubjectViewModel @Inject constructor(
    private val subjectRepository: SubjectRepository
):ViewModel() {
}