package com.zj.interview

import android.content.Intent
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.tencent.mmkv.MMKV
import com.zj.interview.databinding.ActivityMainBinding
import com.zj.interview.db.interviewDao
import com.zj.interview.model.UNKNOWN_INTERVIEW_GROUP
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var currentGroup: String

    override fun ActivityMainBinding.initBinding() {
        currentGroup =
            MMKV.defaultMMKV().decodeString("interview_group", UNKNOWN_INTERVIEW_GROUP)!!
        val interviewAdapter = InterviewAdapter()
        rvInterview.adapter = interviewAdapter
        lifecycleScope.launch {
            Pager(PagingConfig(pageSize = 50)) {
                interviewDao.getInterviewsByGroupPaged(currentGroup)
            }.flow.collectLatest {
                interviewAdapter.submitData(it)
            }
        }
        addInterview.setOnClickListener {
            startActivity(Intent(this@MainActivity, StorageActivity::class.java))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.review) {
            startActivity(Intent(this, ReviewActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun menuRes(): Int {
        return R.menu.home
    }

    override fun enableBackPress(): Boolean {
        return false
    }
}