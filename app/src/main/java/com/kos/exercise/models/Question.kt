package com.kos.exercise.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Question(
    @PrimaryKey
    val question_id: Long,
    val link: String,
    val answer_count: Int,
    val title: String,
    val creation_date: Long,
    @Embedded(prefix = "owner_")
    val owner: User
) {

}