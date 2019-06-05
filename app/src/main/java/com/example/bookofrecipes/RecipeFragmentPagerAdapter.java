package com.example.bookofrecipes;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class RecipeFragmentPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<String> images;

    public RecipeFragmentPagerAdapter(FragmentManager manager, ArrayList<String> images) {
        super(manager);
        this.images = images;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return PagerFragment.newInstance(position, images);
    }

    @Override
    public int getCount() {
        return images.size();
    }
}
