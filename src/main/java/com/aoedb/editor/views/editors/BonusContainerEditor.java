package com.aoedb.editor.views.editors;

import com.aoedb.editor.data.components.BonusContainer;
import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.components.Divider;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public class BonusContainerEditor extends VerticalLayout {

    public BonusContainerEditor(BonusContainer container){

        //HEADER

        HorizontalLayout bonusHeaderLayout = new HorizontalLayout();
        HorizontalLayout teamBonusHeaderLayout = new HorizontalLayout();
        HorizontalLayout hiddenBonusHeaderLayout = new HorizontalLayout();
        HorizontalLayout uniqueTechsHeaderLayout = new HorizontalLayout();

        bonusHeaderLayout.setAlignItems(Alignment.CENTER);
        teamBonusHeaderLayout.setAlignItems(Alignment.CENTER);
        hiddenBonusHeaderLayout.setAlignItems(Alignment.CENTER);
        uniqueTechsHeaderLayout.setAlignItems(Alignment.CENTER);

        Label bonusHeaderLabel = new Label("Bonus List");
        Label teamBonusHeaderLabel = new Label("Team Bonus List");
        Label hiddenBonusHeaderLabel = new Label("Hidden Bonus List");
        Label uniqueTechsHeaderLabel = new Label("Unique Tech List");

        bonusHeaderLabel.setClassName("category-title");
        teamBonusHeaderLabel.setClassName("category-title");
        hiddenBonusHeaderLabel.setClassName("category-title");
        uniqueTechsHeaderLabel.setClassName("category-title");

        EditableListEditor bonusEditor = new EditableListEditor(container.getBonusList(), Database.BONUS);
        EditableListEditor teamBonusEditor = new EditableListEditor(container.getTeamBonusList(), Database.BONUS);
        EditableListEditor hiddenBonusEditor = new EditableListEditor(container.getHiddenBonusList(), Database.HIDDEN_BONUS);
        EditableListEditor uniqueTechEditor = new EditableListEditor(container.getUniqueTechList(), Database.TECH);

        Button addBonusButton = new Button(VaadinIcon.PLUS.create());
        Button addTeamBonusButton = new Button(VaadinIcon.PLUS.create());
        Button addHiddenBonusButton = new Button(VaadinIcon.PLUS.create());
        Button addUniqueTechsButton = new Button(VaadinIcon.PLUS.create());

        addBonusButton.setText("Add Bonus");
        addTeamBonusButton.setText("Add Team Bonus");
        addHiddenBonusButton.setText("Add Hidden Bonus");
        addUniqueTechsButton.setText("Add Unique Tech");

        addBonusButton.addClickListener(event -> bonusEditor.addItem(0));
        addTeamBonusButton.addClickListener(event -> teamBonusEditor.addItem(0));
        addHiddenBonusButton.addClickListener(event -> hiddenBonusEditor.addItem(0));
        addUniqueTechsButton.addClickListener(event -> uniqueTechEditor.addItem(0));

        bonusHeaderLayout.add(bonusHeaderLabel, addBonusButton);
        teamBonusHeaderLayout.add(teamBonusHeaderLabel, addTeamBonusButton);
        hiddenBonusHeaderLayout.add(hiddenBonusHeaderLabel, addHiddenBonusButton);
        uniqueTechsHeaderLayout.add(uniqueTechsHeaderLabel, addUniqueTechsButton);

        this.add(bonusHeaderLayout, new Divider(false), bonusEditor);
        this.add(teamBonusHeaderLayout, new Divider(false), teamBonusEditor);
        this.add(hiddenBonusHeaderLayout, new Divider(false), hiddenBonusEditor);
        this.add(uniqueTechsHeaderLayout, new Divider(false), uniqueTechEditor);
    }

}
