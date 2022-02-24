package dev.ogabek.java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

import dev.ogabek.java.R;
import dev.ogabek.java.model.Photos;

public class ViewPagerAdapter extends PagerAdapter {

    List<Photos> photos;
    Context context;

    public ViewPagerAdapter(Context context, List<Photos> photos) {
        this.context = context;
        this.photos = photos;
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Photos photo = photos.get(position);
        View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.item_vp, container, false);
        ImageView imageView = view.findViewById(R.id.iv_photo);
        imageView.setImageResource(photo.getPhoto());
        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
