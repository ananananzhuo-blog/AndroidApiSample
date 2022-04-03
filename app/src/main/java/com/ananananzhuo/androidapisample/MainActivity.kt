package com.ananananzhuo.androidapisample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ananananzhuo.androidapisample.activity.RecycleDemoActivity
import com.ananananzhuo.androidapisample.activity.RecycleViewListAdapterActivity
import com.ananananzhuo.androidapisample.activity.ViewPager2Activity
import com.ananananzhuo.mvvm.activity.CustomAdapterActivity
import com.ananananzhuo.mvvm.bean.bean.ItemData
import com.ananananzhuo.mvvm.callback.CallData

class MainActivity : CustomAdapterActivity() {
    override fun getAdapterDatas(): MutableList<ItemData> = mutableListOf(
        ItemData(
            title = "RecycleViewListAdapter的使用",
            content = "RecycleViewListAdapter的使用",
            icon = R.drawable.kabuda,
            showIcon = true,
            activity = RecycleViewListAdapterActivity::class.java,
            lamCallback = {
                toActivity(it)
            }
        ),
        ItemData(
            title = "ViewPager几种好看的效果呈现",
            content = "使用ViewPager2.PageTransformer呈现几种好看的ViewPager2切换效果",
            icon = R.drawable.kabuda,
            showIcon = true,
            activity = ViewPager2Activity::class.java,
            lamCallback = {
                toActivity(it)
            }
        ),
        ItemData(
            title = "RecycleView使用",
            content = "关于Recycle的一些新东西",
            icon = R.drawable.kabuda,
            showIcon = true,
            activity = RecycleDemoActivity::class.java,
            lamCallback = {
                toActivity(it)
            }
        ),
    )

    private fun toActivity(it: CallData) {
        startActivity(Intent(this, it.itemData.activity))
    }

    override fun showFirstItem(): Boolean {
        return false
    }
}