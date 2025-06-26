package com.example.smartstudy.ui.theme.session

import androidx.lifecycle.ViewModel
import com.example.smartstudy.ui.theme.domain.repository.SessionRepository
import javax.inject.Inject

class SessionViewModel @Inject constructor(
    private val sessionRepository:SessionRepository
):ViewModel() {
}