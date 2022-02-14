package com.pdm.firebase.feature.domain.usecase.profile

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.pdm.firebase.feature.domain.enums.InvalidAuth
import com.pdm.firebase.feature.domain.model.auth.User
import com.pdm.firebase.feature.domain.repository.ProfileRepository
import com.pdm.firebase.util.isValidBirthDate

class EditUserUseCase(private val repository: ProfileRepository) {

    @Throws(Exception::class)
    suspend operator fun invoke(firebaseAuth: FirebaseAuth, user: User): Task<Void>? {
        var throws = String()

        if (firebaseAuth.currentUser == null) {
            throw Exception(InvalidAuth.CURRENT_USER_IS_NULL.value)
        }
        if (user.lastName.isBlank()) {
            throws += InvalidAuth.EMPTY_LAST_NAME.value
        }
        if (!isValidBirthDate(user.birthdate)) {
            throws += InvalidAuth.INVALID_BIRTHDATE.value
        }
        if (user.gender == 0) {
            throws += InvalidAuth.INVALID_GENDER.value
        }
        if (throws.isNotEmpty()) {
            throw Exception(throws)
        }

        return repository.editProfile(
            currentUser = firebaseAuth.currentUser!!,
            user = user
        )
    }
}