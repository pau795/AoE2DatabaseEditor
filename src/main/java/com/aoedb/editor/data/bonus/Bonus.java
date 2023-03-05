package com.aoedb.editor.data.bonus;

import com.aoedb.editor.data.simple.Editable;
import com.aoedb.editor.data.components.BonusEffectContainer;

public abstract class Bonus extends Editable {

    protected BonusEffectContainer bonusEffect;

    protected Bonus(int id) {
        super(id);
    }

    public BonusEffectContainer getBonusEffect() {
        return bonusEffect;
    }

    public void setBonusEffect(BonusEffectContainer bonusEffect) {
        this.bonusEffect = bonusEffect;
    }
}
