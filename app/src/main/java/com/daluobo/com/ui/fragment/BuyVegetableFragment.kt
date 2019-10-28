package com.daluobo.com.ui.fragment

import android.os.Bundle
import com.daluobo.com.R
import com.daluobo.com.mvp.mvp.XFragment
import com.daluobo.com.ui.present.PBuyVegetable

/**
 * 买菜
 */
class BuyVegetableFragment :XFragment<PBuyVegetable>() {
    override fun lazyLoad() {
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun getLayoutId(): Int {
        return R.layout.buy_vegetable_fragment
    }

    override fun newP(): PBuyVegetable {
        return PBuyVegetable()
    }

    override fun stopLoad() {
    }

}