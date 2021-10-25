package fr.android.tennistrackerv2.Fragment.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

import fr.android.tennistrackerv2.Callback.ISendDataFragment;
import fr.android.tennistrackerv2.Fragment.LocationFragment;
import fr.android.tennistrackerv2.Fragment.MatchFragment;
import fr.android.tennistrackerv2.Fragment.PictureFragment;
import fr.android.tennistrackerv2.Model.Club;
import fr.android.tennistrackerv2.Model.Statistique;
import fr.android.tennistrackerv2.Model.Upload;

public class FragmentAdapter extends FragmentStateAdapter {
    Club club1;
    Club club2;
    Statistique stats;
    List<Upload> uploads;
    ISendDataFragment iSendDataFragment;

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, Club club1, Club club2, Statistique statistique) {
        super(fragmentManager, lifecycle);
        this.club1 = club1;
        this.club2 = club2;
        this.stats = statistique;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 1:
                fragment = new LocationFragment();
                return fragment;
            case 2:
                fragment = new PictureFragment();
                return fragment;
        }
        fragment = MatchFragment.newInstance(club1, club2, stats);
        return fragment;
    }



    @Override
    public int getItemCount() {
        return 3;
    }

    public void setUploads(List<Upload> uploadList) {
        this.uploads = uploadList;

    }

    public List<Upload> getUploads() {
        return uploads;
    }
}
