package todo.domain

import kotlinx.coroutines.flow.Flow

interface RepoLabels {

    suspend fun getAllPersonalLabelsFromApi(): ResponseState<List<LabelDomain>>
    fun getAllPersonalLabelsFromDB(): Flow<List<LabelDomain>>

    suspend fun createPersonalLabelInApi( label: LabelDomain):ResponseState<LabelDomain?>
    suspend fun createPersonalLabelInDB( label: LabelDomain)

    suspend fun getPersonalLabelByIdFromApi( idLabel: String): ResponseState<LabelDomain?>
    suspend fun getPersonalLabelByIdFromDB( idLabel: String): LabelDomain

    suspend fun updatePersonalLabelByIdInApi( idLabel: String, label: LabelDomain):ResponseState<LabelDomain?>
    suspend fun updatePersonalLabelByIdInDB( idLabel: String, label: LabelDomain)

    suspend fun deleteLabelByIdInApi( idLabel: String):ResponseState<Int>
    suspend fun deleteLabelByIdInDB( idLabel: String)
    suspend fun clearAllLabels()

}