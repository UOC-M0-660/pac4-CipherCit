package edu.uoc.pac4.data.streams

import android.util.Log
import edu.uoc.pac4.data.network.Endpoints
import edu.uoc.pac4.data.network.UnauthorizedException
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class TwitchStreamsRemoteDataSource (private val httpClient: HttpClient) {

  private val TAG = "TwitchStreamsRemoteDataSource"

  suspend fun getStreams(cursor: String?): Pair<String?, List<Stream>> {
    try {
      val response = httpClient
          .get<StreamsResponse>(Endpoints.streamsUrl) {
            cursor?.let { parameter("after", it) }
          }

      return Pair(response.pagination?.cursor, response.data.orEmpty())
    } catch (t: Throwable) {
      Log.w(TAG, "Error getting streams", t)
      // Try to handle error
      return when (t) {
        is ClientRequestException -> {
          // Check if it's a 401 Unauthorized
          if (t.response?.status?.value == 401) {
            throw UnauthorizedException
          }
          Pair(null, listOf())
        }
        else -> Pair(null, listOf())
      }
    }
  }
}