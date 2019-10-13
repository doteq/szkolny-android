/*
 * Copyright (c) Kuba Szczodrzyński 2019-10-5.
 */

package pl.szczodrzynski.edziennik.api.v2.mobidziennik

import pl.szczodrzynski.edziennik.api.v2.*
import pl.szczodrzynski.edziennik.api.v2.librus.ENDPOINT_LIBRUS_API_PUSH_CONFIG
import pl.szczodrzynski.edziennik.api.v2.models.Feature

const val ENDPOINT_MOBIDZIENNIK_API_MAIN                = 1000
const val ENDPOINT_MOBIDZIENNIK_WEB_MESSAGES_INBOX      = 2011
const val ENDPOINT_MOBIDZIENNIK_WEB_MESSAGES_SENT       = 2012
const val ENDPOINT_MOBIDZIENNIK_WEB_MESSAGES_ALL        = 2019
const val ENDPOINT_MOBIDZIENNIK_WEB_CALENDAR            = 2020
const val ENDPOINT_MOBIDZIENNIK_WEB_GRADES              = 2030
const val ENDPOINT_MOBIDZIENNIK_WEB_NOTICES             = 2040
const val ENDPOINT_MOBIDZIENNIK_WEB_ATTENDANCE          = 2050
const val ENDPOINT_MOBIDZIENNIK_WEB_MANUALS             = 2100
const val ENDPOINT_MOBIDZIENNIK_WEB_ACCOUNT_EMAIL       = 2200
const val ENDPOINT_MOBIDZIENNIK_API2_MAIN               = 3000

