package com.aoedb.editor.data.components;

import java.util.List;

public class BonusContainer {
    private List<Integer> bonusList;
    private List<Integer> teamBonusList;
    private List<Integer> hiddenBonusList;
    private List<Integer> uniqueTechList;

    public List<Integer> getBonusList() {
        return bonusList;
    }

    public void setBonusList(List<Integer> bonusList) {
        this.bonusList = bonusList;
    }

    public List<Integer> getTeamBonusList() {
        return teamBonusList;
    }

    public void setTeamBonusList(List<Integer> teamBonusList) {
        this.teamBonusList = teamBonusList;
    }

    public List<Integer> getHiddenBonusList() {
        return hiddenBonusList;
    }

    public void setHiddenBonusList(List<Integer> hiddenBonusList) {
        this.hiddenBonusList = hiddenBonusList;
    }

    public List<Integer> getUniqueTechList() {
        return uniqueTechList;
    }

    public void setUniqueTechList(List<Integer> uniqueTechList) {
        this.uniqueTechList = uniqueTechList;
    }
}
