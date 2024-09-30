package com.aoedb.editor.create;

import com.aoedb.editor.data.entity.Technology;
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


public class NewTechView extends NewEditableView {
    private final EditableSelector mainSelector;
    private final EditableSelector statSelector;
    private final EditableSelector upgradesSelector;
    private final EditableSelector effectSelector;
    private final EditableSelector availabilitySelector;
    private final EditableSelector bonusSelector;
    private final Button createButton;


    public NewTechView(Dialog dialog){
        super(dialog);

        this.mainSelector = new EditableSelector(Unit.getNone());
        this.statSelector = new EditableSelector(Unit.getNone());
        this.upgradesSelector = new EditableSelector(Unit.getNone());
        this.effectSelector = new EditableSelector(Unit.getNone());
        this.availabilitySelector = new EditableSelector(Unit.getNone());
        this.bonusSelector = new EditableSelector(Unit.getNone());

        this.createButton = new Button("Create");
        this.createButton.addClassNames("new-editable-button");
        this.createButton.setEnabled(false);
        this.createButton.addClickListener(event -> this.createNewEditable());

        this.dialog.addDialogCloseActionListener(event -> this.mainSelector.setEditable(Technology.getNone()));
        this.mainSelector.setEditableChangedListener(unit -> {
            this.statSelector.setEditable(unit);
            this.upgradesSelector.setEditable(unit);
            this.availabilitySelector.setEditable(unit);
            this.effectSelector.setEditable(unit);
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
        grid.add(getEntitySelectorTarget("Main tech", mainSelector),
                 getEntitySelectorTarget("Tech stats", statSelector),
                 getEntitySelectorTarget("Tech upgrades", upgradesSelector),
                 getEntitySelectorTarget("Tech availability", availabilitySelector),
                 getEntitySelectorTarget("Tech effect", effectSelector),
                 getEntitySelectorTarget("Tech bonus", bonusSelector)
        );
        layout.add(grid, createButton);
        return layout;
    }



    public void createNewEditable(){
        this.dialog.close();
        Technology mainTech = (Technology) this.mainSelector.getEditable();
        Technology statTech = (Technology) this.statSelector.getEditable();
        Technology upgradesTech = (Technology) this.upgradesSelector.getEditable();
        Technology availabilityTech = (Technology) this.availabilitySelector.getEditable();
        Technology effectTech = (Technology) this.effectSelector.getEditable();
        Technology bonusTech = (Technology) this.bonusSelector.getEditable();

        Technology newTechnology = new Technology(Database.getUnitList().size() + 1, mainTech, statTech, upgradesTech, availabilityTech, bonusTech);
        Database.getTechList().add(newTechnology);
        Map<String, String> params = new HashMap<>();
        params.put("type", newTechnology.getType());
        params.put("id", String.valueOf(newTechnology.getId()));
        this.createButton.getUI().ifPresent(ui -> ui.navigate(EditEditableView.class, new RouteParameters(params)));
    }
}
