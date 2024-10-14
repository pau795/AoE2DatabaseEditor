package com.aoedb.editor.views.create;

import com.aoedb.editor.data.bonus.HiddenBonus;
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


public class NewHiddenBonusView extends NewEditableView {
    private final EditableSelector mainSelector;
    private final Button createButton;


    public NewHiddenBonusView(Dialog dialog){
        super(dialog);

        this.mainSelector = new EditableSelector(HiddenBonus.getNone());

        this.createButton = new Button("Create");
        this.createButton.addClassNames("new-editable-button");
        this.createButton.setEnabled(false);
        this.createButton.addClickListener(event -> this.createNewEditable());

        this.dialog.addDialogCloseActionListener(event -> this.mainSelector.setEditable(HiddenBonus.getNone()));
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
        grid.add(getEntitySelectorTarget("Main Hidden Bonus", mainSelector));
        layout.add(grid, createButton);
        return layout;
    }



    public void createNewEditable(){
        this.dialog.close();
        HiddenBonus mainBonus = (HiddenBonus) this.mainSelector.getEditable();
        HiddenBonus newBonus = new HiddenBonus(Database.getHiddenBonusList().size() + 1, mainBonus);
        Database.getHiddenBonusList().add(newBonus);
        Map<String, String> params = new HashMap<>();
        params.put("type", newBonus.getType());
        params.put("id", String.valueOf(newBonus.getId()));
        this.createButton.getUI().ifPresent(ui -> ui.navigate(EditEditableView.class, new RouteParameters(params)));
    }
}
