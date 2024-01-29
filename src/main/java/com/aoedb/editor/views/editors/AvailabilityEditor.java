package com.aoedb.editor.views.editors;

import com.aoedb.editor.data.components.Availability;
import com.aoedb.editor.data.entity.Civilization;
import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.components.Divider;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dnd.DragSource;
import com.vaadin.flow.component.dnd.DropTarget;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AvailabilityEditor extends HorizontalLayout {

    public AvailabilityEditor(Availability availability){
        List<Civilization> civilizationList = Database.getCivList();
        List<Civilization> availableCivs = availability.getAvailableCivs().stream().map(Database::getCivilization).collect(Collectors.toList());
        List<Civilization> unavailableCivs = civilizationList.stream().filter(e -> !availableCivs.contains(e)).collect(Collectors.toList());

        VerticalLayout availablePanel =  new VerticalLayout();
        VerticalLayout unavailablePanel =  new VerticalLayout();
        H4 availableHeader = new H4("Available");
        H4 unavailableHeader = new H4("Unavailable");
        VerticalLayout availableDropPanel = new VerticalLayout(availableCivs.stream().map(e -> {
            DragCivLabel label = new DragCivLabel(e);
            DragSource.create(label);
            return label;
        }).toArray(Component[]::new));
        availableDropPanel.setHeightFull();
        VerticalLayout unavailableDropPanel = new VerticalLayout(unavailableCivs.stream().map(e ->{
            DragCivLabel label = new DragCivLabel(e);
            DragSource.create(label);
            return label;
        }).toArray(Component[]::new));
        unavailableDropPanel.setHeightFull();
        DropTarget<VerticalLayout> availableDropTarget = DropTarget.create(availableDropPanel);
        availableDropTarget.addDropListener(event -> {
            event.getDragSourceComponent().ifPresent(availableDropPanel::add);
            event.getDragSourceComponent().ifPresent(component -> {
                int civID = ((DragCivLabel) component).getCivilization().getId();
                availability.getAvailableCivs().add(civID);
                Collections.sort(availability.getAvailableCivs());
            });
        });
        DropTarget<VerticalLayout> unavailableDropTarget = DropTarget.create(unavailableDropPanel);
        unavailableDropTarget.addDropListener(event -> {
            event.getDragSourceComponent().ifPresent(unavailableDropPanel::add);
            event.getDragSourceComponent().ifPresent(component -> {
                int civID = ((DragCivLabel) component).getCivilization().getId();
                availability.getAvailableCivs().remove(Integer.valueOf(civID));
                Collections.sort(availability.getAvailableCivs());
            });
        });
        availablePanel.add(availableHeader, new Divider(false), availableDropPanel);
        unavailablePanel.add(unavailableHeader, new Divider(false), unavailableDropPanel);
        setHeightFull();
        add(availablePanel, new Divider(true), unavailablePanel);
    }

    private static class DragCivLabel extends Div implements DragSource<DragCivLabel>{

        private final Civilization civ;

        public DragCivLabel(Civilization civ){
            this.civ = civ;
            setDraggable(true);
            add(civ.getEditableView().getLabel());
        }

        public Civilization getCivilization() {
            return civ;
        }
    }

}
