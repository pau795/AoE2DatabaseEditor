package com.aoedb.editor.data.items;


import java.util.List;

public class EcoUpgrades extends Editable {

    private List<Integer> upgrades;

    public EcoUpgrades(int id) {
        super(id);
    }

    public List<Integer> getUpgrades() {
        return upgrades;
    }

    public void setUpgrades(List<Integer> upgrades) {
        this.upgrades = upgrades;
    }
}
