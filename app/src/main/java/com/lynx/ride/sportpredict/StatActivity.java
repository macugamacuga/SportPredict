package com.lynx.ride.sportpredict;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.lynx.ride.sportpredict.Models.TeamModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class StatActivity extends AppCompatActivity {
TextView team1,team2,team1_last5,team2_last5,team1_OU,team2_OU,timeview;
    String ID,currentDateandTime,working_date;
    ProgressBar mprogressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        currentDateandTime = sdf.format(new Date());

        mprogressBar=findViewById(R.id.progressBar_stat);

//ad view
        AdView adView=findViewById(R.id.statadView);
        AdRequest adRequest=new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);
        AdView adView2=findViewById(R.id.statadView2);
        AdRequest adRequest2=new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView2.loadAd(adRequest2);

        team1=findViewById(R.id.team1);
        team2=findViewById(R.id.team2);
        team1_last5=findViewById(R.id.team1_last5);
        team2_last5=findViewById(R.id.team2_last5);
        team1_OU=findViewById(R.id.team1_OU);
        team2_OU=findViewById(R.id.team2_OU);
timeview=findViewById(R.id.time);
        Intent myIntent = getIntent();
        ID=myIntent.getStringExtra("gameID");
        working_date=myIntent.getStringExtra("working_date");
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        getData();
    }


    public  void getData(){

        mprogressBar.setVisibility(View.VISIBLE);

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        String url ="https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1bc7GXb6I4HJ41Ci5WhV4fys-l2awkDLjkqJ8YqiDHZw&sheet="+working_date+"";

// Request a string response from the provided URL.
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        setStat(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mprogressBar.setVisibility(View.GONE);
                Log.d(null, "onErrorResponse:  "+error);
            }
        });


// Add the request to the RequestQueue.
        queue.add(stringRequest1);
    }
    public void setStat(String responce){
        TeamModel teamModel;
        try {
            JSONObject jsonObject = new JSONObject(responce);
            JSONArray jsonArray = jsonObject.getJSONArray(working_date);

            ArrayList<TeamModel> arrayList = new ArrayList<>();

            for (int j = 0; j < jsonArray.length(); j++) {
                JSONObject jo2 = jsonArray.getJSONObject(j);
                String teamA = jo2.getString("team1");
                String teamB = jo2.getString("team2");
              String teamA_ou=jo2.getString("team1_OU");
              String teamB_ou=jo2.getString("team2_OU");
                String teamA_last5=jo2.getString("team1Last5");
                String teamB_last5=jo2.getString("team2Last5");
                String time = jo2.getString("time");
                String gameID = jo2.getString("ID");

                if (gameID.equals(ID)) {
                    mprogressBar.setVisibility(View.GONE);
                     teamModel = new TeamModel();
                    teamModel.setTeamA(teamA);
                    teamModel.setTeamB(teamB);
                    teamModel.setTime(time);

                    teamModel.setTeam1_ou(teamA_ou);
                    teamModel.setTeam2_ou(teamB_ou);
                    teamModel.setTeam1_last5(teamA_last5);
                    teamModel.setTeam2_last5(teamB_last5);


                    arrayList.add(teamModel);
                    team1.setText(teamModel.getTeamA());
                    team2.setText(teamModel.getTeamB());
                    team1_last5.setText(teamModel.getTeam1_last5());
                    team2_last5.setText(teamModel.getTeam2_last5());
                    team1_OU.setText(teamModel.getTeam1_ou());
                    team2_OU.setText(teamModel.getTeam2_ou());
                    timeview.setText(teamModel.getTime());
                }


            }


        }
        catch (Exception e){
            mprogressBar.setVisibility(View.GONE);

        }





    }

}
