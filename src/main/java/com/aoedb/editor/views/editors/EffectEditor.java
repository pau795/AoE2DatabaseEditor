package com.aoedb.editor.views.editors;

import com.aoedb.editor.data.components.EffectContainer;
import com.aoedb.editor.data.components.EffectContainer.*;
import com.aoedb.editor.data.simple.Editable;
import com.aoedb.editor.data.simple.ResourceElement;
import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.components.Divider;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.renderer.ComponentRenderer;

import java.util.*;

public class EffectEditor extends VerticalLayout {

    VerticalLayout statsEffectList;
    VerticalLayout costEffectList;
    VerticalLayout ecoEffectList;
    VerticalLayout attackEffectList;
    VerticalLayout armorEffectList;

    public EffectEditor(EffectContainer container){

        VerticalLayout statEffectsLayout = new VerticalLayout();
        VerticalLayout costEffectsLayout = new VerticalLayout();
        VerticalLayout ecoEffectsLayout = new VerticalLayout();
        VerticalLayout attackEffectsLayout = new VerticalLayout();
        VerticalLayout armorEffectsLayout = new VerticalLayout();

        Label statEffectLabel = new Label("Stat Effects");
        Label costEffectLabel = new Label("Cost Effects");
        Label ecoEffectLabel = new Label("Eco Effects");
        Label attackEffectLabel = new Label("Attack Effects");
        Label armorEffectLabel = new Label("Armor Effects");

        statEffectLabel.setClassName("bonus-effect-category-title");
        costEffectLabel.setClassName("bonus-effect-category-title");
        ecoEffectLabel.setClassName("bonus-effect-category-title");
        attackEffectLabel.setClassName("bonus-effect-category-title");
        armorEffectLabel.setClassName("bonus-effect-category-title");

        statsEffectList = new VerticalLayout();
        costEffectList = new VerticalLayout();
        ecoEffectList = new VerticalLayout();
        attackEffectList = new VerticalLayout();
        armorEffectList = new VerticalLayout();

        statsEffectList.setPadding(false);
        costEffectList.setPadding(false);
        ecoEffectList.setPadding(false);
        attackEffectList.setPadding(false);
        armorEffectList.setPadding(false);

        statEffectsLayout.setPadding(false);
        costEffectsLayout.setPadding(false);
        ecoEffectsLayout.setPadding(false);
        attackEffectsLayout.setPadding(false);
        armorEffectsLayout.setPadding(false);

        container.getStatsEffects().forEach(effect -> statsEffectList.add(new EffectLayout(effect, EffectContainer.STAT)));
        container.getCostEffects().forEach(effect -> costEffectList.add(new EffectLayout(effect, EffectContainer.COST)));
        container.getEcoEffects().forEach(effect -> ecoEffectList.add(new EffectLayout(effect, EffectContainer.ECO)));
        container.getAttackEffects().forEach(effect -> attackEffectList.add(new EffectLayout(effect, EffectContainer.ATTACK)));
        container.getArmorEffects().forEach(effect -> armorEffectList.add(new EffectLayout(effect, EffectContainer.ARMOR)));


        Button addStatEffectButton = new Button(VaadinIcon.PLUS.create());
        Button addCostEffectButton = new Button(VaadinIcon.PLUS.create());
        Button addEcoEffectButton = new Button(VaadinIcon.PLUS.create());
        Button addAttackEffectButton = new Button(VaadinIcon.PLUS.create());
        Button addArmorEffectButton = new Button(VaadinIcon.PLUS.create());

        addStatEffectButton.setText("Add Stat Effect");
        addCostEffectButton.setText("Add Cost Effect");
        addEcoEffectButton.setText("Add Eco Effect");
        addAttackEffectButton.setText("Add Attack Effect");
        addArmorEffectButton.setText("Add Armor Effect");

        addStatEffectButton.addClickListener(event -> statsEffectList.add(new EffectLayout(new EffectItem(container.isStaggered()), EffectContainer.STAT)));
        addCostEffectButton.addClickListener(event -> costEffectList.add(new EffectLayout(new EffectItem(container.isStaggered()), EffectContainer.COST)));
        addEcoEffectButton.addClickListener(event -> ecoEffectList.add(new EffectLayout(new EffectItem(container.isStaggered()), EffectContainer.ECO)));
        addAttackEffectButton.addClickListener(event -> attackEffectList.add(new EffectLayout(new EffectItem(container.isStaggered()), EffectContainer.ATTACK)));
        addArmorEffectButton.addClickListener(event -> armorEffectList.add(new EffectLayout(new EffectItem(container.isStaggered()), EffectContainer.ARMOR)));

        statEffectsLayout.add(statEffectLabel, new Divider(false), statsEffectList, addStatEffectButton);
        costEffectsLayout.add(costEffectLabel, new Divider(false), costEffectList, addCostEffectButton);
        ecoEffectsLayout.add(ecoEffectLabel, new Divider(false), ecoEffectList, addEcoEffectButton);
        attackEffectsLayout.add(attackEffectLabel, new Divider(false), attackEffectList, addAttackEffectButton);
        armorEffectsLayout.add(armorEffectLabel, new Divider(false), armorEffectList, addArmorEffectButton);

        if (container.getCivID() != 0) {
            EditableSelector civSelector = new EditableSelector(Database.getCivilization(container.getCivID()));
            civSelector.setEditableChangedListener(civ -> container.setCivID(civ.getId()));

            Select<String> globalFilter = new Select<>();
            globalFilter.setItems(EffectContainer.GLOBAL_FILTER_LIST);
            globalFilter.setValue(container.getGlobalFilter());
            globalFilter.addValueChangeListener(event -> container.setGlobalFilter(event.getValue()));

            Checkbox teamBonus = new Checkbox();
            teamBonus.setLabel("Team Bonus");
            teamBonus.setValue(container.isTeamBonus());
            teamBonus.addValueChangeListener(event -> container.setTeamBonus(event.getValue()));

            Checkbox staggered = new Checkbox();
            staggered.setLabel("Staggered");
            staggered.setValue(container.isStaggered());
            staggered.addValueChangeListener(event -> {
                container.setStaggered(event.getValue());
                statsEffectList.getChildren().map(c -> (EffectLayout)c).forEach(e -> e.setStaggeredView(event.getValue()));
                costEffectList.getChildren().map(c -> (EffectLayout)c).forEach(e -> e.setStaggeredView(event.getValue()));
                ecoEffectList.getChildren().map(c -> (EffectLayout)c).forEach(e -> e.setStaggeredView(event.getValue()));
                attackEffectList.getChildren().map(c -> (EffectLayout)c).forEach(e -> e.setStaggeredView(event.getValue()));
                armorEffectList.getChildren().map(c -> (EffectLayout)c).forEach(e -> e.setStaggeredView(event.getValue()));
            });


            HorizontalLayout globalSettingsLayout = new HorizontalLayout(civSelector, new Label("Global Filter"), globalFilter, teamBonus, staggered);
            globalSettingsLayout.setAlignItems(Alignment.CENTER);
            add(globalSettingsLayout);
        }
        add(statEffectsLayout, costEffectsLayout, ecoEffectsLayout, attackEffectsLayout, armorEffectsLayout);

    }

