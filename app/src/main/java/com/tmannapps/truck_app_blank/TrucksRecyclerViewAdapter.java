package com.tmannapps.truck_app_blank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//view holder used as a reference to memory recycler to truck row, data model and dataset
public class TrucksRecyclerViewAdapter extends RecyclerView.Adapter<TrucksRecyclerViewAdapter.ViewHolder> {

    public TrucksRecyclerViewAdapter(List<Truck> truckList, Context context) {
        this.truckList = truckList;
        this.context = context;
    }

    private List<Truck> truckList;
    private Context context;

    @NonNull
    @Override
    public TrucksRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.truck_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TrucksRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.truckTitleModel.setText(truckList.get(position).getModelName());
        holder.truckType.setText(truckList.get(position).getVehicleType());
        holder.truckCapacity.setText(truckList.get(position).getCapacity());
        holder.truckImage.setImageResource(truckList.get(position).getTruckImage());
    }

    @Override
    public int getItemCount() {
        return truckList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView truckTitleModel;
        TextView truckType;
        TextView truckCapacity;
        ImageView truckImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            truckTitleModel = itemView.findViewById(R.id.textViewTruckTitle);
            truckType = itemView.findViewById(R.id.textViewTruckDetailType);
            truckCapacity = itemView.findViewById(R.id.textViewTruckDetailCapacity);
            truckImage = itemView.findViewById(R.id.imageViewTruckPicture);
        }
    }
}
