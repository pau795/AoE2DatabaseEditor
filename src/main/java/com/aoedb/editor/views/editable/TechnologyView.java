package com.aoedb.editor.views.editable;


import com.aoedb.editor.data.entity.Technology;
import com.aoedb.editor.views.editors.EffectEditor;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.html.Div;


public class TechnologyView extends EntityView {

    private final Technology technology;

    public TechnologyView(Technology t){
        super(t);
        this.technology = t;
    }

    @Override
    public Div getView(){
        Div mainLayout = super.getView();

        Accordion techEffectAccordion = new Accordion();
        techEffectAccordion.add("Tech Effect", new EffectEditor(technology.getTechEffect()));
        techEffectAccordion.close();

        mainLayout.add(techEffectAccordion);
        return mainLayout;
    }

}
