package dev.ogabek.kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.imageview.ShapeableImageView
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import dev.ogabek.kotlin.R
import dev.ogabek.kotlin.model.Feed
import dev.ogabek.kotlin.model.Post

class FeedAdapter(private val context: Context, private val feeds: ArrayList<Feed>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when {
            feeds[position].stories.size > 0 -> {
                TYPE_STORY_VIEW
            }
            feeds[position].post!!.photos.isNotEmpty() -> {
                TYPE_PHOTOS_POST_VIEW
            }
            else -> {
                TYPE_SIMPLE_POST_VIEW
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_STORY_VIEW) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_feed_story, parent, false)
            StoryViewHolder(context, view)
        } else if (viewType == FeedAdapter.TYPE_PHOTOS_POST_VIEW) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_feed_post_photos, parent, false)
            PhotosPostViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_feed_post, parent, false)
            SimplePostViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val feed: Feed = feeds[position]
        if (holder is StoryViewHolder) {
            holder.recyclerView.adapter =
                StoryAdapter(context, feed.stories)
        }
        if (holder is SimplePostViewHolder) {
            val post: Post = feed.post!!
            holder.iv_profile.setImageResource(post.profile)
            holder.iv_photo.setImageResource(post.photo)
            holder.tv_fullName.setText(post.fullName)
        }

        if (holder is PhotosPostViewHolder) {
            val post: Post = feed.post!!
            holder.iv_profile.setImageResource(post.profile)
            val adapter = ViewPagerAdapter(context, post.photos)
            holder.vp_photo.adapter = adapter
            holder.wormDotsIndicator.setViewPager(holder.vp_photo)
            holder.tv_fullName.setText(post.fullName)
        }
    }

    override fun getItemCount(): Int {
        return feeds.size
    }

    private class StoryViewHolder(context: Context?, view: View) :
        RecyclerView.ViewHolder(view) {
        var recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        init {
            recyclerView.layoutManager =
                GridLayoutManager(context, 1, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private class SimplePostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_profile: ShapeableImageView = view.findViewById(R.id.iv_profile)
        var tv_fullName: TextView = view.findViewById(R.id.tv_full_name)
        var iv_photo: ImageView = view.findViewById(R.id.iv_photo)

    }

    private class PhotosPostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_profile: ShapeableImageView = view.findViewById(R.id.iv_profile)
        var tv_fullName: TextView = view.findViewById(R.id.tv_full_name)
        var vp_photo: ViewPager = view.findViewById(R.id.view_pager)
        var wormDotsIndicator: WormDotsIndicator = view.findViewById(R.id.worm_dot)

    }

    companion object {
        private const val TYPE_STORY_VIEW = 0
        private const val TYPE_SIMPLE_POST_VIEW = 1
        private const val TYPE_PHOTOS_POST_VIEW = 2
    }

}