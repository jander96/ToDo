package todo.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import todo.data.RepoLabelsImpl
import todo.data.RepoProjectsImpl
import todo.data.RepoTaskImpl
import todo.domain.LocalResource
import todo.domain.NetworkResources
import todo.domain.RepoLabels
import todo.domain.RepoProjects
import todo.domain.RepoTask
import todo.framework.LocalResourceImpl
import todo.framework.NetworkResourcesImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {


    @Binds
    @ViewModelScoped
     abstract fun bindNetworkResource(networkResourcesImpl: NetworkResourcesImpl): NetworkResources

     @Binds
     @ViewModelScoped
     abstract fun bindLocalResource(localResourceImpl: LocalResourceImpl): LocalResource

     @Binds
     @ViewModelScoped
     abstract fun bindRepoProjects(repoProjectsImpl: RepoProjectsImpl):RepoProjects
     @Binds
     @ViewModelScoped
     abstract fun bindRepoTask(repoTaskImpl: RepoTaskImpl):RepoTask

     @Binds
     @ViewModelScoped
     abstract fun bindRepoLabels(repoLabelsImpl: RepoLabelsImpl):RepoLabels
}