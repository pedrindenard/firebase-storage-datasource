package com.pdm.firebase.feature.domain.usecase.login

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.pdm.firebase.feature.domain.enums.AuthException
import com.pdm.firebase.feature.domain.enums.InvalidAuth
import com.pdm.firebase.feature.domain.repository.LoginRepository

class LoginWithGoogleUseCase(private val repository: LoginRepository) {

    @Throws(AuthException::class)
    suspend operator fun invoke(accessToken: String?): Task<AuthResult>? {
        if (accessToken == null) {
            throw AuthException(InvalidAuth.SIGN_IN_FAILED.value)
        }

        return repository.loginWithGoogle(
            accessToken = accessToken
        )
    }
}