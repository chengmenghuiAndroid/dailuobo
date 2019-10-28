package com.daluobo.com.ui.fragment

import android.os.Bundle
import com.daluobo.com.R
import com.daluobo.com.mvp.mvp.XFragment
import com.daluobo.com.ui.present.PShoppingCar

/**
 * 购物车
 */
class ShoppingCarFragment :XFragment<PShoppingCar>(){
    override fun lazyLoad() {
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun getLayoutId(): Int {
        return R.layout.shopping_car_fragment_layout
    }

    override fun newP(): PShoppingCar {
        return PShoppingCar()
    }

    override fun stopLoad() {
    }
}