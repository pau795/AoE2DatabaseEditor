package com.aoedb.editor.data.items;



public class GatheringRates extends Editable{

    int ecoID;
    String resourceIcon;
    String statIcon;


    private GatheringRates(int id) {
        super(id);
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
}
