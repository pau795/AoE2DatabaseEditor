package com.aoedb.editor.data.entity;

import com.aoedb.editor.data.simple.ImageEditable;
import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.editable.CivilizationView;
import com.aoedb.editor.views.editable.EditableView;

import java.util.ArrayList;
import java.util.List;

public class Civilization extends ImageEditable {

    public static class UniqueUnit{
        int id;
        int eliteID;

        public UniqueUnit(){
            id = -1;
            eliteID = -1;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getEliteID() {
            return eliteID;
        }

        public void setEliteID(int eliteID) {
            this.eliteID = eliteID;
        }
    }

    public Civilization(int id){
        super(id);
        bonusList = new ArrayList<>();
        uniqueUnitList =  new ArrayList<>();
        teamBonus = -1;
        castleUT = -1;
        imperialUT = -1;
        uniqueBuilding = -1;
    }
    @Override
    public String getName(){
        return String.format("civilization_name_%d", this.id);
    }
    @Override
    public String getType(){
        return Database.CIV;
    }

    @Override
    public EditableView getEditableView() {
        return new CivilizationView(this);
    }

    private List<Integer> bonusList;
    private int teamBonus;
    private List<UniqueUnit> uniqueUnitList;
    private int castleUT, imperialUT;
    private int uniqueBuilding;

    public String getStyle() {
        return String.format("civilization_style_%d", this.id);
    }


    public List<Integer> getBonusList() {
        return bonusList;
    }

    public void setBonusList(List<Integer> bonusList) {
        this.bonusList = bonusList;
    }

    public int getTeamBonus() {
        return teamBonus;
    }

    public void setTeamBonus(int teamBonus) {
        this.teamBonus = teamBonus;
    }

    public List<UniqueUnit> getUniqueUnitList() {
        return uniqueUnitList;
    }

    public void setUniqueUnitList(List<UniqueUnit> uniqueUnitList) {
        this.uniqueUnitList = uniqueUnitList;
    }

    public int getCastleUT() {
        return castleUT;
    }

    public void setCastleUT(int castleUT) {
        this.castleUT = castleUT;
    }

    public int getImperialUT() {
        return imperialUT;
    }

    public void setImperialUT(int imperialUT) {
        this.imperialUT = imperialUT;
    }

    public int getUniqueBuilding() {
        return uniqueBuilding;
    }

    public void setUniqueBuilding(int uniqueBuilding) {
        this.uniqueBuilding = uniqueBuilding;
    }



}
