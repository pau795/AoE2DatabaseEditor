package com.aoedb.editor.views;


import com.aoedb.editor.views.component.NavigationMenu;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Header;



/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    MainView mainView;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        addToDrawer(createNavigation());
    }

    private Component createHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.addClassNames("view-toggle");
        toggle.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        toggle.getElement().setAttribute("aria-label", "Menu toggle");
        Header header = new Header(toggle);
        header.addClassNames("view-header");
        return header;
    }



    private Component createNavigation() {
        NavigationMenu menu = new NavigationMenu();
        menu.addChildClickListener(editable -> {
            if (mainView != null) mainView.addTab(editable);
        });
        return menu;
    }


    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        this.mainView = (MainView) getContent();

        //TODO work with mainView
    }




}
