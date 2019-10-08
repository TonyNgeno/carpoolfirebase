package com.example.tony.ridealong.ui.rides;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.tony.ridealong.R;

public class RidesFragment extends Fragment {

    private RidesViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(RidesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_myrides, container, false);
        return root;
    }
}