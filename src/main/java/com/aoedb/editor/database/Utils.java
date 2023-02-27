package com.aoedb.editor.database;

public class Utils  {

    public static String getFileName(String type){
        switch (type){
            case Database.UNIT: return Database.UNIT_LIST;
            case Database.BUILDING: return Database.BUILDING_LIST;
            case Database.TECH: return Database.TECH_LIST;
            case Database.CIV: return Database.CIVILIZATION_LIST;
            case Database.CLASS: return Database.CLASS_LIST;
            case Database.TYPE: return Database.TYPE_LIST;
            case Database.PERFORMANCE: return Database.PERFORMANCE_LIST;
            case Database.HISTORY: return Database.HISTORY_LIST;
            case Database.STAT: return Database.STAT_LIST;
            case Database.ECO_STAT: return Database.ECO_LIST;
            case Database.TAUNT: return Database.TAUNT_LIST;
            case Database.BONUS: return Database.BONUS_LIST;
            case Database.HIDDEN_BONUS: return Database.HIDDEN_BONUS;
            case Database.GATHERING_RATES: return Database.GATHERING_RATES;
            default: return Database.EMPTY;
        }
    }

    public static String getEffectFileName(String file){
        if (Database.HIDDEN_BONUS.equals(file)) return Database.HIDDEN_BONUS_EFFECT;
        else return Database.BONUS_EFFECT;
    }
}
