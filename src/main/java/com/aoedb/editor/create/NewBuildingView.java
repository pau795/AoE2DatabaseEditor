package com.aoedb.editor.create;

import com.aoedb.editor.data.entity.Building;
import com.aoedb.editor.data.entity.Unit;
import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.editors.EditableSelector;
import com.aoedb.editor.views.pages.EditEditableView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouteParameters;

import java.util.HashMap;
import java.util.Map;


public class NewBuildingView extends NewEditableView {
    private final EditableSelector mainSelector;
    private final EditableSelector statSelector;
    private final EditableSelector attackSelector;
    private final EditableSelector armorSelector;
    private final EditableSelector upgradesSelector;
    private final EditableSelector availabilitySelector;
    private final EditableSelector trainableSelector;
    private final EditableSelector bonusSelector;
    private final Button createButton;


    public NewBuildingView(Dialog dialog){
        super(dialog);

        this.mainSelector = new EditableSelector(Building.getNone());
        this.statSelector = new EditableSelector(Building.getNone());
        this.attackSelector = new EditableSelector(Building.getNone());
        this.armorSelector = new EditableSelector(Building.getNone());
        this.upgradesSelector = new EditableSelector(Building.getNone());
        this.availabilitySelector = new EditableSelector(Building.getNone());
        this.trainableSelector = new EditableSelector(Building.getNone());
        this.bonusSelector = new EditableSelector(Building.getNone());

        this.createButton = new Button("Create");
        this.createButton.addClassNames("new-editable-button");
        this.createButton.setEnabled(false);
        this.createButton.addClickListener(event -> this.createNewEditable());

        this.dialog.addDialogCloseActionListener(event -> this.mainSelector.setEditable(Unit.getNone()));
        this.mainSelector.setEditableChangedListener(unit -> {
            this.statSelector.setEditable(unit);
            this.attackSelector.setEditable(unit);
            this.armorSelector.setEditable(unit);
            this.upgradesSelector.setEditable(unit);
            this.availabilitySelector.setEditable(unit);
            this.trainableSelector.setEditable(unit);
            this.bonusSelector.setEditable(unit);
            if (unit.getId() != 0) this.createButton.setEnabled(true);
        });

    }


    public VerticalLayout getView(){
        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);
        layout.setSpacing(false);
        Div grid = new Div();
        grid.addClassNames("new-editable-grid");
        grid.add(getEntitySelectorTarget("Main building", mainSelector),
                getEntitySelectorTarget("Building stats", statSelector),
                getEntitySelectorTarget("Building attack", attackSelector),
                getEntitySelectorTarget("Building armor", armorSelector),
                getEntitySelectorTarget("Building upgrades", upgradesSelector),
                getEntitySelectorTarget("Building availability", availabilitySelector),
                getEntitySelectorTarget("Building trainable", trainableSelector),
                getEntitySelectorTarget("Building bonus", bonusSelector)
        );
        layout.add(grid, createButton);
        return layout;
    }

    public void createNewEditable(){
        this.dialog.close();
        Building mainBuilding = (Building) this.mainSelector.getEditable();
        Building statBuilding = (Building) this.statSelector.getEditable();
        Building attackBuilding = (Building) this.attackSelector.getEditable();
        Building armorBuilding = (Building) this.armorSelector.getEditable();
        Building upgradesBuilding = (Building) this.upgradesSelector.getEditable();
        Building availabilityBuilding = (Building) this.availabilitySelector.getEditable();
        Building performanceBuilding = (Building) this.trainableSelector.getEditable();
        Building bonusBuilding = (Building) this.bonusSelector.getEditable();

        Building newBuilding = new Building(Database.getBuildingList().size() + 1, mainBuilding, statBuilding, attackBuilding, armorBuilding,
                upgradesBuilding, availabilityBuilding, performanceBuilding, bonusBuilding);
        Database.getBuildingList().add(newBuilding);
        Map<String, String> params = new HashMap<>();
        params.put("type", newBuilding.getType());
        params.put("id", String.valueOf(newBuilding.getId()));
        this.createButton.getUI().ifPresent(ui -> ui.navigate(EditEditableView.class, new RouteParameters(params)));
    }
}
