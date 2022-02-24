package dev.ogabek.java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.imageview.ShapeableImageView;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;

import dev.ogabek.java.R;
import dev.ogabek.java.model.Feed;
import dev.ogabek.java.model.Post;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final ArrayList<Feed> feeds;

    private static final int TYPE_STORY_VIEW = 0;
    private static final int TYPE_SIMPLE_POST_VIEW = 1;
    private static final int TYPE_PHOTOS_POST_VIEW = 2;

    public FeedAdapter(Context context, ArrayList<Feed> feeds) {
        this.context = context;
        this.feeds = feeds;
    }

    @Override
    public int getItemViewType(int position) {
        if (feeds.get(position).getStories().size() > 0) {
            return TYPE_STORY_VIEW;
        } else if (feeds.get(position).getPost().getPhotos().size() > 0) {
            return TYPE_PHOTOS_POST_VIEW;
        } else {
            return TYPE_SIMPLE_POST_VIEW;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_STORY_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_story, parent, false);
            return new StoryViewHolder(context, view);
        } else if (viewType == TYPE_PHOTOS_POST_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_post_photos, parent, false);
            return new PhotosPostViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_post, parent, false);
            return new SimplePostViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Feed feed = feeds.get(position);

        if (holder instanceof StoryViewHolder) {
            ((StoryViewHolder) holder).recyclerView.setAdapter(new StoryAdapter(context, feed.getStories()));
        }

        if (holder instanceof SimplePostViewHolder) {
            Post post = feed.getPost();

            ((SimplePostViewHolder) holder).iv_profile.setImageResource(post.getProfile());
            ((SimplePostViewHolder) holder).iv_photo.setImageResource(post.getPhoto());
            ((SimplePostViewHolder) holder).tv_fullName.setText(post.getFullName());

        }

        if (holder instanceof PhotosPostViewHolder) {
            Post post = feed.getPost();

            ((PhotosPostViewHolder) holder).iv_profile.setImageResource(post.getProfile());
            ViewPagerAdapter adapter = new ViewPagerAdapter(context, feed.getPost().getPhotos());
            ((PhotosPostViewHolder) holder).vp_photo.setAdapter(adapter);
            ((PhotosPostViewHolder) holder).wormDotsIndicator.setViewPager(((PhotosPostViewHolder) holder).vp_photo);
            ((PhotosPostViewHolder) holder).tv_fullName.setText(post.getFullName());
        }

    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    private static class StoryViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;

        public StoryViewHolder(Context context, View view) {
            super(view);

            recyclerView = view.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(context, 1, LinearLayoutManager.HORIZONTAL, false));

        }
    }

    private static class SimplePostViewHolder extends RecyclerView.ViewHolder {

        ShapeableImageView iv_profile;
        TextView tv_fullName;
        ImageView iv_photo;

        public SimplePostViewHolder(View view) {
            super(view);
            iv_profile = view.findViewById(R.id.iv_profile);
            tv_fullName = view.findViewById(R.id.tv_full_name);
            iv_photo = view.findViewById(R.id.iv_photo);

        }
    }

    private static class PhotosPostViewHolder extends RecyclerView.ViewHolder {

        ShapeableImageView iv_profile;
        TextView tv_fullName;
        ViewPager vp_photo;
        WormDotsIndicator wormDotsIndicator;

        public PhotosPostViewHolder(View view) {
            super(view);

            iv_profile = view.findViewById(R.id.iv_profile);
            tv_fullName = view.findViewById(R.id.tv_full_name);
            vp_photo = view.findViewById(R.id.view_pager);
            wormDotsIndicator = view.findViewById(R.id.worm_dot);

        }
    }
}
