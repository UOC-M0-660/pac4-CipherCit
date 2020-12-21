package edu.uoc.pac4.data.oauth

/**
 * Created by alex on 11/21/20.
 */
class OAuthAuthenticationRepository(
    private val remoteDataSource: OAuthAuthenticationRemoteDataSource
) : AuthenticationRepository {

    override suspend fun isUserAvailable(): Boolean {
        return remoteDataSource.isUserAvailable()
    }

    override suspend fun login(authorizationCode: String): Boolean {
        return remoteDataSource.login(authorizationCode)
    }

    override suspend fun logout() {
        remoteDataSource.logout()
    }
}