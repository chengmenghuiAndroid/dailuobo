package com.daluobo.com.ui.fragment

import android.os.Bundle
import com.daluobo.com.R
import com.daluobo.com.mvp.mvp.XFragment
import com.daluobo.com.ui.present.PMineFragment

class MineFragment :XFragment<PMineFragment>(){
    override fun lazyLoad() {
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun getLayoutId(): Int {
        return R.layout.mine_fragment_layout
    }

    override fun newP(): PMineFragment {
        return PMineFragment()
    }

    override fun stopLoad() {
    }
}