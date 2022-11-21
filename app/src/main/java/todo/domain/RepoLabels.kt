package todo.domain

import kotlinx.coroutines.flow.Flow

interface RepoLabels {

    suspend fun getAllPersonalLabelsFromApi(): List<LabelDomain>
    fun getAllPersonalLabelsFromDB(): Flow<List<LabelDomain>>

    suspend fun createPersonalLabelInApi( label: LabelDomain):LabelDomain?
    suspend fun createPersonalLabelInDB( label: LabelDomain)

    suspend fun getPersonalLabelByIdFromApi( idLabel: String): LabelDomain?
    suspend fun getPersonalLabelByIdFromDB( idLabel: String): LabelDomain

    suspend fun updatePersonalLabelByIdInApi( idLabel: String, label: LabelDomain):LabelDomain?
    suspend fun updatePersonalLabelByIdInDB( idLabel: String, label: LabelDomain)

    suspend fun deleteLabelByIdInApi( idLabel: String):Int
    suspend fun deleteLabelByIdInDB( idLabel: String)
    suspend fun clearAllLabels()

}