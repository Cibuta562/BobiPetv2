package com.example.insights;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import pl.droidsonroids.gif.GifImageView;


public class ImageAdapter extends PagerAdapter {
    private Context mContext;
    private int[] mImageIds = new int []{R.drawable.bobi_pet1,R.drawable.bobi_pet2,R.drawable.bobi_pet3,R.drawable.bobi_pet4,R.drawable.bobi_pet5};
    ImageView bobiChoice;
    Intent intent;
    Bitmap image;

    ImageAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mImageIds.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        GifImageView imageView = (GifImageView) new GifImageView(mContext);
        imageView.bringToFront();
        imageView.setScaleType(GifImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(mImageIds[position]);
        container.addView(imageView, 0);
       /* if (position==0)
        {
          bobiChoice.setImageResource(mImageIds[0]);
        }
        else if (position==1)
        {
            bobiChoice.setImageResource(mImageIds[1]);
        }
        else if (position==2)
        {
            bobiChoice.setImageResource(mImageIds[2]);
        }
        else if (position==3)
        {
            bobiChoice.setImageResource(mImageIds[3]);
        }
        else
        {
            bobiChoice.setImageResource(mImageIds[4]);
        }
        //bobiChoice.buildDrawingCache();
        //image=bobiChoice.getDrawingCache();*/
        return imageView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((GifImageView) object);
    }
}