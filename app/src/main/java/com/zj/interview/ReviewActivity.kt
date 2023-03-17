package com.zj.interview

import android.widget.RadioButton
import androidx.lifecycle.lifecycleScope
import com.zj.interview.databinding.ActivityReviewBinding
import com.zj.interview.db.interviewDao
import com.zj.interview.model.HALF_KNOWLEDGE
import com.zj.interview.model.Interview
import com.zj.interview.model.NO_ANSWER
import com.zj.interview.model.PERFECT_ANSWER
import kotlinx.coroutines.launch

class ReviewActivity : BaseActivity<ActivityReviewBinding>() {

    private lateinit var currentInterview: Interview

    override fun ActivityReviewBinding.initBinding() {
        supportActionBar?.title = "面试模拟"
        state.showLoading()
        lifecycleScope.launch {
            val interview = interviewDao.getRandomInterviewByStatus(2)
            if (interview == null) {
                state.showEmpty()
                return@launch
            }
            state.showContent()
            currentInterview = interview
            question.text = currentInterview.question
        }
        next.setOnClickListener {
            val status =
                when (commentGroup.findViewById<RadioButton>(commentGroup.checkedRadioButtonId).text) {
                    "没答出" -> NO_ANSWER
                    "半知半解" -> HALF_KNOWLEDGE
                    else -> PERFECT_ANSWER
                }
            currentInterview.status = status
            currentInterview.updateTime = System.currentTimeMillis()
            currentInterview.answer = inputAnswer.text.toString()
            lifecycleScope.launch {
                interviewDao.update(currentInterview)
            }
        }
    }
}