package com.aoedb.editor.views.editable;

import com.aoedb.editor.data.bonus.Bonus;
import com.aoedb.editor.views.editors.*;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.html.Div;

public abstract class BonusView extends EditableView{

    private final Bonus bonus;

    public BonusView(Bonus b){
        super(b);
        this.bonus = b;
    }

    @Override
    public Div getView(){
        Div mainLayout = super.getView();

        Accordion bonusEditor = new Accordion();
        bonusEditor.add("Bonus Effect", new EffectEditor(this.bonus.getBonusEffect()));

        mainLayout.add(bonusEditor);
        return mainLayout;
    }

}
