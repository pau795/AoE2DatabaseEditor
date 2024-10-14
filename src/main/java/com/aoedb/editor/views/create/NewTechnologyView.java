package com.aoedb.editor.views.create;

import com.aoedb.editor.data.entity.Technology;
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


public class NewTechnologyView extends NewEditableView {
    private final EditableSelector mainSelector;
    private final EditableSelector statSelector;
    private final EditableSelector upgradesSelector;
    private final EditableSelector effectSelector;
    private final EditableSelector availabilitySelector;
    private final EditableSelector bonusSelector;
    private final Button createButton;


    public NewTechnologyView(Dialog dialog){
        super(dialog);

        this.mainSelector = new EditableSelector(Technology.getNone());
        this.statSelector = new EditableSelector(Technology.getNone());
        this.upgradesSelector = new EditableSelector(Technology.getNone());
        this.effectSelector = new EditableSelector(Technology.getNone());
        this.availabilitySelector = new EditableSelector(Technology.getNone());
        this.bonusSelector = new EditableSelector(Technology.getNone());

        this.createButton = new Button("Create");
        this.createButton.addClassNames("new-editable-button");
        this.createButton.setEnabled(false);
        this.createButton.addClickListener(event -> this.createNewEditable());

        this.dialog.addDialogCloseActionListener(event -> this.mainSelector.setEditable(Technology.getNone()));
        this.mainSelector.setEditableChangedListener(tech -> {
            this.statSelector.setEditable(tech);
            this.upgradesSelector.setEditable(tech);
            this.availabilitySelector.setEditable(tech);
            this.effectSelector.setEditable(tech);
            this.bonusSelector.setEditable(tech);
            if (tech.getId() != 0) this.createButton.setEnabled(true);
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

        Technology newTechnology = new Technology(Database.getTechList().size() + 1, mainTech, statTech, upgradesTech, availabilityTech, effectTech, bonusTech);
        Database.getTechList().add(newTechnology);
        Map<String, String> params = new HashMap<>();
        params.put("type", newTechnology.getType());
        params.put("id", String.valueOf(newTechnology.getId()));
        this.createButton.getUI().ifPresent(ui -> ui.navigate(EditEditableView.class, new RouteParameters(params)));
    }
}
