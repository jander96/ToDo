package todo.data

import kotlinx.coroutines.flow.Flow
import todo.domain.LabelDomain
import todo.domain.LocalResource
import todo.domain.NetworkResources
import todo.domain.RepoLabels
import javax.inject.Inject

class RepoLabelsImpl
@Inject constructor(
    private val localResource: LocalResource,
    private val networkResources: NetworkResources
) : RepoLabels {
    override suspend fun getAllPersonalLabelsFromApi(): List<LabelDomain> {
        return networkResources.getAllPersonalLabels()
    }

    override  fun getAllPersonalLabelsFromDB(): Flow<List<LabelDomain>> {
        return localResource.getAllPersonalLabels()
    }

    override suspend fun createPersonalLabelInApi(label: LabelDomain): LabelDomain? {
       return networkResources.createPersonalLabel(label)
    }

    override suspend fun createPersonalLabelInDB(label: LabelDomain) {
       localResource.createPersonalLabel(label)
    }

    override suspend fun getPersonalLabelByIdFromApi(idLabel: String): LabelDomain? {
       return networkResources.getPersonalLabelById(idLabel)
    }

    override suspend fun getPersonalLabelByIdFromDB(idLabel: String): LabelDomain {
       return localResource.getPersonalLabelById(idLabel)
    }

    override suspend fun updatePersonalLabelByIdInApi(idLabel: String, label: LabelDomain): LabelDomain? {
       return networkResources.updatePersonalLabelById(idLabel,label)
    }

    override suspend fun updatePersonalLabelByIdInDB(idLabel: String, label: LabelDomain) {
        localResource.updatePersonalLabelById(idLabel,label)
    }

    override suspend fun deleteLabelByIdInApi(idLabel: String):Int{
       return networkResources.deleteLabelById(idLabel)
    }

    override suspend fun deleteLabelByIdInDB(idLabel: String) {
        localResource.deleteLabelById(idLabel)
    }

    override suspend fun clearAllLabels() {
        localResource.clearAllLabelsInDB()
    }
}