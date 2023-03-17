package com.zj.interview.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.zj.interview.model.Interview

@Dao
interface InterviewDao {

    /**
     * 向数据库中插入一条 Interview 记录
     */
    @Insert
    suspend fun insert(interview: Interview)

    /**
     * 从数据库中删除一条 Interview 记录
     */
    @Insert
    suspend fun delete(interview: Interview)

    /**
     * 按照指定的分页查询条件从数据库中查询 group 字段值为 group 的 Interview 记录，按照 updateTime 倒序排列，限制每页查询 50 条记录，查询结果从 offset 处开始
     */
    @Query("select * from interview where `group` = :group order by updateTime desc")
    fun getInterviewsByGroupPaged(group: String): PagingSource<Int, Interview>

    /**
     * 从数据库中按照指定的 status 条件随机查询一条 Interview 记录，并按照随机数乘以 status 倒序排列
     */
    @Query("SELECT * FROM Interview WHERE `status` = :status ORDER BY RANDOM() * `status` DESC LIMIT 1")
    suspend fun getRandomInterviewByStatus(status: Int): Interview?

    /**
     * 更新数据库中的一条 Interview 记录
     */
    @Update
    suspend fun update(interview: Interview)
}