package com.aoedb.editor.data.entity;

import com.aoedb.editor.data.components.Trainable;
import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.editable.BuildingView;
import com.aoedb.editor.views.editable.EditableView;

public class Building extends Item{
    private Integer requiredBuilding;

    private Trainable trainable;

    public Building(int id) {
        super(id);
    }

    @Override
    public String getName(){
        if (id == 0) return "none";
        return String.format("building_name_%d", this.id);
    }

    @Override
    public String getType(){
        return Database.BUILDING;
    }

    @Override
    public EditableView getEditableView() {
        return new BuildingView(this);
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

    @Override
    public String getCreatorType(){
        return Database.UNIT;
    }

    public static Building getNone(){
        Building none = new Building(0);
        none.setImagePath("t_white");
        return none;
    }
}
