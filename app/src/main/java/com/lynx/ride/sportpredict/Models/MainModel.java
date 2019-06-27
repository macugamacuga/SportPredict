package com.lynx.ride.sportpredict.Models;

import java.util.ArrayList;

public class MainModel {
     private String Title;
  private   String Tip;

    public String getTip() {
        return Tip;
    }

    public void setTip(String tip) {
        Tip = tip;
    }

   private ArrayList<TeamModel> arrayList;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public ArrayList<TeamModel> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<TeamModel> arrayList) {
        this.arrayList = arrayList;
    }


}
