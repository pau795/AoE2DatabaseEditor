package com.aoedb.editor.data.bonus;

import com.aoedb.editor.data.simple.Editable;
import com.aoedb.editor.data.components.EffectContainer;

public abstract class Bonus extends Editable {

    protected EffectContainer bonusEffect;

    protected Bonus(int id) {
        super(id);
    }

    protected Bonus(int id, Bonus mainBonus){
        super(id, mainBonus);
        this.bonusEffect = new EffectContainer(mainBonus.getBonusEffect());
    }

    public EffectContainer getBonusEffect() {
        return bonusEffect;
    }

    public void setBonusEffect(EffectContainer bonusEffect) {
        this.bonusEffect = bonusEffect;
    }

}
