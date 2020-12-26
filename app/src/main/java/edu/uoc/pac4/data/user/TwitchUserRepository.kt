package edu.uoc.pac4.data.user

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