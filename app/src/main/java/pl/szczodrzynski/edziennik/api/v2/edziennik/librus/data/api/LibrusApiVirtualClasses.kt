/*
 * Copyright (c) Kuba Szczodrzyński 2019-10-23.
 */

package pl.szczodrzynski.edziennik.api.v2.edziennik.librus.data.api

import pl.szczodrzynski.edziennik.*
import pl.szczodrzynski.edziennik.api.v2.edziennik.librus.DataLibrus
import pl.szczodrzynski.edziennik.api.v2.edziennik.librus.ENDPOINT_LIBRUS_API_VIRTUAL_CLASSES
import pl.szczodrzynski.edziennik.api.v2.edziennik.librus.data.LibrusApi
import pl.szczodrzynski.edziennik.data.db.modules.teams.Team

class LibrusApiVirtualClasses(override val data: DataLibrus,
                       val onSuccess: () -> Unit) : LibrusApi(data) {
    companion object {
        const val TAG = "LibrusApiVirtualClasses"
    }

    init {
        apiGet(TAG, "VirtualClasses") { json ->
            val virtualClasses = json.getJsonArray("VirtualClasses").asJsonObjectList()

            virtualClasses?.forEach { virtualClass ->
                val id = virtualClass.getLong("Id") ?: return@forEach
                val name = virtualClass.getString("Name") ?: ""
                val teacherId = virtualClass.getJsonObject("Teacher")?.getLong("Id") ?: -1
                val code = "${data.schoolName}:$name"

                data.teamList.put(id, Team(profileId, id, name, 2, code, teacherId))
            }

            data.setSyncNext(ENDPOINT_LIBRUS_API_VIRTUAL_CLASSES, 4*DAY)
            onSuccess()
        }
    }
}