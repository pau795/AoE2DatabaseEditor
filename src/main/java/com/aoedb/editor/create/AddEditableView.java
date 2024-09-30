package com.aoedb.editor.create;

import com.aoedb.editor.database.Database;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;

public class AddEditableView extends Dialog {

    private static final String[] editableTypes = {
            Database.NONE,
            Database.UNIT,
            Database.BUILDING,
            Database.TECH,
            Database.CIV,
            Database.CLASS,
            Database.TYPE,
            Database.PERFORMANCE,
            Database.TAUNT,
            Database.HISTORY,
            Database.STAT,
            Database.ECO_STAT,
            Database.GATHERING_RATES,
            Database.BONUS,
            Database.HIDDEN_BONUS
    };

    private final VerticalLayout newEditablePanel;

    public AddEditableView(){
        H2 title = new H2("Create new editable");
        title.addClassNames("dialog-title");
        newEditablePanel = new VerticalLayout();
        newEditablePanel.setMinWidth("600px");
        newEditablePanel.setMinHeight("300px");
        newEditablePanel.setPadding(false);
        Select<String> typeSelect = new Select<>();
        typeSelect.setItems(editableTypes);
        typeSelect.setLabel("Select New Editable Type");
        typeSelect.addValueChangeListener(e -> {
            newEditablePanel.removeAll();
            newEditablePanel.add(getNewEditableComponent(e.getValue()));
        });
        this.addDialogCloseActionListener(event -> {
            this.close();
            typeSelect.setValue(Database.NONE);
        });
        this.add(title, new Hr(), typeSelect, newEditablePanel);
        this.setCloseOnEsc(true);
        this.setCloseOnOutsideClick(true);
    }

    private Component getNewEditableComponent(String type){
        switch (type){
            case Database.UNIT: return new NewUnitView(this).getView();
            case Database.BUILDING: return new NewBuildingView(this).getView();
//            case Database.TECH: return new NewTechnologyView();
//            case Database.CIV: return new NewCivilizationView();
//            case Database.CLASS: return new NewClassView();
//            case Database.TYPE: return new NewTypeView();
//            case Database.PERFORMANCE: return new NewPerformanceView();
//            case Database.TAUNT: return new NewTauntView();
//            case Database.HISTORY: return new NewHistoryView();
//            case Database.STAT: return new NewStatView();
//            case Database.ECO_STAT: return new NewEcoStatView();
//            case Database.GATHERING_RATES: return new NewGatheringRatesView();
//            case Database.BONUS: return new NewBonusView();
//            case Database.HIDDEN_BONUS: return new NewHiddenBonusView();
            default: return new Div();
        }
    }

}
