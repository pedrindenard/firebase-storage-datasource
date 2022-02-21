package com.pdm.firebase.feature.domain.usecase.tv

import com.pdm.firebase.arquitecture.Resource
import com.pdm.firebase.feature.domain.model.tv.TvShowResponse
import com.pdm.firebase.feature.domain.repository.TvShowRepository
import retrofit2.HttpException
import java.io.IOException

class GetTvShowPopularUseCase(private val repository: TvShowRepository) {

    suspend operator fun invoke(page: Int, ignoreCache: Boolean? = false): Resource<TvShowResponse?> {
        return try {
            repository.getTvShowPopular(
                page = page, ignoreCache = ignoreCache!!
            )
        } catch (e: HttpException) {
            Resource.Error(
                message = e.message(),
                code = e.code()
            )
        } catch (e: IOException) {
            Resource.Failure(
                throwable = e.cause!!
            )
        }
    }
}