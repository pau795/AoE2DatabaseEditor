package com.aoedb.editor.data.components;


import java.util.List;

public class Upgrades {
    public static class UpgradeList {
        private Integer elementID;
        private List<Integer> subList;

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

    public List<UpgradeList> getExpandableList() {
        return expandableList;
    }

    public void setExpandableList(List<UpgradeList> expandableList) {
        this.expandableList = expandableList;
    }



}
