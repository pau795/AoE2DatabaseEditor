package com.aoedb.editor.views.editable;

import com.aoedb.editor.data.entity.Civilization;
import com.aoedb.editor.views.editors.EffectContainerEditor;
import com.aoedb.editor.views.editors.StringEditor;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CivilizationView extends ImageEditableView{

    private final Civilization civilization;

    public CivilizationView(Civilization c) {
        super(c);
        this.civilization = c;
    }

    @Override
    public Div getView(){
        Div mainLayout = super.getView();

        Accordion civStyleAccordion = new Accordion();
        civStyleAccordion.add("Civilization Style", new VerticalLayout(new StringEditor(civilization.getStyle())));

        

        mainLayout.add(civStyleAccordion);
        return mainLayout;
    }
}
