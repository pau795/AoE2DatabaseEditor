package com.aoedb.editor.data.entity;

import com.aoedb.editor.data.components.Trainable;
import com.aoedb.editor.database.Database;

public class Building extends Item{
    private Integer requiredBuilding;

    private Trainable trainable;

    public Building(int id) {
        super(id);
    }

    @Override
    public String getName(){
        return String.format("building_name_%d", this.id);
    }

    @Override
    public String getType(){
        return Database.BUILDING;
    }

    public int getRequiredBuilding() {
        return requiredBuilding;
    }

    public void setRequiredBuilding(int requiredBuilding) {
        this.requiredBuilding = requiredBuilding;
    }

    public Trainable getTrainable() {
        return trainable;
    }

    public void setTrainable(Trainable trainable) {
        this.trainable = trainable;
    }


}
