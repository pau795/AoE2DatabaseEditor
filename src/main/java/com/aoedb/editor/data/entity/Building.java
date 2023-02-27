package com.aoedb.editor.data.entity;

import com.aoedb.editor.data.components.Trainable;

public class Building extends Item{
    int requiredBuilding;

    Trainable trainable;

    public Building(int id) {
        super(id);
    }

    @Override
    protected String getNamePattern(){
        return String.format("building_name_%d", this.id);
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
