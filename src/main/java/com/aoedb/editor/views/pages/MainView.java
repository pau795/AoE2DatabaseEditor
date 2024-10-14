package com.aoedb.editor.views.pages;

import com.aoedb.editor.views.create.AddEditableView;
import com.aoedb.editor.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Main")
@Route(value = "main", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class MainView extends VerticalLayout {

    public MainView(){
        Button button = new Button("New Editable");
        AddEditableView addEditableView = new AddEditableView();
        button.addClickListener(event -> {
            addEditableView.open();
        });
        this.add(button);
    }
}
