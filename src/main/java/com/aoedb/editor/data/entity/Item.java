package com.aoedb.editor.data.entity;

import com.aoedb.editor.data.items.TypeValues;

public abstract class Item extends Entity{
    int classID;
    int upgradedFromID;

    TypeValues attackList;
    TypeValues armorList;

    public Item(int id) {
        super(id);
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public int getUpgradedFromID() {
        return upgradedFromID;
    }

    public void setUpgradedFromID(int upgradedFromID) {
        this.upgradedFromID = upgradedFromID;
    }

    public TypeValues getAttackList() {
        return attackList;
    }

    public void setAttackList(TypeValues attackList) {
        this.attackList = attackList;
    }

    public TypeValues getArmorList() {
        return armorList;
    }

    public void setArmorList(TypeValues armorList) {
        this.armorList = armorList;
    }
}
