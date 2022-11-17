package todo.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import todo.data.RepoImpl
import todo.domain.NetworkResources
import todo.domain.Repo
import todo.framework.NetworkResourcesImpl

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {
    @Provides

    fun bindRepo(repoImpl: RepoImpl): Repo{
        return repoImpl
    }

    @Provides

     fun bindNetworkResource(networkResourcesImpl: NetworkResourcesImpl): NetworkResources{
         return networkResourcesImpl
     }
}