package com.aoedb.editor.data.components;

import com.aoedb.editor.database.Database;

import java.util.ArrayList;
import java.util.List;

public class EffectContainer {

    public static class EffectItem{

        public static final String ADD = "+";
        public static final String MINUS = "-";
        public static final String MULT = "*";
        public static final String DIV = "/";
        public static final String SET = "@";

        public static final String[] FILTER_LIST = {NONE, CLASS, UNIT, BUILDING, TECH, TECH_REQUIREMENT};
        public static final String[] OPERATOR_LIST = {ADD, MINUS, MULT, DIV, SET};

        private String filter;
        private Integer techRequirement;
        private Boolean affectsSecondaryProjectile;
        private List<Integer> filterEntitiesIDs;

        private String statID;
        private String operator;

        private Boolean staggered;
        private Double singleValue;
        private final Double[] staggeredValues;

        public EffectItem(boolean staggered){
            this.statID = "1";
            this.staggered = staggered;
            this.staggeredValues = new Double[] {0.0,0.0,0.0,0.0};
            this.singleValue = 0.0;
            this.setFilter(Database.NONE);
            this.setAffectsSecondaryProjectile(false);
            this.setTechRequirement(0);
            this.setFilterEntitiesIDs(new ArrayList<>());
        }

        public EffectItem(EffectItem other){
            this.filter = other.filter;
            this.techRequirement = other.techRequirement;
            this.affectsSecondaryProjectile = other.affectsSecondaryProjectile;
            this.filterEntitiesIDs = new ArrayList<>(other.filterEntitiesIDs);
            this.statID = other.statID;
            this.operator = other.operator;
            this.staggered = other.staggered;
            this.singleValue = other.singleValue;
            this.staggeredValues = new Double[] {
                other.staggeredValues[0],
                other.staggeredValues[1],
                other.staggeredValues[2],
                other.staggeredValues[3]
            };
        }

        public String getFilter() {
            return filter;
        }

        public void setFilter(String filter) {
            this.filter = filter;
        }

        public int getTechRequirement() {
            return techRequirement;
        }

        public void setTechRequirement(int techRequirement) {
            this.techRequirement = techRequirement;
        }

        public boolean isAffectsSecondaryProjectile() {
            return affectsSecondaryProjectile;
        }

        public void setAffectsSecondaryProjectile(boolean affectsSecondaryProjectile) {
            this.affectsSecondaryProjectile = affectsSecondaryProjectile;
        }

        public List<Integer> getFilterEntitiesIDs() {
            return filterEntitiesIDs;
        }

        public void setFilterEntitiesIDs(List<Integer> filterEntitiesIDs) {
            this.filterEntitiesIDs = filterEntitiesIDs;
        }

        public String getStatID() {
            return statID;
        }

        public void setStatID(String statID) {
            this.statID = statID;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public void setValue(String v){
            if (staggered){
                String[] values = v.split(" ");
                for (int i=0; i<values.length; ++i){
                    staggeredValues[i] = Double.parseDouble(values[i]);
                }
            }
            else{
                singleValue = Double.parseDouble(v);
            }
        }

        public void setSingleValue(Double v){
            singleValue = v;
        }

        public void setStaggeredValue(Double v, int pos){
            staggeredValues[pos] = v;
        }

        public double getStaggeredValue(int age){
            return staggeredValues[age];
        }

        public double getSingleValue(){
            return singleValue;
        }

        public Boolean getStaggered() {
            return staggered;
        }

        public void setStaggered(Boolean staggered) {
            this.staggered = staggered;
        }
    }

    public static final String NONE = "none";
    public static final String CLASS = "class";
    public static final String UNIT = "unit";
    public static final String BUILDING = "building";
    public static final String TECH = "tech";
    public static final String TECH_REQUIREMENT = "techRequirement";

    public static final String STAT = "stat";
    public static final String COST = "cost";
    public static final String ECO = "eco";
    public static final String ATTACK = "attack";
    public static final String ARMOR = "armor";


    public static final String[] GLOBAL_FILTER_LIST = {NONE, UNIT, BUILDING, TECH};

    Integer civID;
    Boolean teamBonus;

    private String globalFilter;
    private Boolean staggered;

    private List<EffectItem> statsEffects;
    private List<EffectItem> costEffects;
    private List<EffectItem> ecoEffects;
    private List<EffectItem> attackEffects;
    private List<EffectItem> armorEffects;

    public EffectContainer(){}

    public EffectContainer(EffectContainer other){
        this.civID = other.civID;
        this.teamBonus = other.teamBonus;
        this. globalFilter = other.globalFilter;
        this.staggered = other.staggered;

        this.statsEffects = new ArrayList<>();
        this.costEffects = new ArrayList<>();
        this.ecoEffects = new ArrayList<>();
        this.attackEffects = new ArrayList<>();
        this.armorEffects = new ArrayList<>();

        for (EffectItem effect : other.statsEffects) this.statsEffects.add(new EffectItem(effect));
        for (EffectItem effect : other.costEffects) this.costEffects.add(new EffectItem(effect));
        for (EffectItem effect : other.ecoEffects) this.ecoEffects.add(new EffectItem(effect));
        for (EffectItem effect : other.attackEffects) this.attackEffects.add(new EffectItem(effect));
        for (EffectItem effect : other.armorEffects) this.armorEffects.add(new EffectItem(effect));
    }

    public int getCivID() {
        return civID;
    }

    public void setCivID(int civID) {
        this.civID = civID;
    }

    public boolean isTeamBonus() {
        return teamBonus;
    }

    public void setTeamBonus(boolean teamBonus) {
        this.teamBonus = teamBonus;
    }

    public String getGlobalFilter() {
        return globalFilter;
    }

    public void setGlobalFilter(String globalFilter) {
        this.globalFilter = globalFilter;
    }

    public boolean isStaggered() {
        return staggered;
    }

    public void setStaggered(boolean staggered) {
        this.staggered = staggered;
        this.statsEffects.forEach(effect -> effect.setStaggered(staggered));
        this.costEffects.forEach(effect -> effect.setStaggered(staggered));
        this.ecoEffects.forEach(effect -> effect.setStaggered(staggered));
        this.attackEffects.forEach(effect -> effect.setStaggered(staggered));
        this.armorEffects.forEach(effect -> effect.setStaggered(staggered));
    }

    public List<EffectItem> getStatsEffects() {
        return statsEffects;
    }

    public void setStatsEffects(List<EffectItem> statsEffects) {
        this.statsEffects = statsEffects;
    }

    public List<EffectItem> getCostEffects() {
        return costEffects;
    }

    public void setCostEffects(List<EffectItem> costEffects) {
        this.costEffects = costEffects;
    }

    public List<EffectItem> getEcoEffects() {
        return ecoEffects;
    }

    public void setEcoEffects(List<EffectItem> ecoEffects) {
        this.ecoEffects = ecoEffects;
    }

    public List<EffectItem> getAttackEffects() {
        return attackEffects;
    }

    public void setAttackEffects(List<EffectItem> attackEffects) {
        this.attackEffects = attackEffects;
    }

    public List<EffectItem> getArmorEffects() {
        return armorEffects;
    }

    public void setArmorEffects(List<EffectItem> armorEffects) {
        this.armorEffects = armorEffects;
    }
}
