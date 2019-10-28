package com.example.tony.ridealong.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tony.ridealong.Model.Rides;
import com.example.tony.ridealong.R;

import java.util.List;

public class RidesAdapter extends RecyclerView.Adapter<RidesAdapter.ViewHolder> {

    private List<Rides> ridesList;

    public RidesAdapter(List<Rides> ridesList) {
        this.ridesList = ridesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ridesdisplay,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Rides rides = ridesList.get(position);
        holder.item_seats.setText(rides.getNo_of_Seats());
        holder.item_dest.setText(rides.getDestination());
        holder.item_date.setText(rides.getDate());
        holder.item_start.setText(rides.getStart_Point());
        holder.priceperseat.setText(rides.getPrice_per_seat());


    }

    @Override
    public int getItemCount() {
        return ridesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView item_start,item_dest,item_date,item_seats,priceperseat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_start = itemView.findViewById(R.id.item_start);
            item_date = itemView.findViewById(R.id.item_date);
            item_dest = itemView.findViewById(R.id.item_dest);
            item_seats = itemView.findViewById(R.id.item_seats);
            priceperseat = itemView.findViewById(R.id.priceperseat);
        }
    }
}
