package com.aoedb.editor.data.components;

import java.util.List;

public class BonusEffectContainer {

    public static class EffectItem{
        private String filter;
        private int techRequirement;
        private boolean affectsSecondaryProjectile;
        private List<Integer> filterEntitiesIDs;

        private String statID;
        private String operator;

        boolean staggered;
        double singleValue;
        double[] staggeredValues;

        public EffectItem(boolean staggered){
            this.staggered = staggered;
            staggeredValues = new double[] {0,0,0,0};
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

        public double getValue(int age){
            if(staggered) return staggeredValues[age];
            else return singleValue;
        }


    }

    public static final String CLASS = "class";
    public static final String UNIT = "unit";
    public static final String BUILDING = "building";
    public static final String TECH = "tech";
    public static final String TECH_REQUIREMENT = "techRequirement";

    public static final String ADD = "+";
    public static final String MINUS = "-";
    public static final String MULT = "*";
    public static final String DIV = "/";
    public static final String SET = "@";

    int civID;
    boolean teamBonus;

    private String globalFilter;
    private boolean staggered;

    private List<EffectItem> statsEffects;
    private List<EffectItem> costEffects;
    private List<EffectItem> ecoEffects;
    private List<EffectItem> attackEffects;
    private List<EffectItem> armorEffects;

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
