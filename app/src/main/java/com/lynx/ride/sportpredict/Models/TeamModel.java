package com.lynx.ride.sportpredict.Models;

public class TeamModel {

   private String teamA,teamB,Tip,time;
    private String leagueid;
   private String ID,team1_ou,team2_ou,team1_last5,team2_last5;
 private String teamA_percent,draw_percent,teamB_percent;

    public String getTeamA_percent() {
        return teamA_percent;
    }

    public void setTeamA_percent(String teamA_percent) {
        this.teamA_percent = teamA_percent;
    }

    public String getDraw_percent() {
        return draw_percent;
    }

    public void setDraw_percent(String draw_percent) {
        this.draw_percent = draw_percent;
    }

    public String getTeamB_percent() {
        return teamB_percent;
    }

    public void setTeamB_percent(String teamB_percent) {
        this.teamB_percent = teamB_percent;
    }

    public String getTeam1_ou() {
        return team1_ou;
    }

    public void setTeam1_ou(String team1_ou) {
        this.team1_ou = team1_ou;
    }

    public String getTeam2_ou() {
        return team2_ou;
    }

    public void setTeam2_ou(String team2_ou) {
        this.team2_ou = team2_ou;
    }

    public String getTeam1_last5() {
        return team1_last5;
    }

    public void setTeam1_last5(String team1_last5) {
        this.team1_last5 = team1_last5;
    }

    public String getTeam2_last5() {
        return team2_last5;
    }

    public void setTeam2_last5(String team2_last5) {
        this.team2_last5 = team2_last5;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTeamA() {
        return teamA;
    }

    public String getLeagueid() {
        return leagueid;
    }

    public void setLeagueid(String leagueid) {
        this.leagueid = leagueid;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public String getTip() {
        return Tip;
    }

    public void setTip(String tip) {
        Tip = tip;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
