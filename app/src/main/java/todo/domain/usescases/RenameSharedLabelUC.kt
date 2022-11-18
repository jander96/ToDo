package todo.domain.usescases

import todo.domain.Repo
import todo.framework.RenamedLabel
import todo.toRenamedLabelDomain
import javax.inject.Inject

class RenameSharedLabelUC (private val repo:Repo) {
    suspend fun renameSharedLabel(renamedlabel: RenamedLabel){
        repo.renameSharedLabels(renamedlabel.toRenamedLabelDomain())
    }
}