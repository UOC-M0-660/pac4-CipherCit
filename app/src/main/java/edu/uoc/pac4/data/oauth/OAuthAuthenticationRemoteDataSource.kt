package edu.uoc.pac4.data.oauth

import android.util.Log
import edu.uoc.pac4.data.SessionManager
import edu.uoc.pac4.data.network.Endpoints
import io.ktor.client.*
import io.ktor.client.request.*

class OAuthAuthenticationRemoteDataSource(
    private val sessionManager: SessionManager,
    private val httpClient: HttpClient,
) {
  private val tag = "OAuthAuthenticationRemoteDataSource"

  fun isUserAvailable(): Boolean {
    return sessionManager.isUserAvailable()
  }

  suspend fun login(authorizationCode: String): Boolean {
    try {
      val response = httpClient
          .post<OAuthTokensResponse>(Endpoints.tokenUrl) {
            parameter("client_id", OAuthConstants.clientID)
            parameter("client_secret", OAuthConstants.clientSecret)
            parameter("code", authorizationCode)
            parameter("grant_type", "authorization_code")
            parameter("redirect_uri", OAuthConstants.redirectUri)
          }

      Log.d(tag, "Got Access token ${response.accessToken}")

      // Save access token and refresh token using the SessionManager class
      sessionManager.saveAccessToken(response.accessToken)
      response.refreshToken?.let {
        sessionManager.saveRefreshToken(it)
      }

      return true
    } catch (t: Throwable) {
      Log.w(tag, "Error Getting Access token", t)
      return false
    }
  }

  fun logout() {
    sessionManager.clearAccessToken()
    sessionManager.clearRefreshToken()
  }
}