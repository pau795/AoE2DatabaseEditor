package com.aoedb.editor.views.editable;

import com.aoedb.editor.data.bonus.CivBonus;
import com.aoedb.editor.data.entity.Civilization;
import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.editors.StringEditor;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CivBonusView extends BonusView{

    private final CivBonus civBonus;

    public CivBonusView(CivBonus cb){
        super(cb);
        this.civBonus = cb;
    }

    @Override
    public Div getView(){
        Div mainLayout = super.getView();

        Accordion itemDescription = new Accordion();
        itemDescription.add("Item Description", new VerticalLayout(new StringEditor(civBonus.getBonusItemPattern())));

        mainLayout.addComponentAtIndex(2, itemDescription);
        return mainLayout;
    }

    @Override
    public Div getLabel(){
        Div layout = super.getLabel();
        Image icon = new Image();
        if (this.civBonus.getId() == 0) icon.setSrc(Database.getImage("t_white"));
        else {
            Civilization civ = Database.getCivilization(this.civBonus.getBonusEffect().getCivID());
            icon.setSrc(Database.getImage(civ.getImagePath()));
        }
        icon.addClassNames("editable-label-icon");
        layout.addComponentAsFirst(icon);
        return layout;
    }
}
