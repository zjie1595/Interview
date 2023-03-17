package com.zj.interview

import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.ToastUtils
import com.zj.interview.databinding.ActivityStorageBinding
import com.zj.interview.db.interviewDao
import com.zj.interview.model.Interview
import kotlinx.coroutines.launch

class StorageActivity : BaseActivity<ActivityStorageBinding>() {

    override fun ActivityStorageBinding.initBinding() {
        supportActionBar?.title = "录入题库"
        btnAddInterview.setOnClickListener {
            val question = inputQuestion.text.toString()
            val answer = inputAnswer.text.toString()
            val interview = Interview(
                question = question,
                answer = answer
            )
            lifecycleScope.launch {
                interviewDao.insert(interview)
                ToastUtils.showLong("录入成功")
            }
        }
    }
}