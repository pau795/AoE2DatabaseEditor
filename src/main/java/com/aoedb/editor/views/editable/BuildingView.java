package com.aoedb.editor.views.editable;

import com.aoedb.editor.data.entity.Building;
import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.editors.EditableSelector;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.util.Objects;

public class BuildingView extends ItemView {

    private final Building building;

    public BuildingView(Building b) {
        super(b);
        this.building = b;
    }

    @Override
    public Div getBaseInfo(){
        Div parentLayout = super.getBaseInfo();

        EditableSelector requiredBuildingSelector = new EditableSelector(Objects.requireNonNull(Database.getEditable(building.getType(), building.getRequiredBuilding())));
        requiredBuildingSelector.setEditableChangedListener(editable -> this.building.setRequiredBuilding(editable.getId()));

        Label label1 = new Label("Required Building");
        label1.setWidth("125px");
        label1.getElement().getStyle().set("font-weight"," bold");
        HorizontalLayout layout1 = new HorizontalLayout(label1, requiredBuildingSelector);
        layout1.setAlignItems(FlexComponent.Alignment.CENTER);
        parentLayout.add(layout1);
        return parentLayout;
    }
}
