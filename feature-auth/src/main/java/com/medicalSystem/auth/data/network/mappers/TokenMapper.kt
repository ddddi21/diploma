package com.medicalSystem.auth.data.network.mappers

import com.medicalSystem.auth.data.network.model.Tokens
import com.medicalSystem.auth.data.network.response.TokensDto
import com.medicalSystem.common.utils.BaseSingleModelMapper
import javax.inject.Inject

class TokenMapper @Inject constructor() : BaseSingleModelMapper<TokensDto, Tokens> {

    override fun map(model: TokensDto): Tokens {
        return Tokens(
            authToken = model.accessToken,
            refreshToken = model.refreshToken
        )
    }
}