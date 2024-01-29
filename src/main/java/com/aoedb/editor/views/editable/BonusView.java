package com.aoedb.editor.views.editable;

import com.aoedb.editor.data.bonus.Bonus;
import com.aoedb.editor.data.entity.Civilization;
import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.editors.*;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;

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
        bonusEditor.add("Bonus Effect", new EffectContainerEditor(this.bonus.getBonusEffect()));
        bonusEditor.close();

        mainLayout.add(bonusEditor);
        return mainLayout;
    }



}
