package com.aoedb.editor.views.create;

import com.aoedb.editor.data.bonus.CivBonus;
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


public class NewCivBonusView extends NewEditableView {
    private final EditableSelector mainSelector;
    private final Button createButton;


    public NewCivBonusView(Dialog dialog){
        super(dialog);

        this.mainSelector = new EditableSelector(CivBonus.getNone());

        this.createButton = new Button("Create");
        this.createButton.addClassNames("new-editable-button");
        this.createButton.setEnabled(false);
        this.createButton.addClickListener(event -> this.createNewEditable());

        this.dialog.addDialogCloseActionListener(event -> this.mainSelector.setEditable(CivBonus.getNone()));
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
        grid.add(getEntitySelectorTarget("Main bonus", mainSelector));
        layout.add(grid, createButton);
        return layout;
    }



    public void createNewEditable(){
        this.dialog.close();
        CivBonus mainBonus = (CivBonus) this.mainSelector.getEditable();
        CivBonus newBonus = new CivBonus(Database.getBonusList().size() + 1, mainBonus);
        Database.getBonusList().add(newBonus);
        Map<String, String> params = new HashMap<>();
        params.put("type", newBonus.getType());
        params.put("id", String.valueOf(newBonus.getId()));
        this.createButton.getUI().ifPresent(ui -> ui.navigate(EditEditableView.class, new RouteParameters(params)));
    }
}
