package com.aoedb.editor.views.pages;



import com.aoedb.editor.data.simple.Editable;
import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.MainLayout;

import com.vaadin.flow.component.Component;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

@PageTitle("Edit")
@RoutePrefix("edit")
@Route(value = ":type?/:id?", layout = MainLayout.class)
public class EditEditableView extends VerticalLayout implements BeforeEnterObserver {

    Editable editable;
    Component editableComponent;

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        RouteParameters routeParameters = beforeEnterEvent.getRouteParameters();
        String type = routeParameters.get("type").orElse(Database.UNIT);
        int id = Integer.parseInt(routeParameters.get("id").orElse("0"));
        editable = Database.getEditable(type, id);
        editableComponent = editable.getEditableView().getView();
        this.removeAll();
        this.add(editableComponent);
    }
}
