package edu.uoc.pac4.data.di

import edu.uoc.pac4.data.SessionManager
import edu.uoc.pac4.data.network.Network
import edu.uoc.pac4.data.oauth.AuthenticationRepository
import edu.uoc.pac4.data.oauth.OAuthAuthenticationRemoteDataSource
import edu.uoc.pac4.data.oauth.OAuthAuthenticationRepository
import edu.uoc.pac4.data.streams.StreamsRepository
import edu.uoc.pac4.data.streams.TwitchStreamsRemoteDataSource
import edu.uoc.pac4.data.streams.TwitchStreamsRepository
import edu.uoc.pac4.data.user.TwitchUserRemoteDataSource
import edu.uoc.pac4.data.user.TwitchUserRepository
import edu.uoc.pac4.data.user.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by alex on 11/21/20.
 */

val dataModule = module {
    single<StreamsRepository> { TwitchStreamsRepository(get()) }
    single<UserRepository> { TwitchUserRepository(get()) }
    single<AuthenticationRepository> { OAuthAuthenticationRepository(get()) }
    
    // data sources
    single { TwitchStreamsRemoteDataSource(get(), get()) }
    single { TwitchUserRemoteDataSource(get()) }
    single { OAuthAuthenticationRemoteDataSource(get(), get()) }

    single {  SessionManager(androidContext()) }
    single { Network.createHttpClient(androidContext()) }
}