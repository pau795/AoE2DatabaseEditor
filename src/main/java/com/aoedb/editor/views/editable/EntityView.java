package com.aoedb.editor.views.editable;

import com.aoedb.editor.data.entity.Entity;
import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.editors.*;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Objects;

public abstract class EntityView extends ImageEditableView {

    private final Entity entity;

    public EntityView(Entity e){
        super(e);
        this.entity = e;
    }

    @Override
    public Div getView(){
        Div mainLayout = super.getView();

        Accordion descriptionAccordion = new Accordion();
        descriptionAccordion.add("Descriptions", new DescriptionEditor(entity));
        descriptionAccordion.close();

        Accordion baseAccordion = new Accordion();
        baseAccordion.add("Base info", new VerticalLayout(getBaseInfo()));
        baseAccordion.close();

        Accordion statsAccordion = new Accordion();
        statsAccordion.add("Stats", new StatsEditor(entity.getStatMap()));
        statsAccordion.close();

        Accordion upgradesAccordion = new Accordion();
        upgradesAccordion.add("Upgrades", new UpgradeEditor(entity.getUpgrades()));
        upgradesAccordion.close();

        Accordion availabilityAccordion = new Accordion();
        availabilityAccordion.add("Availability", new AvailabilityEditor(entity.getAvailability()));
        availabilityAccordion.close();

        Accordion bonusContainerAccordion = new Accordion();
        bonusContainerAccordion.add("Bonuses", new BonusContainerEditor(entity.getBonusContainer()));
        bonusContainerAccordion.close();

        mainLayout.add(descriptionAccordion, baseAccordion, statsAccordion, upgradesAccordion, availabilityAccordion, bonusContainerAccordion);
        return mainLayout;
    }

    public Div getBaseInfo(){
        EditableSelector creatorSelector = new EditableSelector(Objects.requireNonNull(Database.getEditable(entity.getCreatorType(), entity.getCreatorID())));
        creatorSelector.setEditableChangedListener(editable -> this.entity.setCreatorID(editable.getId()));

        EditableSelector ageSelector = new EditableSelector(Objects.requireNonNull(Database.getEditable(Database.TECH, entity.getAgeID())));
        ageSelector.setEditableChangedListener(editable -> this.entity.setAgeID(editable.getId()));

        EditableSelector requiredTechnologySelector = new EditableSelector(Objects.requireNonNull(Database.getEditable(Database.TECH, entity.getRequiredTechnologyID())));
        requiredTechnologySelector.setEditableChangedListener(editable -> this.entity.setRequiredTechnologyID(editable.getId()));

        EditableSelector nextUpgradeSelector = new EditableSelector(Objects.requireNonNull(Database.getEditable(entity.getType(), entity.getNextUpgradeID())));
        nextUpgradeSelector.setEditableChangedListener(editable -> this.entity.setNextUpgradeID(editable.getId()));

        Label label1 = new Label("Creator");
        Label label2 = new Label("Age");
        Label label3 = new Label("Required Tech");
        Label label4 = new Label("Next Upgrade");
        label1.setWidth("125px");
        label2.setWidth("125px");
        label3.setWidth("125px");
        label4.setWidth("125px");
        label1.getElement().getStyle().set("font-weight"," bold");
        label2.getElement().getStyle().set("font-weight"," bold");
        label3.getElement().getStyle().set("font-weight"," bold");
        label4.getElement().getStyle().set("font-weight"," bold");
        HorizontalLayout layout1 = new HorizontalLayout(label1, creatorSelector);
        HorizontalLayout layout2 = new HorizontalLayout(label2, ageSelector);
        HorizontalLayout layout3 = new HorizontalLayout(label3, requiredTechnologySelector);
        HorizontalLayout layout4 = new HorizontalLayout(label4, nextUpgradeSelector);
        layout1.setAlignItems(FlexComponent.Alignment.CENTER);
        layout2.setAlignItems(FlexComponent.Alignment.CENTER);
        layout3.setAlignItems(FlexComponent.Alignment.CENTER);
        layout4.setAlignItems(FlexComponent.Alignment.CENTER);
        Div div = new Div(layout1, layout2, layout3, layout4);
        div.setClassName("base-stats-layout-grid");
        return div;
    }
}
