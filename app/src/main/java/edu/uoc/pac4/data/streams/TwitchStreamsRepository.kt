package edu.uoc.pac4.data.streams

/**
 * Created by alex on 11/21/20.
 */

class TwitchStreamsRepository(
    private val remoteDataSource: TwitchStreamsRemoteDataSource
): StreamsRepository {

    override suspend fun getStreams(cursor: String?): Pair<String?, List<Stream>> {
        // Aqui se gestionaría si recoger los datos del data source local o remoto
        return remoteDataSource.getStreams(cursor)
    }
}