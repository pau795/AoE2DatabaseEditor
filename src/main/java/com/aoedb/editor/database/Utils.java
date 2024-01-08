package com.aoedb.editor.database;

import com.aoedb.editor.data.bonus.CivBonus;
import com.aoedb.editor.data.bonus.HiddenBonus;
import com.aoedb.editor.data.entity.Building;
import com.aoedb.editor.data.entity.Civilization;
import com.aoedb.editor.data.entity.Technology;
import com.aoedb.editor.data.entity.Unit;
import com.aoedb.editor.data.items.EcoStat;
import com.aoedb.editor.data.items.GatheringRates;
import com.aoedb.editor.data.items.Stat;
import com.aoedb.editor.data.simple.*;

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

    public static String unCamelCase(String s){
        String aux = s.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ), " ");
        return  aux.substring(0, 1).toUpperCase() + aux.substring(1);
    }

    public static String camelCase(String s){
        String[] words = s.split("[\\W_]+");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (i == 0) {
                word = word.isEmpty() ? word : word.toLowerCase();
            } else {
                word = word.isEmpty() ? word : Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
            }
            builder.append(word);
        }
        return builder.toString();
    }
}
