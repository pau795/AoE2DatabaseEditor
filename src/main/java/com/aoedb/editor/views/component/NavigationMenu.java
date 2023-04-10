package com.aoedb.editor.views.component;

import com.aoedb.editor.data.bonus.CivBonus;
import com.aoedb.editor.data.bonus.HiddenBonus;
import com.aoedb.editor.data.entity.Building;
import com.aoedb.editor.data.entity.Civilization;
import com.aoedb.editor.data.entity.Technology;
import com.aoedb.editor.data.entity.Unit;
import com.aoedb.editor.data.items.EcoStat;
import com.aoedb.editor.data.items.GatheringRates;
import com.aoedb.editor.data.items.Stat;
import com.aoedb.editor.data.simple.*;
import com.aoedb.editor.database.Database;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class NavigationMenu extends Div {

    List<Details> detailList;

    FilterableVerticalList.ChildClick listener;
    String query;
    boolean collapse = false;
    HashMap<String, FilterableVerticalList<? extends Editable>> map;
    public NavigationMenu(){
        detailList = new ArrayList<>();
        map =  new LinkedHashMap<>();
        query = "";
        TextField textField = new TextField();
        textField.setPlaceholder(Database.getString("filter_hint"));
        textField.setPrefixComponent(VaadinIcon.SEARCH.create());
        textField.setValueChangeMode(ValueChangeMode.EAGER);
        textField.addValueChangeListener(event->{
            query = textField.getValue();
            performFilter();
        });
        Button collapseAll = new Button(VaadinIcon.ANGLE_RIGHT.create());
        collapseAll.addClassNames("navbar-collapse");
        collapseAll.addClickListener(event -> {
            for (Details d: detailList){
                d.setOpened(collapse);
            }
            collapse = !collapse;
            collapseAll.setIcon(collapse? VaadinIcon.ANGLE_DOWN.create(): VaadinIcon.ANGLE_RIGHT.create());
        });
        Div header =  new Div(textField, collapseAll);
        header.addClassNames("navbar-header");
        initDetailMap();
        this.add(header);
        for (String s : map.keySet()) {
            Details detail = new Details(new Label(getSummary(s, map.get(s).getSize())), map.get(s));
            detailList.add(detail);
            this.add(detail);
        }
        this.addClassNames("navbar-layout");
        performFilter();
    }

    private void initDetailMap(){
        FilterableVerticalList<Unit> unitList = new FilterableVerticalList<>();
        unitList.setItems(Database.getUnitList());
        unitList.addChildClickListener(editable -> {
            if (listener != null) this.listener.onChildClick(editable);
        });
        map.put("Units", unitList);

        FilterableVerticalList<Building> buildingList = new FilterableVerticalList<>();
        buildingList.setItems(Database.getBuildingList());
        buildingList.addChildClickListener(editable -> {
            if (listener != null) this.listener.onChildClick(editable);
        });
        map.put("Buildings", buildingList);

        FilterableVerticalList<Technology> techList = new FilterableVerticalList<>();
        techList.setItems(Database.getTechList());
        techList.addChildClickListener(editable -> {
            if (listener != null) this.listener.onChildClick(editable);
        });
        map.put("Technologies", techList);

        FilterableVerticalList<Civilization> civList = new FilterableVerticalList<>();
        civList.setItems(Database.getCivList());
        civList.addChildClickListener(editable -> {
            if (listener != null) this.listener.onChildClick(editable);
        });
        map.put("Civilizations", civList);

        FilterableVerticalList<ClassElement> classList = new FilterableVerticalList<>();
        classList.setItems(Database.getClassList());
        classList.addChildClickListener(editable -> {
            if (listener != null) this.listener.onChildClick(editable);
        });
        map.put("Classes", classList);

        FilterableVerticalList<TypeElement> typeList = new FilterableVerticalList<>();
        typeList.setItems(Database.getTypeList());
        typeList.addChildClickListener(editable -> {
            if (listener != null) this.listener.onChildClick(editable);
        });
        map.put("Types", typeList);

        FilterableVerticalList<TauntElement> tauntList = new FilterableVerticalList<>();
        tauntList.setItems(Database.getTauntList());
        tauntList.addChildClickListener(editable -> {
            if (listener != null) this.listener.onChildClick(editable);
        });
        map.put("Taunts", tauntList);

        FilterableVerticalList<PerformanceElement> performanceList = new FilterableVerticalList<>();
        performanceList.setItems(Database.getPerformanceList());
        performanceList.addChildClickListener(editable -> {
            if (listener != null) this.listener.onChildClick(editable);
        });
        map.put("Performance", performanceList);

        FilterableVerticalList<HistoryElement> historyList = new FilterableVerticalList<>();
        historyList.setItems(Database.getHistoryList());
        historyList.addChildClickListener(editable -> {
            if (listener != null) this.listener.onChildClick(editable);
        });
        map.put("History", historyList);

        FilterableVerticalList<Stat> statList = new FilterableVerticalList<>();
        statList.setItems(Database.getStatList());
        statList.addChildClickListener(editable -> {
            if (listener != null) this.listener.onChildClick(editable);
        });
        map.put("Stats", statList);

        FilterableVerticalList<EcoStat> ecoStatList = new FilterableVerticalList<>();
        ecoStatList.setItems(Database.getEcoStatsList());
        ecoStatList.addChildClickListener(editable -> {
            if (listener != null) this.listener.onChildClick(editable);
        });
        map.put("Eco Stats", ecoStatList);

        FilterableVerticalList<GatheringRates> gatheringRatesList = new FilterableVerticalList<>();
        gatheringRatesList.setItems(Database.getGatheringRates());
        gatheringRatesList.addChildClickListener(editable -> {
            if (listener != null) this.listener.onChildClick(editable);
        });
        map.put("Gathering Rates", gatheringRatesList);

        FilterableVerticalList<CivBonus> bonusList = new FilterableVerticalList<>();
        bonusList.setItems(Database.getBonusList());
        bonusList.addChildClickListener(editable -> {
            if (listener != null) this.listener.onChildClick(editable);
        });
        map.put("Bonuses", bonusList);

        FilterableVerticalList<HiddenBonus> hiddenBonusList = new FilterableVerticalList<>();
        hiddenBonusList.setItems(Database.getHiddenBonusList());
        hiddenBonusList.addChildClickListener(editable -> {
            if (listener != null) this.listener.onChildClick(editable);
        });
        map.put("Hidden Bonuses", hiddenBonusList);

    }


    public void addChildClickListener(FilterableVerticalList.ChildClick listener){
        this.listener = listener;
    }
    private String getSummary(String s, int size){
        return s + " - " + size;
    }

    public void performFilter(){
        int i = 0;
        for (String s : map.keySet()){
            map.get(s).performFilter(query);
            Details detail = detailList.get(i);
            detail.setSummary(new Label(getSummary(s, map.get(s).getSize())));
            detail.setVisible(map.get(s).getSize() > 0);
            detail.setOpened(map.get(s).getSize() > 0);
            ++i;

        }

    }
}
