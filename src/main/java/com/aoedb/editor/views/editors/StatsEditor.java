package com.aoedb.editor.views.editors;

import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.components.Divider;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class StatsEditor extends VerticalLayout {

    Map<String, String> statMap;
    Div statLayout;
    boolean fixing;

    public StatsEditor(Map<String, String> statMap){
        this.statMap = statMap;
        this.statLayout = new Div();
        this.statLayout.addClassNames("layout-grid");
        Button button = new Button(VaadinIcon.PLUS.create());
        button.setText("Add Stat");
        button.addClickListener(event ->{
           HorizontalLayout newStat = statPanel("","-");
           statLayout.add(newStat);
        });
        for (String stat : statMap.keySet())statLayout.add(statPanel(stat, statMap.get(stat)));
        add(statLayout, new Divider(false), button);
    }

    private HorizontalLayout statPanel(String stat, String value){
        HorizontalLayout statDisplay = new HorizontalLayout();
        AtomicReference<String> currentStat = new AtomicReference<>(stat);
        ComboBox<String> statSelector = new ComboBox<>();
        statSelector.setItems(Database.getStatListNames());
        statSelector.setValue(currentStat.get());
        fixing = false;
        statSelector.addValueChangeListener(event -> {
            if (fixing || !statMap.containsKey(event.getValue())) {
                currentStat.set(event.getValue());
                String statValue = statMap.getOrDefault(event.getOldValue(), "-");
                if (!fixing) statMap.remove(event.getOldValue());
                if (!fixing && !currentStat.get().isEmpty()) statMap.put(currentStat.get(), statValue);
                fixing = false;
            }
            else {
                fixing = true;
                statSelector.setValue(event.getOldValue());
            }
        });
        TextField statValueEditor = new TextField();
        statValueEditor.setWidth("75px");
        statValueEditor.setValue(value);
        statValueEditor.addValueChangeListener(event -> {
            if (!currentStat.get().isEmpty()) statMap.put(currentStat.get(), event.getValue());
        });
        Button deleteButton = new Button(VaadinIcon.TRASH.create());
        deleteButton.addClickListener(event -> {
            statLayout.remove(statDisplay);
            statMap.remove(currentStat.get());
        });
        statDisplay.add(statSelector, statValueEditor, deleteButton);
        return statDisplay;
    }

}
