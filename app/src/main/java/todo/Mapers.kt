package todo


import todo.domain.CollaboratorsDomain
import todo.domain.DueDomain
import todo.domain.LabelDomain
import todo.domain.ProjectDomain
import todo.domain.RenamedLabelDomain
import todo.domain.TaskDomain
import todo.framework.Collaborators
import todo.framework.Label
import todo.framework.Project
import todo.framework.RenamedLabel
import todo.framework.Task
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
fun Project.toProjectDomain()=
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
fun ProjectDomain.toProject()=
    Project(
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
fun CollaboratorsDomain.toCollaborators() =
    Collaborators(
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
        due?.toDueDomain(),
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
        due?.toDueDto(),
        url,
        commentCount,
        createdAt,
        creatorId,
        assigneeId,
        assignerId
    )
fun TaskDomain.toTask() =
    Task(
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
        due,
        url,
        commentCount,
        createdAt,
        creatorId,
        assigneeId,
        assignerId
    )
fun Task.toTaskDomain()=
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
        due,
        url,
        commentCount,
        createdAt,
        creatorId,
        assigneeId,
        assignerId
    )
fun DueDto.toDueDomain() =
    DueDomain(
        string,
        date,
        isRecurring,
        datetime,
        timezone
    )
fun DueDomain.toDueDto()=
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
fun LabelDomain.toLabel()=
    Label(
        id, name, color, order, isFavorite
    )
fun Label.toLabelDomain()=
    LabelDomain(
        id, name, color, order, isFavorite
    )
fun RenamedLabelDomain.toRenamedLabelDto()=
    RenamedLabelDto(
        name,
        newName
    )
fun RenamedLabel.toRenamedLabelDomain()=
    RenamedLabelDomain(
        name, newName
    )