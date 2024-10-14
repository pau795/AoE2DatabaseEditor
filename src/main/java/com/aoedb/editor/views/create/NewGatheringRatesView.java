package com.aoedb.editor.views.create;

import com.aoedb.editor.data.items.GatheringRates;
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


public class NewGatheringRatesView extends NewEditableView {
    private final EditableSelector mainSelector;
    private final Button createButton;


    public NewGatheringRatesView(Dialog dialog){
        super(dialog);
        this.mainSelector = new EditableSelector(GatheringRates.getNone());

        this.createButton = new Button("Create");
        this.createButton.addClassNames("new-editable-button");
        this.createButton.setEnabled(false);
        this.createButton.addClickListener(event -> this.createNewEditable());

        this.dialog.addDialogCloseActionListener(event -> this.mainSelector.setEditable(GatheringRates.getNone()));
        this.mainSelector.setEditableChangedListener(bonus -> {
            if (bonus.getId() != 0) this.createButton.setEnabled(true);
        });
    }

    public VerticalLayout getView(){
        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);
        layout.setSpacing(false);
        Div grid = new Div();
        grid.addClassNames("new-editable-grid");
        grid.add(getEntitySelectorTarget("Main Gathering Rate", mainSelector));
        layout.add(grid, createButton);
        return layout;
    }

    public void createNewEditable(){
        this.dialog.close();
        GatheringRates mainGatheringRate = (GatheringRates) this.mainSelector.getEditable();
        GatheringRates newGatheringRate = new GatheringRates(Database.getGatheringRates().size() + 1, mainGatheringRate);
        Database.getGatheringRates().add(newGatheringRate);
        Map<String, String> params = new HashMap<>();
        params.put("type", newGatheringRate.getType());
        params.put("id", String.valueOf(newGatheringRate.getId()));
        this.createButton.getUI().ifPresent(ui -> ui.navigate(EditEditableView.class, new RouteParameters(params)));
    }
}
