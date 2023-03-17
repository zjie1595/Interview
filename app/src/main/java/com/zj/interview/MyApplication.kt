package com.zj.interview

import android.app.Application
import android.content.Context
import com.drake.brv.utils.BRV
import com.drake.statelayout.StateConfig
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.tencent.mmkv.MMKV

lateinit var myApplicationContext: Context

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        myApplicationContext = this
        MMKV.initialize(this)
        initBrv()
    }

    private fun initBrv() {
        BRV.modelId = BR.m
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { _, _ -> MaterialHeader(this) }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { _, _ -> ClassicsFooter(this) }
        StateConfig.apply {
            emptyLayout = R.layout.layout_empty
            errorLayout = R.layout.layout_error
            loadingLayout = R.layout.layout_loading

            setRetryIds(R.id.msg)

            onLoading {
                // 此生命周期可以拿到LoadingLayout创建的视图对象, 可以进行动画设置或点击事件.
            }
        }
    }
}