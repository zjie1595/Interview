package com.zj.interview.model

import androidx.room.Entity
import androidx.room.PrimaryKey

const val PERFECT_ANSWER = 0
const val HALF_KNOWLEDGE = 1
const val NO_ANSWER = 2

const val UNKNOWN_INTERVIEW_GROUP = "未分类"

@Entity
data class Interview(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var question: String,
    var answer: String,
    var createTime: Long = System.currentTimeMillis(),
    var updateTime: Long = System.currentTimeMillis(),
    var status: Int = NO_ANSWER,
    var group: String = UNKNOWN_INTERVIEW_GROUP
)
