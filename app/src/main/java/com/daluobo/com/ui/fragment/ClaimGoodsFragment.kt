package com.daluobo.com.ui.fragment

import android.os.Bundle
import com.daluobo.com.R
import com.daluobo.com.mvp.mvp.XFragment
import com.daluobo.com.ui.present.PClaimGoods

/**
 * 取货
 */
class ClaimGoodsFragment :XFragment<PClaimGoods>(){
    override fun lazyLoad() {
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun getLayoutId(): Int {
        return R.layout.claim_goods_fragment_layout
    }

    override fun newP(): PClaimGoods {
        return PClaimGoods()
    }

    override fun stopLoad() {
    }
}