package com.PowerZone.PowerZone.Config

import com.PowerZone.PowerZone.Models.Activity
import com.PowerZone.PowerZone.Models.Role
import com.PowerZone.PowerZone.Models.User
import com.PowerZone.PowerZone.Services.ActivityService
import com.PowerZone.PowerZone.Services.UserService
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime
import java.util.UUID

@Bean
fun dataInitializer(
        activityService: ActivityService,
        userService: UserService
) = ApplicationRunner {
    val activities = listOf(
        Activity(
            title = "Clase de Yoga",
            description = "Sesión de yoga para mejorar la flexibilidad y la relajación.",
            startDateTime = LocalDateTime.now().withHour(9).withMinute(0),
            endDateTime = LocalDateTime.now().withHour(10).withMinute(0)
        ),
        Activity(
            title = "Entrenamiento de Fuerza",
            description = "Entrenamiento con pesas para aumentar la fuerza y la masa muscular.",
            startDateTime = LocalDateTime.now().withHour(11).withMinute(0),
            endDateTime = LocalDateTime.now().withHour(12).withMinute(0)
        ),
        Activity(
            title = "Clase de Spinning",
            description = "Clase de spinning para mejorar la resistencia cardiovascular.",
            startDateTime = LocalDateTime.now().plusDays(1).withHour(8).withMinute(0),
            endDateTime = LocalDateTime.now().plusDays(1).withHour(9).withMinute(0)
        ),
        Activity(
            title = "Pilates",
            description = "Sesión de pilates para fortalecer el core y mejorar la postura.",
            startDateTime = LocalDateTime.now().plusDays(1).withHour(17).withMinute(0),
            endDateTime = LocalDateTime.now().plusDays(1).withHour(18).withMinute(0)
        ),
        Activity(
            title = "Zumba",
            description = "Clase de Zumba para quemar calorías y divertirse al ritmo de la música.",
            startDateTime = LocalDateTime.now().plusDays(2).withHour(19).withMinute(0),
            endDateTime = LocalDateTime.now().plusDays(2).withHour(20).withMinute(0)
        )
    )

    activities.forEach { activityService.newActivity(it) }


    val users = mutableSetOf(
            User(
                    id = UUID.randomUUID().toString(),
                    name = "alejandro",
                    email = "ale@gmail.com",
                    password = "1234",
                    role = Role.ADMIN.toString()
            ),
            User(
                    id = UUID.randomUUID().toString(),
                    name = "pepe",
                    email = "pepe@gmail.com",
                    password = "pass1",
            ),
            User(
                    id = UUID.randomUUID().toString(),
                    name = "paco",
                    email = "paco@gmail.com",
                    password = "pass2",
            ),
            User(
                    id = UUID.randomUUID().toString(),
                    name = "manolo",
                    email = "manolo@gmail.com",
                    password = "pass3",
            ),
    )

    users.forEach { userService.createUser(it) }

}
