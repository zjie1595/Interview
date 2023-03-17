package com.zj.interview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity(), BaseBinding<VB> {

    val binding: VB by lazy(mode = LazyThreadSafetyMode.NONE) {
        getViewBinding(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(enableBackPress())
        beforeInitBinding()
        binding.initBinding()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuRes = menuRes() ?: return false
        menuInflater.inflate(menuRes, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    open fun enableBackPress() = true

    open fun beforeInitBinding() {

    }

    /**
     * 设置要加载的菜单资源id，默认为空，也就是默认不加载菜单
     */
    open fun menuRes(): Int? = null
}