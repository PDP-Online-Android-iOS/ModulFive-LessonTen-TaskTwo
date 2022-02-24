package dev.ogabek.kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import dev.ogabek.kotlin.R
import dev.ogabek.kotlin.model.Photos

class ViewPagerAdapter(var context: Context, var photos: List<Photos>) : PagerAdapter() {

    override fun getCount(): Int {
        return photos.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val photo: Photos = photos[position]
        val view: View = LayoutInflater.from(context.applicationContext).inflate(R.layout.item_vp, container, false)
        val imageView = view.findViewById<ImageView>(R.id.iv_photo)
        imageView.setImageResource(photo.photo)
        val viewPager = container as ViewPager
        viewPager.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager = container as ViewPager
        val view = `object` as View
        viewPager.removeView(view)
    }

}