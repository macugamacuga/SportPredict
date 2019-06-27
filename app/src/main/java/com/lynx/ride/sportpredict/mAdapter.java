package com.lynx.ride.sportpredict;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.lynx.ride.sportpredict.Models.TeamModel;

import java.util.ArrayList;

public class mAdapter extends RecyclerView.Adapter<mAdapter.myViewBuilder> {

    Context context;
    ArrayList<TeamModel> predictions;
String working_date;
    public mAdapter(Context c, ArrayList<TeamModel> p){
        this.context =c;
        this.predictions=p;
    }

    @NonNull
    @Override
    public myViewBuilder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            return new myViewBuilder(LayoutInflater.from(context).inflate(R.layout.prediction_item,parent,false));


    }

    @Override
    public void onBindViewHolder(@NonNull myViewBuilder holder, int position) {
        final TeamModel teamModel=predictions.get(position);
holder.teamA.setText(teamModel.getTeamA());
        holder.teamB.setText(teamModel.getTeamB());
        holder.tips.setText(teamModel.getTip());
        holder.time.setText(teamModel.getTime());
        holder.teamA_percent.setText(teamModel.getTeamA_percent());
        holder.teamB_percent.setText(teamModel.getTeamB_percent());
        holder.draw_percent.setText(teamModel.getDraw_percent());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               working_date=MainActivity.getWorking_date();
                Intent intent=new Intent( context,StatActivity.class);
                intent.putExtra("gameID",teamModel.getID());
                intent.putExtra("working_date", working_date);
                context.startActivity(intent);



            }
        });


      // holder.updates.setText(predictions.get(position).getUpdate());

    }

    @Override
    public int getItemCount() {
        return predictions.size();
    }

    class myViewBuilder extends RecyclerView.ViewHolder
    {
TextView teamA,teamB,tips,time,updates,teamA_percent,draw_percent,teamB_percent;
Button button;

        public myViewBuilder(View itemView) {
            super(itemView);
            teamA=(TextView) itemView.findViewById(R.id.team_A_textview);
            teamB=(TextView) itemView.findViewById(R.id.team_B_textview);
            tips=(TextView) itemView.findViewById(R.id.tips_textview);
            time=(TextView) itemView.findViewById(R.id.time_textview);
teamA_percent=itemView.findViewById(R.id.team1_percenttext);
draw_percent=itemView.findViewById(R.id.Draw_percenttext);
teamB_percent=itemView.findViewById(R.id.team2_percenttext);
//

        }
    }
}
