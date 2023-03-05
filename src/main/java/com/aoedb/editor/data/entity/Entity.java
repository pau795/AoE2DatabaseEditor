package com.aoedb.editor.data.entity;

import com.aoedb.editor.data.simple.ImageEditable;
import com.aoedb.editor.data.components.*;

import java.util.LinkedHashMap;
import java.util.Set;

public abstract class Entity extends ImageEditable {
    int creatorID;
    int ageID;
    int requiredTechnologyID;
    int nextUpgradeID;

    LinkedHashMap<String, String> statMap;
    LinkedHashMap<String, Integer> costMap;

    Upgrades upgrades;
    Availability availability;
    BonusContainer bonusContainer;

    public Entity(int id) {
        super(id);
        statMap = new LinkedHashMap<>();
        costMap = new LinkedHashMap<>();
    }


    public int getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }

    public int getAgeID() {
        return ageID;
    }

    public void setAgeID(int ageID) {
        this.ageID = ageID;
    }

    public int getRequiredTechnologyID() {
        return requiredTechnologyID;
    }

    public void setRequiredTechnologyID(int requiredTechnologyID) {
        this.requiredTechnologyID = requiredTechnologyID;
    }

    public int getNextUpgradeID() {
        return nextUpgradeID;
    }

    public void setNextUpgradeID(int nextUpgradeID) {
        this.nextUpgradeID = nextUpgradeID;
    }

    public String getStat(String stat) {
        return statMap.get(stat);
    }

    public void setStat(String stat, String value) {
        this.statMap.put(stat, value);
    }

    public Set<String> getStatList(){
        return statMap.keySet();
    }

    public int getCost(String cost) {
        return costMap.get(cost);
    }

    public void setCost(String cost, int value) {
        this.costMap.put(cost, value);
    }

    public Set<String> getCostList(){
        return costMap.keySet();
    }

    public Upgrades getUpgrades() {
        return upgrades;
    }

    public void setUpgrades(Upgrades upgrades) {
        this.upgrades = upgrades;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public BonusContainer getBonusContainer() {
        return bonusContainer;
    }

    public void setBonusContainer(BonusContainer bonusContainer) {
        this.bonusContainer = bonusContainer;
    }

}
