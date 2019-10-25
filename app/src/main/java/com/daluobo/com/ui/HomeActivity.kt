package com.daluobo.com.ui

import android.os.Bundle
import com.daluobo.com.R
import com.daluobo.com.mvp.mvp.XActivity
import com.daluobo.com.ui.present.PHomeActivity

 class HomeActivity : XActivity<PHomeActivity>() {

    override fun newP(): PHomeActivity {
       return PHomeActivity()
    }

    override fun initData(savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main_home
    }

}