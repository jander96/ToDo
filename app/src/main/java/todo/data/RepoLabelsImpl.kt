package todo.data

import kotlinx.coroutines.flow.Flow
import todo.domain.LabelDomain
import todo.domain.RepoLabels
import todo.domain.ResponseState
import javax.inject.Inject

class RepoLabelsImpl
@Inject constructor(
    private val localResource: LocalResource,
    private val networkResources: NetworkResources
) : RepoLabels {
    override suspend fun getAllPersonalLabelsFromApi(): ResponseState<List<LabelDomain>> {
        return networkResources.getAllPersonalLabels()
    }

    override  fun getAllPersonalLabelsFromDB(): Flow<List<LabelDomain>> {
        return localResource.getAllPersonalLabels()
    }

    override suspend fun createPersonalLabelInApi(label: LabelDomain): ResponseState<LabelDomain?> {
       return networkResources.createPersonalLabel(label)
    }

    override suspend fun createPersonalLabelInDB(label: LabelDomain) {
       localResource.createPersonalLabel(label)
    }

    override suspend fun getPersonalLabelByIdFromApi(idLabel: String): ResponseState<LabelDomain?> {
       return networkResources.getPersonalLabelById(idLabel)
    }

    override suspend fun getPersonalLabelByIdFromDB(idLabel: String): LabelDomain {
       return localResource.getPersonalLabelById(idLabel)
    }

    override suspend fun findLabelByName(labelName: String): String {
        return localResource.findLabelByName(labelName)
    }

    override suspend fun updatePersonalLabelByIdInApi(idLabel: String, label: LabelDomain): ResponseState<LabelDomain?> {
       return networkResources.updatePersonalLabelById(idLabel,label)
    }

    override suspend fun updatePersonalLabelByIdInDB(idLabel: String, label: LabelDomain) {
        localResource.updatePersonalLabelById(idLabel,label)
    }

    override suspend fun deleteLabelByIdInApi(idLabel: String): ResponseState<Int> {
       return networkResources.deleteLabelById(idLabel)
    }

    override suspend fun deleteLabelByIdInDB(idLabel: String) {
        localResource.deleteLabelById(idLabel)
    }

    override suspend fun clearAllLabels() {
        localResource.clearAllLabelsInDB()
    }
}