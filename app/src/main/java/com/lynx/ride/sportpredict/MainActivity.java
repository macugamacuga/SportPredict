package com.lynx.ride.sportpredict;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.lynx.ride.sportpredict.Models.MainModel;
import com.lynx.ride.sportpredict.Models.TeamModel;


import org.json.JSONArray;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    static String working_date;
    ProgressBar mprogressBar;
RecyclerView mainrecyclerView;
ArrayList<MainModel> list;
main_RV_Adapter adapter;
    String currentDateandTime,tomorrow_date,yesterday_date;
TextView yesterday,today,tomorrow;
CardView cardView1,cardView2,cardView3;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
cardView1=findViewById(R.id.card1);
        cardView2=findViewById(R.id.card2);
        cardView3=findViewById(R.id.card3);
        //get date
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1=Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        currentDateandTime = sdf.format(new Date());
        // add one day to the date/calendar
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrowdate = calendar.getTime();
        tomorrow_date=sdf.format(tomorrowdate);
        // subtract one day to the date/calendar
        calendar1.add(Calendar.DAY_OF_YEAR, -1);
        Date yesterdaydate = calendar1.getTime();
        yesterday_date=sdf.format(yesterdaydate);

        //initializ text
        yesterday=findViewById(R.id.yesterday);
        today=findViewById(R.id.currentdate);
        tomorrow=findViewById(R.id.tomorrow);

        today.setText(currentDateandTime);
        tomorrow.setText(tomorrow_date);
        yesterday.setText(yesterday_date);


//start progress bar
 mprogressBar=findViewById(R.id.progressBar_main);



AdView adView=findViewById(R.id.HomeAdd);
AdRequest adRequest=new AdRequest.Builder()
        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
        .build();


adView.loadAd(adRequest);



//recycler view
        mainrecyclerView = findViewById(R.id.myrecycler);
        working_date=currentDateandTime;
        adaptercall();




       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(interstitialAd.isLoaded()){
                    interstitialAd.show();
                }
                sendEmail();

            }
        });*/
        //interstitalad
        interstitialAd=new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        AdRequest adRequest2=new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        interstitialAd.loadAd(adRequest2);


/*cardView1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        getData(yesterday_date);
    }
});*/

    }


    public static String getWorking_date() {
        return working_date;
    }

    public void adaptercall(){
        mainrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainrecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mainrecyclerView.setHasFixedSize(false);
        list = new ArrayList<>();
        adapter=new main_RV_Adapter(this,list );
        mainrecyclerView.setAdapter(adapter);
        getData();
    }

    public  void getData( ){
list.clear();
adapter.notifyDataSetChanged();


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
                      setData(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mprogressBar.setVisibility(View.GONE);
                Log.d(null, "onErrorResponse:  "+error);
                Toast.makeText(MainActivity.this, "Error: "+error,Toast.LENGTH_SHORT).show();
            }
        });


// Add the request to the RequestQueue.
        queue.add(stringRequest1);
        }

    private void setData(String responce) {

        ArrayList<String> countlist = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(responce);
            JSONArray jsonArray = jsonObject.getJSONArray(working_date);
for(int i=0;i<jsonArray.length();i++) {
    JSONObject jo2 = jsonArray.getJSONObject(i);
    String leagueID = jo2.getString("leagueID");

    countlist.add(leagueID);
}
    Set<String> set = new HashSet<>(countlist);
    countlist.clear();
    countlist.add("0");
    countlist.addAll(set);


            for (int i = 1; i < countlist.size(); i++) {
                MainModel mainModel = new MainModel();

                mainModel.setTitle(countlist.get(i));


                ArrayList<TeamModel> arrayList = new ArrayList<>();

                for (int j = 0; j < jsonArray.length(); j++) {
                    JSONObject jo2 = jsonArray.getJSONObject(j);
                    String teamA = jo2.getString("team1");
                    String teamB = jo2.getString("team2");
                    String tip = jo2.getString("tip");
                    String time=jo2.getString("time");
                    String leagueID=jo2.getString("leagueID");
                    String ID=jo2.getString("ID");
                    String teamA_percent=jo2.getString("team1percent");
                    String teamB_percent=jo2.getString("team2percent");
                    String draw_percent=jo2.getString("drawpercent");
                    TeamModel teamModel = new TeamModel();
                    teamModel.setTeamA(teamA);
                    teamModel.setTeamB(teamB);
                    teamModel.setTime(time);
                    teamModel.setTip(tip);
                    teamModel.setTeamA_percent(teamA_percent);
                    teamModel.setTeamB_percent(teamB_percent);
                    teamModel.setDraw_percent(draw_percent);
teamModel.setID(ID);
                   if(leagueID.equals(countlist.get(i))) {
                       arrayList.add(teamModel);
                   }
                }
                mainModel.setArrayList(arrayList);
                list.add(mainModel);
            }
            adapter.notifyDataSetChanged();
            mprogressBar.setVisibility(View.GONE);
            if(interstitialAd.isLoaded()){
                interstitialAd.show();
            }
        } catch (Exception e) {
            mprogressBar.setVisibility(View.GONE);
        }

    }



    protected void sendEmail() {

        Log.i("Send email", "");
        String[] TO = {"Williammacuga@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Sport Predict Comment");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "I love this app, send  me notification of new updated");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));

            Log.i("email sent...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void yesterdayClick( View v){
        working_date=yesterday_date;
adaptercall();
cardView1.setCardBackgroundColor(Color.WHITE);
        cardView2.setCardBackgroundColor(this.getResources().getColor(R.color.cardbg));
        cardView3.setCardBackgroundColor(this.getResources().getColor(R.color.cardbg));
    }
    public void todayClick(View v){
        working_date=currentDateandTime;
adaptercall();
        cardView2.setCardBackgroundColor(Color.WHITE);
        cardView3.setCardBackgroundColor(this.getResources().getColor(R.color.cardbg));
        cardView1.setCardBackgroundColor(this.getResources().getColor(R.color.cardbg));
    }
    public void tomorrowClick(View v){
        working_date=tomorrow_date;
adaptercall();
        cardView3.setCardBackgroundColor(Color.WHITE);
        cardView1.setCardBackgroundColor(this.getResources().getColor(R.color.cardbg));
        cardView2.setCardBackgroundColor(this.getResources().getColor(R.color.cardbg));
    }
}
