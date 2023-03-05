package com.aoedb.editor.data.items;


import com.aoedb.editor.data.simple.Editable;
import com.aoedb.editor.database.Database;

public class GatheringRates extends Editable {

    private Integer ecoID;
    private String resourceIcon;
    private String statIcon;

    public GatheringRates(int id) {
        super(id);
    }

    @Override
    public String getName() {
        return String.format("gathering_rates_%d", this.id);
    }

    public int getEcoID() {
        return ecoID;
    }

    public void setEcoID(int ecoID) {
        this.ecoID = ecoID;
    }

    public String getResourceIcon() {
        return resourceIcon;
    }

    public void setResourceIcon(String resourceIcon) {
        this.resourceIcon = resourceIcon;
    }

    public String getStatIcon() {
        return statIcon;
    }

    public void setStatIcon(String statIcon) {
        this.statIcon = statIcon;
    }

    @Override
    public String getType(){
        return Database.GATHERING_RATES;
    }
}
