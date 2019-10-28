package com.example.tony.ridealong.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tony.ridealong.DriverProfile;
import com.example.tony.ridealong.Model.Rides;
import com.example.tony.ridealong.PassengerLoginActivity;
import com.example.tony.ridealong.R;
import com.example.tony.ridealong.ShowRides;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RidessFragment extends Fragment {

    private RecyclerView mrideList;
    private DatabaseReference table_rides;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rides_layout, container, false);
        return view;

    }

}