    private class EffectLayout extends VerticalLayout {

        NumberField singleValue, darkAgeValue, feudalAgeValue, castleAgeValue, imperialAgeValue;

        HashMap<String, List<Integer>> filterIdsByFilterType;

        EffectItem effectItem;
        Div filterGrid;
        
        public EffectLayout(EffectItem effectItem, String effectType) {

            this.effectItem = effectItem;

            EditableSelector techSelector = new EditableSelector(Database.getTechnology(effectItem.getTechRequirement()));
            techSelector.setEditableChangedListener(tech -> effectItem.setTechRequirement(tech.getId()));
            techSelector.setVisible(effectItem.getFilter().equals(EffectContainer.TECH_REQUIREMENT));
            techSelector.addClassNames("margin-match-label");

            Checkbox afSecProj = new Checkbox();
            afSecProj.setLabel("Affects Secondary Projectile");
            afSecProj.setValue(effectItem.isAffectsSecondaryProjectile());
            afSecProj.addValueChangeListener(event -> effectItem.setAffectsSecondaryProjectile(event.getValue()));
            afSecProj.setClassName("margin-match-label");

            Select<String> operatorSelector = new Select<>();
            operatorSelector.setItems(EffectItem.OPERATOR_LIST);
            operatorSelector.setValue(effectItem.getOperator());
            operatorSelector.addValueChangeListener(event -> effectItem.setOperator(event.getValue()));
            operatorSelector.setLabel("Operator");

            ComboBox<Editable> statSelector = new ComboBox<>();
            ComboBox.ItemFilter<Editable> filter = (editable1, filterString) -> editable1.getType().toLowerCase(Locale.ROOT).contains(filterString.toLowerCase(Locale.ROOT)) || Database.getString(editable1.getName()).toLowerCase(Locale.ROOT).contains(filterString.toLowerCase(Locale.ROOT));
            switch (effectType) {
                case EffectContainer.STAT:
                    statSelector.setItems(filter, (Collection<Editable>) Database.getEditableList(Database.STAT));
                    statSelector.addValueChangeListener(event -> effectItem.setStatID(String.valueOf(event.getValue().getId())));
                    statSelector.setValue(Database.getStat(Integer.parseInt(effectItem.getStatID())));
                    break;
                case EffectContainer.COST:
                    List<Editable> list = (List<Editable>) Database.getResourceList();
                    statSelector.setItems(filter, list);
                    statSelector.addValueChangeListener(event -> effectItem.setStatID(event.getValue().getType()));
                    statSelector.setValue(list.get(ResourceElement.getResourceId(effectItem.getStatID())));
                    break;
                case EffectContainer.ECO:
                    statSelector.setItems(filter, (Collection<Editable>) Database.getEditableList(Database.ECO_STAT));
                    statSelector.addValueChangeListener(event -> effectItem.setStatID(String.valueOf(event.getValue().getId())));
                    statSelector.setValue(Database.getEcoStat(Integer.parseInt(effectItem.getStatID())));
                    break;
                case EffectContainer.ATTACK:
                case EffectContainer.ARMOR:
                    statSelector.setItems(filter, (Collection<Editable>) Database.getEditableList(Database.TYPE));
                    statSelector.addValueChangeListener(event -> effectItem.setStatID(String.valueOf(event.getValue().getId())));
                    statSelector.setValue(Database.getType(Integer.parseInt(effectItem.getStatID())));
                    break;
            }
            statSelector.setItemLabelGenerator(e -> Database.getString(e.getName()));
            statSelector.setRenderer(new ComponentRenderer<>(e -> e.getEditableView().getLabel()));
            statSelector.setLabel("Stat");


            Button deleteEffectButton = new Button(VaadinIcon.TRASH.create());
            deleteEffectButton.setText("Delete Effect");
            deleteEffectButton.addClassNames("margin-match-label");
            deleteEffectButton.addClickListener(event -> {
                switch (effectType) {
                    case EffectContainer.STAT:
                        statsEffectList.remove(this);
                        break;
                    case EffectContainer.COST:
                        costEffectList.remove(this);
                        break;
                    case EffectContainer.ECO:
                        ecoEffectList.remove(this);
                        break;
                    case EffectContainer.ATTACK:
                        attackEffectList.remove(this);
                        break;
                    case EffectContainer.ARMOR:
                        armorEffectList.remove(this);
                        break;
                }
            });

            Button addFilterEntityButton = new Button(VaadinIcon.PLUS.create());
            addFilterEntityButton.setText("Add Filter Entity");
            addFilterEntityButton.addClassNames("margin-match-label");
            addFilterEntityButton.setEnabled(!effectItem.getFilter().equals(EffectContainer.NONE) && !effectItem.getFilter().equals(EffectContainer.TECH_REQUIREMENT));
            addFilterEntityButton.addClickListener(event -> {
                filterIdsByFilterType.get(effectItem.getFilter()).add(0);
                addFilterGridItem(0);
            });

            singleValue = new NumberField();
            darkAgeValue = new NumberField();
            feudalAgeValue = new NumberField();
            castleAgeValue = new NumberField();
            imperialAgeValue = new NumberField();

            singleValue.setLabel("Value");
            darkAgeValue.setLabel("Dark Age Value");
            feudalAgeValue.setLabel("Feudal Age Value");
            castleAgeValue.setLabel("Castle Age Value");
            imperialAgeValue.setLabel("Imperial Age Value");

            singleValue.addValueChangeListener(event -> effectItem.setSingleValue(event.getValue()));
            darkAgeValue.addValueChangeListener(event -> effectItem.setStaggeredValue(event.getValue(), 0));
            feudalAgeValue.addValueChangeListener(event -> effectItem.setStaggeredValue(event.getValue(),1));
            castleAgeValue.addValueChangeListener(event -> effectItem.setStaggeredValue(event.getValue(), 2));
            imperialAgeValue.addValueChangeListener(event -> effectItem.setStaggeredValue(event.getValue(), 3));

            singleValue.setValue(effectItem.getSingleValue());
            darkAgeValue.setValue(effectItem.getStaggeredValue(0));
            feudalAgeValue.setValue(effectItem.getStaggeredValue(1));
            castleAgeValue.setValue(effectItem.getStaggeredValue(2));
            imperialAgeValue.setValue(effectItem.getStaggeredValue(3));

            HorizontalLayout effectStatLayout = new HorizontalLayout();
            effectStatLayout.setAlignItems(Alignment.CENTER);
            effectStatLayout.add(statSelector, operatorSelector, singleValue, darkAgeValue, feudalAgeValue, castleAgeValue, imperialAgeValue, addFilterEntityButton, deleteEffectButton);

            VerticalLayout mainFilterLayout = new VerticalLayout();
            mainFilterLayout.setPadding(false);



            filterGrid = new Div();
            filterGrid.addClassNames("entities-layout-grid", "standard-horizontal-padding");
            populateFilterIdsGrid();

            filterIdsByFilterType = new HashMap<>();
            for(String type : EffectItem.FILTER_LIST) filterIdsByFilterType.put(type, new ArrayList<>());
            filterIdsByFilterType.put(effectItem.getFilter(), effectItem.getFilterEntitiesIDs());

            Select<String> effectFilter = new Select<>();
            effectFilter.setItems(EffectItem.FILTER_LIST);
            effectFilter.addValueChangeListener(event -> {
                effectItem.setFilter(event.getValue());
                techSelector.setVisible(event.getValue().equals(EffectContainer.TECH_REQUIREMENT));
                afSecProj.setVisible(!event.getValue().equals(EffectContainer.NONE));
                effectItem.setFilterEntitiesIDs(filterIdsByFilterType.get(effectItem.getFilter()));
                populateFilterIdsGrid();
                addFilterEntityButton.setEnabled(!effectItem.getFilter().equals(EffectContainer.NONE) && !effectItem.getFilter().equals(EffectContainer.TECH_REQUIREMENT));
            });
            effectFilter.setValue(effectItem.getFilter());
            effectFilter.setLabel("Filter Type");

            HorizontalLayout effectFilterLayout = new HorizontalLayout();
            effectFilterLayout.setAlignItems(Alignment.CENTER);
            effectFilterLayout.add(effectFilter, techSelector, afSecProj);

            mainFilterLayout.add(effectFilterLayout, filterGrid);

            setStaggeredView(effectItem.getStaggered());

            setPadding(false);
            setSpacing(false);
            addClassNames("black-border-bottom");
            add(effectStatLayout, mainFilterLayout);
        }

