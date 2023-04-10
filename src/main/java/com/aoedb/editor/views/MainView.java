package com.aoedb.editor.views;
import com.aoedb.editor.data.entity.Entity;
import com.aoedb.editor.data.simple.Editable;
import com.aoedb.editor.database.Database;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.*;

import java.util.HashMap;


@PageTitle("Main")
@Route
@RouteAlias(value = "", layout = MainLayout.class)
@CssImport(value = "./themes/aoe2databaseeditor/components/general.css")
public class MainView extends VerticalLayout {
    Tabs tabs;
    HashMap<Tab, Component> tabComponentMap;
    HashMap<Editable, Tab> entityTabMap;

    Div pages;

    public MainView(){
        tabs = new Tabs();
        tabComponentMap = new HashMap<>();
        entityTabMap = new HashMap<>();
        tabs.addSelectedChangeListener(event -> selectTab());
        pages = new Div();
        pages.addClassNames("main-div");
        this.add(tabs, pages);
        this.setSpacing(false);
        this.setMargin(false);
        this.setPadding(false);
    }

    private void selectTab(){
        tabComponentMap.values().forEach(page -> page.setVisible(false));
        Component selectedPage = tabComponentMap.get(tabs.getSelectedTab());
        selectedPage.setVisible(true);
    }

    public void addTab(Editable e){

        if (entityTabMap.containsKey(e)) {
            Tab t = entityTabMap.get(e);
            tabs.setSelectedTab(t);
        }
        else {
            if (entityTabMap.size() < 10) {
                Tab t = new Tab(e.getEditableLabel());
                Component c = new Label(Database.getString(e.getName())); //TODO getComponentFromEditable
                entityTabMap.put(e, t);
                tabComponentMap.put(t, c);
                tabs.add(t);
                pages.add(c);
                Button close = new Button(VaadinIcon.CLOSE_SMALL.create());
                close.addClassNames("tab-close");
                close.addClickListener(event -> {
                    tabs.remove(t);
                    pages.remove(c);
                    entityTabMap.remove(e);
                    tabComponentMap.remove(t);
                });
                t.add(close);
                tabs.setSelectedTab(t);
            }
            else{
                Notification notification = Notification
                        .show("You can only have 10 tabs opened.");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.MIDDLE);
            }
        }

    }
}
