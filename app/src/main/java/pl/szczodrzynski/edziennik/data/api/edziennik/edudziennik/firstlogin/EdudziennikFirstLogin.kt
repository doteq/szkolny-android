/*
 * Copyright (c) Kacper Ziubryniewicz 2019-12-22
 */

package pl.szczodrzynski.edziennik.data.api.edziennik.edudziennik.firstlogin

import org.greenrobot.eventbus.EventBus
import pl.szczodrzynski.edziennik.data.api.Regexes.EDUDZIENNIK_ACCOUNT_NAME_START
import pl.szczodrzynski.edziennik.data.api.Regexes.EDUDZIENNIK_STUDENTS_START
import pl.szczodrzynski.edziennik.data.api.edziennik.edudziennik.DataEdudziennik
import pl.szczodrzynski.edziennik.data.api.edziennik.edudziennik.data.EdudziennikWeb
import pl.szczodrzynski.edziennik.data.api.edziennik.edudziennik.login.EdudziennikLoginWeb
import pl.szczodrzynski.edziennik.data.api.events.FirstLoginFinishedEvent
import pl.szczodrzynski.edziennik.data.db.modules.profiles.Profile
import pl.szczodrzynski.edziennik.fixName
import pl.szczodrzynski.edziennik.get
import pl.szczodrzynski.edziennik.getShortName
import pl.szczodrzynski.edziennik.utils.Utils

class EdudziennikFirstLogin(val data: DataEdudziennik, val onSuccess: () -> Unit) {
    companion object {
        private const val TAG = "EdudziennikFirstLogin"
    }

    private val web = EdudziennikWeb(data)
    private val profileList = mutableListOf<Profile>()

    init {
        EdudziennikLoginWeb(data) {
            web.webGet(TAG, "") { text ->
                val accountName = EDUDZIENNIK_ACCOUNT_NAME_START.find(text)?.get(1)?.fixName()

                EDUDZIENNIK_STUDENTS_START.findAll(text).forEach {
                    val studentId = it[1]
                    val studentName = it[2].fixName()

                    if (studentId.isBlank() || studentName.isBlank()) return@forEach

                    val profile = Profile()
                    profile.studentNameLong = studentName
                    profile.studentNameShort = studentName.getShortName()
                    profile.accountNameLong = if (studentName == accountName) null else accountName
                    profile.studentSchoolYear = Utils.getCurrentSchoolYear()
                    profile.name = studentName
                    profile.subname = data.loginEmail
                    profile.empty = true
                    profile.putStudentData("studentId", studentId)
                    profileList.add(profile)
                }

                EventBus.getDefault().post(FirstLoginFinishedEvent(profileList, data.loginStore))
                onSuccess()
            }
        }
    }
}