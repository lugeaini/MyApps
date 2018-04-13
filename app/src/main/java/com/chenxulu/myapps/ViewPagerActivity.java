package com.chenxulu.myapps;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xulu
 */
public class ViewPagerActivity extends AppCompatActivity {
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private List<View> mItemViews;
    int count;
    private int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);

        String[] strArray = getResources().getStringArray(R.array.image_array);
        count = strArray.length;
        mItemViews = new ArrayList<>();

        for (int i = 0; i <= strArray.length + 1; i++) {
            int position;
            if (i == 0) {
                position = count - 1;
            } else if (i == strArray.length + 1) {
                position = 0;
            } else {
                position = i - 1;
            }

            ImageView imageView = new ImageView(this);
            Glide.with(this).load(strArray[position]).into(imageView);

            mItemViews.add(imageView);
        }

        viewPager.setAdapter(new MyAdapter());
        viewPager.addOnPageChangeListener(pageChangeListener);
        viewPager.setCurrentItem(1);
    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                //No operation
                case 0:
                    if (currentItem == 0) {
                        viewPager.setCurrentItem(count, false);
                    } else if (currentItem == count + 1) {
                        viewPager.setCurrentItem(1, false);
                    }
                    break;
                //start Sliding
                case 1:
                    if (currentItem == count + 1) {
                        viewPager.setCurrentItem(1, false);
                    } else if (currentItem == 0) {
                        viewPager.setCurrentItem(count, false);
                    }
                    break;
                //end Sliding
                case 2:
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
        }
    };

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mItemViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            container.addView(mItemViews.get(position));
            View view = mItemViews.get(position);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