val MobidziennikFeatures = listOf(
        // always synced
        Feature(LOGIN_TYPE_MOBIDZIENNIK, FEATURE_ALWAYS_NEEDED, listOf(
                ENDPOINT_MOBIDZIENNIK_API_MAIN to LOGIN_METHOD_MOBIDZIENNIK_WEB,
                ENDPOINT_MOBIDZIENNIK_WEB_ACCOUNT_EMAIL to LOGIN_METHOD_MOBIDZIENNIK_WEB
        ), listOf(LOGIN_METHOD_MOBIDZIENNIK_WEB)),

        // push config
        Feature(LOGIN_TYPE_MOBIDZIENNIK, FEATURE_PUSH_CONFIG, listOf(
                ENDPOINT_MOBIDZIENNIK_API2_MAIN to LOGIN_METHOD_MOBIDZIENNIK_API2
        ), listOf(LOGIN_METHOD_MOBIDZIENNIK_API2)).withShouldSync { data ->
                data.app.appConfig.fcmTokens[LOGIN_TYPE_MOBIDZIENNIK]?.second?.contains(data.profileId) == false
        },





        /**
         * Agenda - "API" + web scraping.
         */
        Feature(LOGIN_TYPE_MOBIDZIENNIK, FEATURE_AGENDA, listOf(
                ENDPOINT_MOBIDZIENNIK_API_MAIN to LOGIN_METHOD_MOBIDZIENNIK_WEB,
                ENDPOINT_MOBIDZIENNIK_WEB_CALENDAR to LOGIN_METHOD_MOBIDZIENNIK_WEB
        ), listOf(LOGIN_METHOD_MOBIDZIENNIK_WEB, LOGIN_METHOD_MOBIDZIENNIK_WEB)),
        /**
         * Grades - "API" + web scraping.
         */
        Feature(LOGIN_TYPE_MOBIDZIENNIK, FEATURE_GRADES, listOf(
                ENDPOINT_MOBIDZIENNIK_API_MAIN to LOGIN_METHOD_MOBIDZIENNIK_WEB,
                ENDPOINT_MOBIDZIENNIK_WEB_GRADES to LOGIN_METHOD_MOBIDZIENNIK_WEB
        ), listOf(LOGIN_METHOD_MOBIDZIENNIK_WEB, LOGIN_METHOD_MOBIDZIENNIK_WEB)),
        /**
         * Behaviour - "API" + web scraping.
         */
        Feature(LOGIN_TYPE_MOBIDZIENNIK, FEATURE_BEHAVIOUR, listOf(
                ENDPOINT_MOBIDZIENNIK_API_MAIN to LOGIN_METHOD_MOBIDZIENNIK_WEB,
                ENDPOINT_MOBIDZIENNIK_WEB_NOTICES to LOGIN_METHOD_MOBIDZIENNIK_WEB
        ), listOf(LOGIN_METHOD_MOBIDZIENNIK_WEB, LOGIN_METHOD_MOBIDZIENNIK_WEB)),
        // attendance TODO implement website attendance scraping
        /*Feature(LOGIN_TYPE_MOBIDZIENNIK, FEATURE_ATTENDANCE, listOf(
                ENDPOINT_MOBIDZIENNIK_WEB_ATTENDANCE to LOGIN_METHOD_MOBIDZIENNIK_WEB
        ), listOf(LOGIN_METHOD_MOBIDZIENNIK_WEB)),*/





        /**
         * Messages inbox - using web scraper.
         */
        Feature(LOGIN_TYPE_MOBIDZIENNIK, FEATURE_MESSAGES_INBOX, listOf(
                ENDPOINT_MOBIDZIENNIK_WEB_MESSAGES_INBOX to LOGIN_METHOD_MOBIDZIENNIK_WEB,
                ENDPOINT_MOBIDZIENNIK_WEB_MESSAGES_ALL to LOGIN_METHOD_MOBIDZIENNIK_WEB
        ), listOf(LOGIN_METHOD_MOBIDZIENNIK_WEB)),
        /**
         * Messages sent - using web scraper.
         */
        Feature(LOGIN_TYPE_MOBIDZIENNIK, FEATURE_MESSAGES_SENT, listOf(
                ENDPOINT_MOBIDZIENNIK_WEB_MESSAGES_SENT to LOGIN_METHOD_MOBIDZIENNIK_WEB,
                ENDPOINT_MOBIDZIENNIK_WEB_MESSAGES_ALL to LOGIN_METHOD_MOBIDZIENNIK_WEB
        ), listOf(LOGIN_METHOD_MOBIDZIENNIK_WEB))

        // lucky number possibilities
        // all endpoints that may supply the lucky number
        /*Feature(LOGIN_TYPE_MOBIDZIENNIK, FEATURE_LUCKY_NUMBER, listOf(
                ENDPOINT_MOBIDZIENNIK_WEB_MANUALS to LOGIN_METHOD_MOBIDZIENNIK_WEB
        ), listOf(LOGIN_METHOD_MOBIDZIENNIK_WEB)).apply { priority = 10 },

        Feature(LOGIN_TYPE_MOBIDZIENNIK, FEATURE_LUCKY_NUMBER, listOf(
                ENDPOINT_MOBIDZIENNIK_WEB_CALENDAR to LOGIN_METHOD_MOBIDZIENNIK_WEB
        ), listOf(LOGIN_METHOD_MOBIDZIENNIK_WEB)).apply { priority = 3 },

        Feature(LOGIN_TYPE_MOBIDZIENNIK, FEATURE_LUCKY_NUMBER, listOf(
                ENDPOINT_MOBIDZIENNIK_WEB_GRADES to LOGIN_METHOD_MOBIDZIENNIK_WEB
        ), listOf(LOGIN_METHOD_MOBIDZIENNIK_WEB)).apply { priority = 2 },

        Feature(LOGIN_TYPE_MOBIDZIENNIK, FEATURE_LUCKY_NUMBER, listOf(
                ENDPOINT_MOBIDZIENNIK_WEB_NOTICES to LOGIN_METHOD_MOBIDZIENNIK_WEB
        ), listOf(LOGIN_METHOD_MOBIDZIENNIK_WEB)).apply { priority = 1 },

        Feature(LOGIN_TYPE_MOBIDZIENNIK, FEATURE_LUCKY_NUMBER, listOf(
                ENDPOINT_MOBIDZIENNIK_WEB_ATTENDANCE to LOGIN_METHOD_MOBIDZIENNIK_WEB
        ), listOf(LOGIN_METHOD_MOBIDZIENNIK_WEB)).apply { priority = 4 }*/

)