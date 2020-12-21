package edu.uoc.pac4.data.user

import android.util.Log
import edu.uoc.pac4.data.network.Endpoints
import edu.uoc.pac4.data.network.UnauthorizedException
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

/**
 * Created by alex on 11/21/20.
 */

class TwitchUserRepository(
    private val remoteDataSource: TwitchUserRemoteDataSource
) : UserRepository {

    override suspend fun getUser(): User? {
        return remoteDataSource.getUser()
    }

    override suspend fun updateUser(description: String): User? {
        return remoteDataSource.updateUser(description)
    }
}