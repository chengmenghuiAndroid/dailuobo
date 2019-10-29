package com.daluobo.com.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import com.daluobo.com.R
import com.daluobo.com.mvp.mvp.XFragment
import com.daluobo.com.ui.present.PBuyVegetable
import com.facebook.drawee.view.SimpleDraweeView
import com.stx.xhb.androidx.XBanner
import kotlinx.android.synthetic.main.buy_vegetable_fragment.*


/**
 * 买菜
 */
class BuyVegetableFragment : XFragment<PBuyVegetable>() {

    private val mBannerUrls: MutableList<String> = mutableListOf<String>()

    override fun lazyLoad() {
    }

    override fun initData(savedInstanceState: Bundle?) {
        initBanner()
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
        mBannerUrls.clear()
        mBannerUrls.add("http://photo.tuchong.com/288782/f/6826902.jpg")
        mBannerUrls.add("http://photo.tuchong.com/1002309/f/20858906.jpg")
        if (mBannerUrls.size > 0) {
            banner.setPointsIsVisible(mBannerUrls.size > 1)
            banner.setAutoPlayAble(mBannerUrls.size > 1)
        }
        banner.setData(R.layout.layout_fresco_imageview, mBannerUrls, null)
        //设置广告图片点击事件
        banner.setOnItemClickListener(XBanner.OnItemClickListener { banner, model, view, position ->
            Toast.makeText(context, "点击了第" + (position + 1) + "图片", Toast.LENGTH_SHORT).show()
        }
        )
        //加载广告图片
        banner.loadImage(XBanner.XBannerAdapter { banner, model, view, position ->
            //在此处使用图片加载框架加载图片，demo中使用glide加载，可替换成自己项目中的图片加载框架
            val draweeView = view as SimpleDraweeView
            draweeView.setImageURI(Uri.parse(model as String))
        })
    }

    /** 为了更好的体验效果建议在下面两个生命周期中调用下面的方法  */
    override fun onResume() {
        super.onResume()
        banner.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        banner.stopAutoPlay()
    }

}