package life.league.challenge.kotlin.helper

import life.league.challenge.kotlin.model.User

fun List<User>.findUser(id: Int): User? = find { it.Id == id }