package com.fraporitmos.criptocurrency.domain.model

import com.fraporitmos.criptocurrency.data.remote.dto.CryptoDetailDto.entitys.TeamMember

data class CryptoDetail (
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val logo: String,
    val tags: List<String>,
    val team: List<TeamMember>
)