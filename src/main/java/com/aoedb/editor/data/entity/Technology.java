package com.aoedb.editor.data.entity;

import com.aoedb.editor.data.components.BonusEffectContainer;


public class Technology extends Entity {

    BonusEffectContainer techEffect;

    public Technology(int id) {
        super(id);
    }

    @Override
    protected String getNamePattern() {
        return String.format("tech_name_%d", this.id);
    }


    public BonusEffectContainer getTechEffect() {
        return techEffect;
    }

    public void setTechEffect(BonusEffectContainer techEffect) {
        this.techEffect = techEffect;
    }
}
