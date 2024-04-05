package edu.jsu.mcis.cs408.lab5a;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabLayoutAdapter extends FragmentStateAdapter {

    public static final int NUM_TABS = 3;

    public TabLayoutAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new TipCalculatorFragment();
                break;
            case 1:
                fragment = new TemperatureConverterFragment();
                break;
            case 2:
                fragment = new DistanceConverterFragment();
                break;
            default:
                fragment = new Fragment();
                break;
        }
        Bundle args = new Bundle();
        args.putInt(TipCalculatorFragment.ARG_ID, position + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return NUM_TABS;
    }

}
