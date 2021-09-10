package com.example.tindog.ui.tinder;

enum Choice{
    first, second ,both;

}
public class FilterData {


    private int minAge = 0;
    private int maxAge = 20;
    private Choice gender = Choice.both;
    private Choice castrated = Choice.both;

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public Choice getGender() {
        return gender;
    }

    public void setGender(Choice gender) {
        this.gender = gender;
    }

    public Choice getCastrated() {
        return castrated;
    }

    public void setCastrated(Choice castrated) {
        this.castrated = castrated;
    }
}
