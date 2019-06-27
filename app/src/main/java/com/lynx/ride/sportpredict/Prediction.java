package com.lynx.ride.sportpredict;

public class Prediction {

    private String team_A;
    private String team_B;
    private String tips;
    private String image;
private  String time;
private  String update;



    public Prediction() {
    }

    public Prediction(String team_A, String team_B, String tips, String image, String time) {
        this.team_A = team_A;
        this.team_B = team_B;
        this.tips = tips;
        this.image = image;
//this.update=update;
        this.time=time;

    }



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeam_A() {
        return team_A;
    }

    public void setTeam_A(String team_A) {
        this.team_A = team_A;
    }

    public String getTeam_B() {
        return team_B;
    }

    public void setTeam_B(String team_B) {
        this.team_B = team_B;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}