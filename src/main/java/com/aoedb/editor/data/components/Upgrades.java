package com.aoedb.editor.data.components;


import java.util.ArrayList;
import java.util.List;

public class Upgrades {
    public static class UpgradeList {
        private Integer elementID;
        private List<Integer> subList;

        public UpgradeList(int elementID){
            this.elementID = elementID;
            this.subList = new ArrayList<>();
        }

        public UpgradeList(UpgradeList other){
            this.elementID = other.elementID;
            this.subList = new ArrayList<>(other.subList);
        }

        public int getElementID() {
            return elementID;
        }

        public void setElementID(int elementID) {
            this.elementID = elementID;
        }

        public List<Integer> getSubList() {
            return subList;
        }

        public void setSubList(List<Integer> subList) {
            this.subList = subList;
        }
    }

    private List<UpgradeList> expandableList;

    public Upgrades() {}

    public Upgrades(Upgrades other){
        this.expandableList = new ArrayList<>();
        for(UpgradeList ul : other.getExpandableList()) this.expandableList.add(new UpgradeList(ul));
    }

    public List<UpgradeList> getExpandableList() {
        return expandableList;
    }

    public void addUpgradeList(UpgradeList upgrade){
        this.expandableList.add(upgrade);
    }

    public void removeUpgradeList(UpgradeList upgrade){
        this.expandableList.remove(upgrade);
    }

    public void setExpandableList(List<UpgradeList> expandableList) {
        this.expandableList = expandableList;
    }



}
