package com.aoedb.editor.views.editors;

import com.aoedb.editor.data.components.Upgrades;
import com.aoedb.editor.data.components.Upgrades.*;
import com.aoedb.editor.data.entity.Technology;
import com.aoedb.editor.database.Database;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class UpgradeEditor extends VerticalLayout {

    VerticalLayout listLayout;
    Upgrades upgradesContainer;

    public UpgradeEditor(Upgrades upgradesContainer){
        this.upgradesContainer = upgradesContainer;
        listLayout = new VerticalLayout();
        listLayout.setPadding(false);
        listLayout.addClassNames("black-border-bottom");
        for (UpgradeList upgrade: upgradesContainer.getExpandableList()) listLayout.add(upgradesByBuilding(upgrade));
        Button addButton = new Button(VaadinIcon.PLUS.create());
        addButton.setText("Add Upgrade Category");
        addButton.addClickListener(event -> {
            UpgradeList newUpgradeList = new UpgradeList(0);
            upgradesContainer.addUpgradeList(newUpgradeList);
            listLayout.add(upgradesByBuilding(newUpgradeList));
        });
        add(listLayout, addButton);
    }

    private Div upgradesByBuilding(UpgradeList upgradeList){
        Div mainContainer = new Div();
        mainContainer.addClassNames("editable-component-base-layout");
        Div gridContainer = new Div();
        gridContainer.addClassNames("entities-layout-grid", "standard-horizontal-padding", "standard-top-padding");
        EditableSelector buildingSelector = new EditableSelector(Database.getBuilding(upgradeList.getElementID()));
        buildingSelector.setEditableChangedListener(building -> upgradeList.setElementID(building.getId()));
        Button addTech = new Button(VaadinIcon.PLUS.create());
        addTech.setText("Add Upgrade");
        addTech.addClickListener(event -> {
            int id = 0;
            upgradeList.getSubList().add(0);
            Technology t = Database.getTechnology(id);
            EditableSelector technologySelector = new EditableSelector(t);
            technologySelector.setDeleteButton(true);
            technologySelector.setEditableChangedListener(tech ->{
                upgradeList.getSubList().remove(Integer.valueOf(id));
                upgradeList.getSubList().add(tech.getId());
            });
            technologySelector.setRemoveEditableListener(event1 -> {
                upgradeList.getSubList().remove(Integer.valueOf(id));
                gridContainer.remove(technologySelector);
            });
            gridContainer.add(technologySelector);
        });
        Button removeHeader = new Button(VaadinIcon.TRASH.create());
        removeHeader.setText("Remove Upgrade Category");
        removeHeader.addClickListener(event -> {
           upgradesContainer.removeUpgradeList(upgradeList);
           listLayout.remove(mainContainer);
        });
        HorizontalLayout categoryHeader = new HorizontalLayout(buildingSelector, addTech, removeHeader);
        categoryHeader.addClassNames("upgrades-category-header");

        upgradeList.getSubList().forEach(id -> {
            Technology t = Database.getTechnology(id);
            EditableSelector technologySelector = new EditableSelector(t);
            technologySelector.setDeleteButton(true);
            technologySelector.setEditableChangedListener(tech ->{
                upgradeList.getSubList().remove(id);
                upgradeList.getSubList().add(tech.getId());
            });
            technologySelector.setRemoveEditableListener(event -> {
                upgradeList.getSubList().remove(id);
                gridContainer.remove(technologySelector);
            });
            gridContainer.add(technologySelector);
        });
        mainContainer.add(categoryHeader, gridContainer);
        return mainContainer;
    }

}
