package com.lynx.ride.sportpredict;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lynx.ride.sportpredict.Models.MainModel;
import com.lynx.ride.sportpredict.Models.TeamModel;

import java.util.ArrayList;

public class main_RV_Adapter extends RecyclerView.Adapter<main_RV_Adapter.MainRVViewHolder> {

private  Context context;
 private  ArrayList<MainModel>arrayList;

    public main_RV_Adapter(Context context, ArrayList<MainModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MainRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.team_title,parent,false);
       return new MainRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull main_RV_Adapter.MainRVViewHolder holder, int position) {
MainModel mainModel=arrayList.get(position);
String league_title=mainModel.getTitle();
String tip=mainModel.getTip();
ArrayList<TeamModel> team_item=mainModel.getArrayList();

holder.League.setText(league_title);

mAdapter mAdapter=new mAdapter(context,team_item);
holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
holder.recyclerView.setHasFixedSize(false);
holder.recyclerView.setAdapter(mAdapter);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MainRVViewHolder extends RecyclerView.ViewHolder{
RecyclerView recyclerView;
TextView League,tip;
        public MainRVViewHolder(View itemView) {
            super(itemView);

            recyclerView=itemView.findViewById(R.id.League_predicationRV);
            League=itemView.findViewById(R.id.text_league);
            tip=itemView.findViewById(R.id.text_tip);
        }
    }
}
