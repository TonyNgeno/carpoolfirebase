package com.example.tony.ridealong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tony.ridealong.Model.Rides;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.auth.AuthUI;

public class ShowRides extends AppCompatActivity {
    private RecyclerView mrideList;
    private DatabaseReference table_rides;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayrideview);


        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() == null) {        mAuth = FirebaseAuth.getInstance();

            startActivity(new Intent(this, PassengerLoginActivity.class));
            finish();
        }

        String S = mAuth.getUid();
        mrideList = (RecyclerView) findViewById(R.id.ridesrecyclerview);
        mrideList.setHasFixedSize(true);
        mrideList.setLayoutManager(new LinearLayoutManager(this));
        table_rides = FirebaseDatabase.getInstance().getReference().child("Rides");

    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Rides, rideViewHolder> FBRA = new FirebaseRecyclerAdapter<Rides, rideViewHolder>(

                Rides.class,
                R.layout.ridesdisplay,
                rideViewHolder.class,
                table_rides
        ) {

            @Override
            protected void populateViewHolder(rideViewHolder viewHolder, Rides model, int position) {

                final String post_key = getRef(position).getKey().toString();

                viewHolder.setItem_start(model.getStart_Point());
                viewHolder.setItem_dest(model.getDestination());
                viewHolder.setSeats(model.getNo_of_Seats());
                viewHolder.setPrice(model.getPrice_per_seat());
                viewHolder.setDate(model.getDate());


                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent singlePostActivity = new Intent(ShowRides.this, DriverProfile.class);
                        //singlePostActivity.putExtra("Postid", post_key);
                        startActivity(singlePostActivity);

                    }
                });
            }
        };

        mrideList.setAdapter(FBRA);
    }


    public static class rideViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public rideViewHolder(View v){
            super(v);
            mView = itemView;
        }

        public void setItem_title(String item_title){
            TextView Title = (TextView) mView.findViewById(R.id.item_title);
            Title.setText(item_title);
        }
        public void setItem_start(String item_start){
            TextView Start= (TextView) mView.findViewById(R.id.item_start);
            Start.setText(item_start);
        }

        public void setItem_dest(String item_dest){
            TextView Dest = (TextView) mView.findViewById(R.id.item_dest);
            Dest.setText(item_dest);
        }

        public void setDate(String item_date){
            TextView Date = (TextView) mView.findViewById(R.id.item_date);
            Date.setText(item_date);
        }

        public void setSeats(String item_seats){
            TextView Seats = (TextView) mView.findViewById(R.id.item_seats);
            Seats.setText(item_seats);
        }

        public void setPrice(String priceperseat){
            TextView Price = (TextView) mView.findViewById(R.id.priceperseat);
            Price.setText(priceperseat);
        }
    }

}

