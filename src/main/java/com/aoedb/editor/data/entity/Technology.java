package com.aoedb.editor.data.entity;

import com.aoedb.editor.data.components.EffectContainer;
import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.editable.EditableView;
import com.aoedb.editor.views.editable.TechnologyView;


public class Technology extends Entity {

    private EffectContainer techEffect;

    public Technology(int id) {
        super(id);
    }

    @Override
    public String getName() {
        if (id == 0) return "none";
        if (id == -1) return "dark_age";
        return String.format("tech_name_%d", this.id);
    }

    @Override
    public String getType(){
        return Database.TECH;
    }

    @Override
    public EditableView getEditableView() {
        return new TechnologyView(this);
    }

    public EffectContainer getTechEffect() {
        return techEffect;
    }

    public void setTechEffect(EffectContainer techEffect) {
        this.techEffect = techEffect;
    }

    @Override
    public String getCreatorType(){
        return Database.BUILDING;
    }

    public static Technology getDarkAge(){
        Technology darkAge = new Technology(-1);
        darkAge.setImagePath("t_dark_age");
        return darkAge;
    }

    public static Technology getNone(){
        Technology none = new Technology(0);
        none.setImagePath("t_white");
        return none;
    }
}
