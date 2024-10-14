package com.aoedb.editor.views.create;

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


public class NewUnitView extends NewEditableView {
    private final EditableSelector mainSelector;
    private final EditableSelector statSelector;
    private final EditableSelector attackSelector;
    private final EditableSelector armorSelector;
    private final EditableSelector upgradesSelector;
    private final EditableSelector availabilitySelector;
    private final EditableSelector performanceSelector;
    private final EditableSelector bonusSelector;
    private final Button createButton;


    public NewUnitView(Dialog dialog){
        super(dialog);

        this.mainSelector = new EditableSelector(Unit.getNone());
        this.statSelector = new EditableSelector(Unit.getNone());
        this.attackSelector = new EditableSelector(Unit.getNone());
        this.armorSelector = new EditableSelector(Unit.getNone());
        this.upgradesSelector = new EditableSelector(Unit.getNone());
        this.availabilitySelector = new EditableSelector(Unit.getNone());
        this.performanceSelector = new EditableSelector(Unit.getNone());
        this.bonusSelector = new EditableSelector(Unit.getNone());

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
            this.performanceSelector.setEditable(unit);
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
        grid.add(getEntitySelectorTarget("Main unit", mainSelector),
            getEntitySelectorTarget("Unit stats", statSelector),
            getEntitySelectorTarget("Unit attack", attackSelector),
            getEntitySelectorTarget("Unit armor", armorSelector),
            getEntitySelectorTarget("Unit upgrades", upgradesSelector),
            getEntitySelectorTarget("Unit availability", availabilitySelector),
            getEntitySelectorTarget("Unit performance", performanceSelector),
            getEntitySelectorTarget("Unit bonus", bonusSelector)
        );
        layout.add(grid, createButton);
        return layout;
    }



    public void createNewEditable(){
        this.dialog.close();
        Unit mainUnit = (Unit) this.mainSelector.getEditable();
        Unit statUnit = (Unit) this.statSelector.getEditable();
        Unit attackUnit = (Unit) this.attackSelector.getEditable();
        Unit armorUnit = (Unit) this.armorSelector.getEditable();
        Unit upgradesUnit = (Unit) this.upgradesSelector.getEditable();
        Unit availabilityUnit = (Unit) this.availabilitySelector.getEditable();
        Unit performanceUnit = (Unit) this.performanceSelector.getEditable();
        Unit bonusUnit = (Unit) this.bonusSelector.getEditable();

        Unit newUnit = new Unit(Database.getUnitList().size() + 1, mainUnit, statUnit, attackUnit, armorUnit,
                upgradesUnit, availabilityUnit, performanceUnit, bonusUnit);
        Database.getUnitList().add(newUnit);
        Map<String, String> params = new HashMap<>();
        params.put("type", newUnit.getType());
        params.put("id", String.valueOf(newUnit.getId()));
        this.createButton.getUI().ifPresent(ui -> ui.navigate(EditEditableView.class, new RouteParameters(params)));
    }
}
