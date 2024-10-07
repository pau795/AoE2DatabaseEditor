package com.aoedb.editor.create;

import com.aoedb.editor.data.items.EcoStat;
import com.aoedb.editor.data.items.Stat;
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


public class NewStatView extends NewEditableView {
    private final EditableSelector mainSelector;
    private final Button createButton;


    public NewStatView(Dialog dialog){
        super(dialog);
        this.mainSelector = new EditableSelector(Stat.getNone());

        this.createButton = new Button("Create");
        this.createButton.addClassNames("new-editable-button");
        this.createButton.setEnabled(false);
        this.createButton.addClickListener(event -> this.createNewEditable());

        this.dialog.addDialogCloseActionListener(event -> this.mainSelector.setEditable(Stat.getNone()));
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
        grid.add(getEntitySelectorTarget("Main Stat", mainSelector));
        layout.add(grid, createButton);
        return layout;
    }

    public void createNewEditable(){
        this.dialog.close();
        Stat mainStat = (Stat) this.mainSelector.getEditable();
        Stat newStat = new Stat(Database.getStatList().size() + 1, mainStat);
        Database.getStatList().add(newStat);
        Map<String, String> params = new HashMap<>();
        params.put("type", newStat.getType());
        params.put("id", String.valueOf(newStat.getId()));
        this.createButton.getUI().ifPresent(ui -> ui.navigate(EditEditableView.class, new RouteParameters(params)));
    }
}
