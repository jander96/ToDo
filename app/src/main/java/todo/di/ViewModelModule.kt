package todo.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import todo.data.RepoImpl
import todo.domain.NetworkResources
import todo.domain.Repo
import todo.framework.NetworkResourcesImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {
    @Binds
    @ViewModelScoped
    abstract fun bindRepo(repoImpl: RepoImpl): Repo

    @Binds
    @ViewModelScoped
    abstract  fun bindNetworkResource(networkResourcesImpl: NetworkResourcesImpl): NetworkResources
}