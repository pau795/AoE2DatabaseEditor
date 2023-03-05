package com.aoedb.editor.data.entity;

import com.aoedb.editor.data.components.BonusEffectContainer;
import com.aoedb.editor.database.Database;


public class Technology extends Entity {

    private BonusEffectContainer techEffect;

    public Technology(int id) {
        super(id);
    }

    @Override
    public String getName() {
        return String.format("tech_name_%d", this.id);
    }

    @Override
    public String getType(){
        return Database.TECH;
    }

    public BonusEffectContainer getTechEffect() {
        return techEffect;
    }

    public void setTechEffect(BonusEffectContainer techEffect) {
        this.techEffect = techEffect;
    }
}