        public void setStaggeredView(boolean staggered){
            singleValue.setVisible(!staggered);
            darkAgeValue.setVisible(staggered);
            feudalAgeValue.setVisible(staggered);
            castleAgeValue.setVisible(staggered);
            imperialAgeValue.setVisible(staggered);
        }

        private void populateFilterIdsGrid(){
            filterGrid.removeAll();
            if (!effectItem.getFilter().equals(EffectContainer.NONE) && !effectItem.getFilter().equals(EffectContainer.TECH_REQUIREMENT))
                effectItem.getFilterEntitiesIDs().forEach(this::addFilterGridItem);
        }

        private void addFilterGridItem(Integer id){
            EditableSelector entitySelector = new EditableSelector(Database.getEditable(effectItem.getFilter(), id));
            entitySelector.setDeleteButton(true);
            entitySelector.setEditableChangedListener(entity -> {
                filterIdsByFilterType.get(effectItem.getFilter()).remove(Integer.valueOf(entitySelector.getEditable().getId()));
                filterIdsByFilterType.get(effectItem.getFilter()).add(entity.getId());
            });
            entitySelector.setRemoveEditableListener(event -> {
                filterIdsByFilterType.get(effectItem.getFilter()).remove(id);
                filterGrid.remove(entitySelector);
            });
            filterGrid.add(entitySelector);
        }
    }

}
