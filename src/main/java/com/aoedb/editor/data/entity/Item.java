package com.aoedb.editor.data.entity;

import com.aoedb.editor.data.components.TypeValues;

public abstract class Item extends Entity{
    protected Integer classID;
    protected int upgradedFromID;

    protected TypeValues attackList;
    protected TypeValues armorList;

    public Item(int id) {
        super(id);
    }

    public Item(int id, Item mainItem, Item statsItem,  Item attackItem, Item armorItem, Item upgradesItem, Item availabilityItem, Item bonusItem) {
        super(id, mainItem, statsItem, upgradesItem, availabilityItem, bonusItem);
        this.classID = mainItem.getClassID();
        this.upgradedFromID = mainItem.getId();
        this.attackList = new TypeValues(attackItem.getAttackList());
        this.armorList = new TypeValues(armorItem.getArmorList());
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
