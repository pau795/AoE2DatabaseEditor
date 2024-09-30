package com.aoedb.editor.data.entity;

import com.aoedb.editor.data.simple.ImageEditable;
import com.aoedb.editor.data.components.*;
import com.aoedb.editor.database.Database;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public abstract class Entity extends ImageEditable {
    int creatorID;
    int ageID;
    int requiredTechnologyID;
    int nextUpgradeID;

    LinkedHashMap<String, String> statMap;
    LinkedHashMap<String, Integer> costMap;

    Availability availability;
    Upgrades upgrades;
    BonusContainer bonusContainer;

    public Entity(int id) {
        super(id);
        statMap = new LinkedHashMap<>();
        costMap = new LinkedHashMap<>();
    }

    public Entity(int id, Entity mainEntity, Entity statsEntity, Entity availabilityEntity, Entity upgradesEntity, Entity bonusEntity) {
        super(id, mainEntity);
        statMap = new LinkedHashMap<>();
        costMap = new LinkedHashMap<>();

        Descriptor descriptor = mainEntity.getDescriptor();
        Database.setString(String.format("%s_description_nominative_%d", this.getType(),  this.id), Database.getString(descriptor.getNominative(), "en"), "en");
        Database.setString(String.format("%s_description_quick_%d", this.getType(), this.id),  Database.getString(descriptor.getQuickDescription(), "en"), "en");
        Database.setString(String.format("%s_description_brief_%d", this.getType(), this.id),  Database.getString(descriptor.getBriefDescription(), "en"), "en");
        Database.setString(String.format("%s_description_long_%d", this.getType(), this.id),  Database.getString(descriptor.getLongDescription(), "en"), "en");
        Database.setString(String.format("%s_description_extra_%d", this.getType(), this.id),  Database.getString(descriptor.getExtraDescription(), "en"), "en");

        Database.setString(String.format("%s_description_nominative_%d", this.getType(),  this.id), Database.getString(descriptor.getNominative(), "es"), "es");
        Database.setString(String.format("%s_description_quick_%d", this.getType(), this.id),  Database.getString(descriptor.getQuickDescription(), "es"), "es");
        Database.setString(String.format("%s_description_brief_%d", this.getType(), this.id),  Database.getString(descriptor.getBriefDescription(), "es"), "es");
        Database.setString(String.format("%s_description_long_%d", this.getType(), this.id),  Database.getString(descriptor.getLongDescription(), "es"), "es");
        Database.setString(String.format("%s_description_extra_%d", this.getType(), this.id),  Database.getString(descriptor.getExtraDescription(), "es"), "es");

        this.creatorID = mainEntity.getCreatorID();
        this.ageID = mainEntity.getAgeID();
        this.requiredTechnologyID = mainEntity.getRequiredTechnologyID();
        this.nextUpgradeID = mainEntity.getNextUpgradeID();
        this.statMap.putAll(statsEntity.getStatMap());
        this.costMap.putAll(statsEntity.getCostMap());
        this.availability = new Availability(availabilityEntity.getAvailability());
        this.upgrades = new Upgrades(upgradesEntity.getUpgrades());
        this.bonusContainer = new BonusContainer(bonusEntity.getBonusContainer());
    }


    public int getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }

    public abstract String getCreatorType();

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

    public Map<String, String> getStatMap(){
        return statMap;
    }

    public Map<String, Integer> getCostMap(){
        return costMap;
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

    public Descriptor getDescriptor(){
        Descriptor descriptor = new Descriptor();
        descriptor.setNominative(String.format("%s_description_nominative_%d", this.getType(),  this.id));
        descriptor.setQuickDescription(String.format("%s_description_quick_%d", this.getType(), this.id));
        descriptor.setBriefDescription(String.format("%s_description_brief_%d", this.getType(), this.id));
        descriptor.setLongDescription(String.format("%s_description_long_%d", this.getType(), this.id));
        descriptor.setExtraDescription(String.format("%s_description_extra_%d", this.getType(), this.id));
        return descriptor;
    }
}
