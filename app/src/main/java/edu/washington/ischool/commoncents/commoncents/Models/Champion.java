package edu.washington.ischool.commoncents.commoncents.Models;

/**
 * Created by yulong on 3/7/17.
 *
 * Testing a model class to use for Firebase database
 */


public class Champion {
    private String name, abilityOne, abilityTwo, abilityThree, abilityFour;

    public Champion(String name, String abilityOne, String abilityTwo, String abilityThree, String abilityFour) {
        this.name = name;
        this.abilityOne = abilityOne;
        this.abilityTwo = abilityTwo;
        this.abilityThree = abilityThree;
        this.abilityFour = abilityFour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbilityOne() {
        return abilityOne;
    }

    public void setAbilityOne(String abilityOne) {
        this.abilityOne = abilityOne;
    }

    public String getAbilityTwo() {
        return abilityTwo;
    }

    public void setAbilityTwo(String abilityTwo) {
        this.abilityTwo = abilityTwo;
    }

    public String getAbilityThree() {
        return abilityThree;
    }

    public void setAbilityThree(String abilityThree) {
        this.abilityThree = abilityThree;
    }

    public String getAbilityFour() {
        return abilityFour;
    }

    public void setAbilityFour(String abilityFour) {
        this.abilityFour = abilityFour;
    }
}
