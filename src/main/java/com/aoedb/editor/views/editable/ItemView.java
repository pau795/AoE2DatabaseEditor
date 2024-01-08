package com.aoedb.editor.views.editable;

import com.aoedb.editor.data.entity.Item;
import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.editors.EditableSelector;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.util.Objects;

public abstract class ItemView extends EntityView{

    private final Item item;

    public ItemView(Item i) {
        super(i);
        this.item = i;
    }

    @Override
    public Div getBaseInfo(){
        Div parentLayout = super.getBaseInfo();

        EditableSelector classSelector = new EditableSelector(Objects.requireNonNull(Database.getEditable(Database.CLASS, item.getClassID())));
        classSelector.setEditableChangedListener(editable -> this.item.setClassID(editable.getId()));

        EditableSelector upgradedFromSelector = new EditableSelector(Objects.requireNonNull(Database.getEditable(item.getType(), item.getUpgradedFromID())));
        upgradedFromSelector.setEditableChangedListener(editable -> this.item.setUpgradedFromID(editable.getId()));

        Label label1 = new Label("Class");
        Label label2 = new Label("Upgraded From");
        label1.setWidth("125px");
        label2.setWidth("125px");
        label1.getElement().getStyle().set("font-weight"," bold");
        label2.getElement().getStyle().set("font-weight"," bold");
        HorizontalLayout layout1 = new HorizontalLayout(label1, classSelector);
        HorizontalLayout layout2 = new HorizontalLayout(label2, upgradedFromSelector);
        layout1.setAlignItems(FlexComponent.Alignment.CENTER);
        layout2.setAlignItems(FlexComponent.Alignment.CENTER);
        parentLayout.addComponentAsFirst(layout1);
        parentLayout.add(layout2);
        return parentLayout;
    }
}
