package edu.uoc.pac4.data.di

import edu.uoc.pac4.data.network.Network
import edu.uoc.pac4.data.streams.StreamsRepository
import edu.uoc.pac4.data.streams.TwitchStreamsRepository
import edu.uoc.pac4.data.user.TwitchUserRepository
import edu.uoc.pac4.data.user.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by alex on 11/21/20.
 */

val dataModule = module {
    single<StreamsRepository> { TwitchStreamsRepository(Network.createHttpClient(androidContext())) }
    single<UserRepository> { TwitchUserRepository(Network.createHttpClient(androidContext())) }
}