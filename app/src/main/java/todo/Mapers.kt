package todo


import todo.domain.CollaboratorsDomain
import todo.domain.DueDtoDomain
import todo.domain.LabelDomain
import todo.domain.ProjectDomain
import todo.domain.RenamedLabelDomain
import todo.domain.TaskDomain
import todo.framework.network.CollaboratorsDto
import todo.framework.network.DueDto
import todo.framework.network.LabelDto
import todo.framework.network.ProjectDto
import todo.framework.network.RenamedLabelDto
import todo.framework.network.TaskDto


fun ProjectDto.toProjectDomain() =
    ProjectDomain(
        id,
        name,
        color,
        parentId,
        order,
        commentCount,
        isShared,
        isFavorite,
        isInboxProject,
        isTeamInbox,
        viewStyle,
        url
    )
fun ProjectDomain.toProjectDto() =
    ProjectDto(
        id,
        name,
        color,
        parentId,
        order,
        commentCount,
        isShared,
        isFavorite,
        isInboxProject,
        isTeamInbox,
        viewStyle,
        url
    )
fun CollaboratorsDto.toCollaboratorsDomain() =
    CollaboratorsDomain(
        id, name, email
    )

fun TaskDto.toTaskDomain() =
    TaskDomain(
        id,
        projectId,
        sectionId,
        content,
        description,
        isCompleted,
        labels,
        parentId,
        order,
        priority,
        due.toDueDomain(),
        url,
        commentCount,
        createdAt,
        creatorId,
        assigneeId,
        assignerId
    )
fun TaskDomain.toTaskDto()=
    TaskDto(
        id,
        projectId,
        sectionId,
        content,
        description,
        isCompleted,
        labels,
        parentId,
        order,
        priority,
        due.toDueDto(),
        url,
        commentCount,
        createdAt,
        creatorId,
        assigneeId,
        assignerId
    )
fun DueDto.toDueDomain() =
    DueDtoDomain(
        string,
        date,
        isRecurring,
        datetime,
        timezone
    )
fun DueDtoDomain.toDueDto()=
    DueDto(
        string,
        date,
        isRecurring,
        datetime,
        timezone
    )
fun LabelDto.toLabelDomain() =
    LabelDomain(
        id,
        name,
        color,
        order,
        isFavorite
    )
fun LabelDomain.toLabelDto()=
    LabelDto(
        id,
        name,
        color,
        order,
        isFavorite
    )
fun RenamedLabelDomain.toRenamedLabelDto()=
    RenamedLabelDto(
        name,
        newName
    )