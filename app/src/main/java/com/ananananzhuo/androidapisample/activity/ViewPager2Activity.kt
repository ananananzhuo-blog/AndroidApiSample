package com.ananananzhuo.androidapisample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ananananzhuo.androidapisample.R
import com.ananananzhuo.androidapisample.ToolbarActivity
import com.ananananzhuo.androidapisample.databinding.ActivityViewPager2Binding
import kotlin.math.abs

/**
 * 功能：ViewPager2的几种切换方法实现
 * Created by mayong on 2022/3/27.
 */
class ViewPager2Activity : ToolbarActivity<ActivityViewPager2Binding>() {
    val resIds = arrayListOf(R.drawable.picone, R.drawable.pictwo, R.drawable.picthree)
    val effectItem = arrayOf("风车", "景深", "推压", "方盒", "旋转")
    private val pageTransformer = ViewPager2.PageTransformer { page, position ->
        when{
            mBinding.cbFengche.isChecked->{//风车
                fengche(page,position)
            }
            mBinding.cbJingshen.isChecked->{
                jingshen(page, position)
            }
            mBinding.cbTuiya.isChecked->{
                tuiya(page, position)
            }
            mBinding.cbFanghe.isChecked->{
                fanghe(page, position)
            }
            mBinding.cbXuanzhuan.isChecked->{
                xuanzhuan(page, position)
            }
        }
    }

    private fun fengche(page: View,position:Float) {
        val (width, height) = listOf(page.width, page.height)
        page.apply {
            when {
                position <= 0 -> {//滑动过程中靠左的item
                    pivotX = width / 2f
                    pivotY = height + 300f
                    rotation = 90 * position / 4
                }
                position < 1 -> {//滑动过程中靠右的item
                    pivotX = width / 2f
                    pivotY = height + 300f
                    rotation = 90 * position / 4
                }
            }
        }
    }

    private fun jingshen(page: View,position:Float) {
        page.apply {
            when {
                position <= -1.0f -> {//item小于等于-1则恢复正常
                    pivotX = width.toFloat()
                    pivotY = height / 2f
                    alpha = 1f
                    scaleY = 1f
                }
                position > -1 && position < -0.5f -> {
                    pivotX = width.toFloat()
                    pivotY = height / 2f
                    alpha = 0.8f
                    scaleY = 0.8f
                }
                position >= -0.5f && position <= 0f -> {
                    pivotX = width.toFloat()
                    pivotY = height / 2f
                    alpha = 0.4f*(position+0.5f)+0.8f
                    scaleY = 0.4f*(position+0.5f)+0.8f
                }
                position>0&&position<=0.5->{
                    pivotX = 0f
                    pivotY = height / 2f
                    alpha = -0.4f*(position-0.5f)+0.8f
                    scaleY = -0.4f*(position-0.5f)+0.8f
                }
                position>0.5&& position<1->{
                    pivotX = 0f
                    pivotY = height / 2f
                    alpha = 0.8f
                    scaleY = -0.8f
                }
                position>=1->{
                    pivotX = 0f
                    pivotY = height / 2f
                    alpha = 1f
                    scaleY = 1f
                }
            }
        }
    }
    private fun tuiya(page: View,position:Float) {
        page.apply {
            when{
                position< -1f->{
                    pivotX = width.toFloat()
                    scaleX = 1f
                }
                position<0->{
                    pivotX = width.toFloat()
                    scaleX = 1+position
                }
                position>0->{
                    pivotX = 0f
                    scaleX = 1-position
                }
                position>1->{
                    pivotX = 0f
                    scaleX = 1f
                }
            }
        }
    }
    private fun fanghe(page: View,position:Float) {
        page.apply {
            pivotX = if (position < 0f) width.toFloat() else 0f
            pivotY = height*0.5f
            rotationY=90f*position
        }
    }
    private fun xuanzhuan(page: View,position:Float) {
        page.apply {
            when{
                position<-1->{
                    translationX = position*width
                    pivotX= 0f
                    pivotY =0f
                    rotationY = 0f
                }
                position<=0->{
//                    translationX = abs(position)*width
//                    pivotX= abs(position)*width
//                    pivotY = height/2f
//                    rotationY = -90f*position
                    pivotX= 0f
                    pivotY = height/2f
                    rotationY = -90f*position
                    translationX = abs(position)*width+30

                }
                position<1->{
                    pivotX= width.toFloat()
                    pivotY = height/2f
                    rotationY = -90f*position+30
                    translationX = -position*width-30

                }
                position>1->{
                    translationX = width.toFloat()
                    pivotX= 0f
                    pivotY =0f
                    rotationY = 0f
                }
            }
        }
    }

    override fun initBinding(): ActivityViewPager2Binding =
        ActivityViewPager2Binding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        mBinding.pager.setPageTransformer(pageTransformer)//设置切换带效果代码
        mBinding.pager.adapter = object : RecyclerView.Adapter<PagerViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_fullimage, parent, false)
                return PagerViewHolder(view)
            }

            override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
                holder.iv.setImageResource(resIds[position % 3])
            }

            override fun getItemCount(): Int = 100
        }
    }

}

private class PagerViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
    val iv = itemView.findViewById<ImageView>(R.id.iv)

}
