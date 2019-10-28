package com.daluobo.com.ui.fragment

import android.os.Bundle
import com.daluobo.com.R
import com.daluobo.com.mvp.mvp.XFragment
import com.daluobo.com.ui.present.PSortFragment

/**
 * 分类
 */
class SortFragment :XFragment<PSortFragment>() {
    override fun lazyLoad() {
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun getLayoutId(): Int {
        return R.layout.sort_fragment_layout
    }

    override fun newP(): PSortFragment {
        return PSortFragment()
    }

    override fun stopLoad() {
    }
}