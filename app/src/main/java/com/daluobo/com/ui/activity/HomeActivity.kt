package com.daluobo.com.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.daluobo.com.R
import com.daluobo.com.core.flycotablayout.listener.CustomTabEntity
import com.daluobo.com.core.flycotablayout.listener.OnTabSelectListener
import com.daluobo.com.entity.TabEntity
import com.daluobo.com.mvp.mvp.XActivity
import com.daluobo.com.ui.fragment.*
import com.daluobo.com.ui.present.PHomeActivity
import kotlinx.android.synthetic.main.activity_main_home.*

class HomeActivity : XActivity<PHomeActivity>() {

     private val mFragments = ArrayList<Fragment>()
     private val mTitles = arrayOf("菜单", "分类", "购物车", "取货", "我的")
     private val mTabEntities = ArrayList<CustomTabEntity>()
     private val mIconUnselectedIds = intArrayOf(R.drawable.home_normal, R.drawable.home_normal, R.drawable.home_normal, R.drawable.home_normal, R.drawable.home_normal)
     private val mIconSelectIds = intArrayOf(R.drawable.home_select, R.drawable.home_select, R.drawable.home_select,
             R.drawable.home_select, R.drawable.home_select)

    override fun newP(): PHomeActivity {
       return PHomeActivity()
    }

    override fun initData(savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        mFragments.clear()
        mFragments.add(BuyVegetableFragment())
        mFragments.add(SortFragment())
        mFragments.add(ShoppingCarFragment())
        mFragments.add(ClaimGoodsFragment())
        mFragments.add(MineFragment())

        for (i in mTitles.indices) {
            mTabEntities.add(TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectedIds[i]))
        }

        tl_common_tabLayout.setTabData(mTabEntities)

        tl_common_tabLayout.setOnTabSelectListener(object :OnTabSelectListener{
            override fun onTabSelect(position: Int) {
            }

            override fun onTabReselect(position: Int) {

            }

        })
        vp_2.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                tl_common_tabLayout.currentTab = position
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        vp_2.adapter = MyPagerAdapter(supportFragmentManager)
        vp_2.currentItem = 0
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main_home
    }

    private inner class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getCount(): Int {
            return mFragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mTitles[position]
        }

        override fun getItem(position: Int): Fragment {
            return mFragments[position]
        }
    }


}