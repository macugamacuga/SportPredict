package com.lynx.ride.sportpredict;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class mAdapter extends RecyclerView.Adapter<mAdapter.myViewBuilder> {

    Context context;
    ArrayList<Prediction> predictions;

    public mAdapter(Context c,ArrayList<Prediction> p){
        context =c;
        predictions=p;
    }

    @NonNull
    @Override
    public myViewBuilder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new myViewBuilder(LayoutInflater.from(context).inflate(R.layout.prediction_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull myViewBuilder holder, int position) {
holder.teamA.setText(predictions.get(position).getTeam_A());
        holder.teamB.setText(predictions.get(position).getTeam_B());
        holder.tips.setText(predictions.get(position).getTips());
        holder.time.setText(predictions.get(position).getTime());
        Picasso.get().load(predictions.get(position).getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return predictions.size();
    }

    class myViewBuilder extends RecyclerView.ViewHolder
    {
TextView teamA,teamB,tips,time;
ImageView image;
        public myViewBuilder(View itemView) {
            super(itemView);
            teamA=(TextView) itemView.findViewById(R.id.team_A_textview);
            teamB=(TextView) itemView.findViewById(R.id.team_B_textview);
            tips=(TextView) itemView.findViewById(R.id.tips_textview);
            time=(TextView) itemView.findViewById(R.id.time_textview);
image=(ImageView)itemView.findViewById(R.id.imageView);

        }
    }
}
