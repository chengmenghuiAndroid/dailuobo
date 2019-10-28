package com.daluobo.com.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.daluobo.com.R
import com.daluobo.com.mvp.mvp.XFragment
import com.daluobo.com.ui.present.PBuyVegetable
import com.google.gson.Gson
import com.stx.xhb.androidx.XBanner
import kotlinx.android.synthetic.main.buy_vegetable_fragment.*
import okhttp3.Call
import java.util.ArrayList

/**
 * 买菜
 */
class BuyVegetableFragment :XFragment<PBuyVegetable>() {


    private val mBannerUrls: MutableList<String> = mutableListOf<String>()

    override fun lazyLoad() {
    }

    override fun initData(savedInstanceState: Bundle?) {
        initBanner()
        mBannerUrls.clear()
        mBannerUrls.add("https://photo.tuchong.com/288782/f/6826902.jpg")
        mBannerUrls.add("https://photo.tuchong.com/1002309/f/20858906.jpg")
    }

    override fun getLayoutId(): Int {
        return R.layout.buy_vegetable_fragment
    }

    override fun newP(): PBuyVegetable {
        return PBuyVegetable()
    }

    override fun stopLoad() {
    }


    /**
     * 初始化XBanner
     */
    private fun initBanner() {
        //设置广告图片点击事件
        banner .setOnItemClickListener(XBanner.OnItemClickListener { banner, model, view, position ->
            Toast.makeText(context, "点击了第" + (position + 1) + "图片", Toast.LENGTH_SHORT).show() }
        )
        //加载广告图片
        banner.loadImage(XBanner.XBannerAdapter { banner, model, view, position ->
            //在此处使用图片加载框架加载图片，demo中使用glide加载，可替换成自己项目中的图片加载框架
            Glide.with(this).load(banner).placeholder(R.drawable.default_image).error(R.drawable.default_image).into(view as ImageView)
        })
    }

}