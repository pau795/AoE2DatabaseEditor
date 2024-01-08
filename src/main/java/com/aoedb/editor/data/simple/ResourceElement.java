package com.aoedb.editor.data.simple;

import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.editable.EditableView;
import com.aoedb.editor.views.editable.ResourceView;

public class ResourceElement extends Editable{

    public ResourceElement(int id) {
        super(id);
    }

    @Override
    public String getName() {
        switch(id){
            case 0: return "res_wood";
            case 1: return "res_food";
            case 2: return "res_gold";
            case 3: return "res_stone";
            default: return "performance_group1_category1";
        }
    }

    @Override
    public String getType() {
        switch(id){
            case 0: return Database.WOOD;
            case 1: return Database.FOOD;
            case 2: return  Database.GOLD;
            case 3: return  Database.STONE;
            default: return  Database.ALL;
        }
    }

    @Override
    public EditableView getEditableView() {
        return new ResourceView(this);
    }

    public static int getResourceId(String resource){
        switch(resource){
            case Database.WOOD: return 0;
            case Database.FOOD: return 1;
            case Database.GOLD: return 2;
            case Database.STONE: return 3;
            default: return 4;
        }
    }
}
