package com.aoedb.editor.database;

import com.aoedb.editor.data.bonus.CivBonus;
import com.aoedb.editor.data.bonus.HiddenBonus;
import com.aoedb.editor.data.entity.Building;
import com.aoedb.editor.data.entity.Civilization;
import com.aoedb.editor.data.entity.Technology;
import com.aoedb.editor.data.entity.Unit;
import com.aoedb.editor.data.items.*;
import com.aoedb.editor.data.simple.*;
import org.apache.http.conn.util.PublicSuffixList;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Database {

    public final static String ENTITY = "entity";
    public final static String UNIT = "unit";
    public final static String BUILDING = "building";
    public final static String TECH = "tech";
    public final static String CIV = "civilization";
    public final static String CLASS = "class";
    public final static String TYPE = "type";
    public final static String STAT = "stat";
    public final static String ECO_STAT = "eco";
    public final static String TAUNT = "taunt";
    public final static String PERFORMANCE = "performance";
    public final static String BONUS = "bonus";
    public final static String HISTORY = "history";
    public final static String EMPTY = "";
    public final static String NONE = "none";

    public static final String WOOD = "Wood";
    public static final String FOOD = "Food";
    public static final String GOLD = "Gold";
    public static final String STONE = "Stone";

    public static final String ALL = "All";

    public static final String WOOD_ICON = "r_wood";
    public static final String FOOD_ICON = "r_food";
    public static final String GOLD_ICON = "r_gold";
    public static final String STONE_ICON = "r_stone";

    //FILES
    public final static String BONUS_EFFECT = "bonus_effect";
    public final static String BONUS_LIST = "bonus_list";
    public final static String BUILDING_ARMOR = "building_armor";
    public final static String BUILDING_ATTACK = "building_attack";
    public final static String BUILDING_AVAILABILITY = "building_availability";
    public final static String BUILDING_BONUS = "building_bonus";
    public final static String BUILDING_GROUPS = "building_groups";
    public final static String BUILDING_LIST = "building_list";
    public final static String BUILDING_STATS = "building_stats";
    public final static String BUILDING_TRAINABLE = "building_trainable";
    public final static String BUILDING_UPGRADES = "building_upgrades";
    public final static String CIVILIZATION_GROUPS = "civilization_groups";
    public final static String CIVILIZATION_INFO = "civilization_info";
    public final static String CIVILIZATION_LIST = "civilization_list";
    public final static String CLASS_LIST = "class_list";
    public final static String ECO_LIST = "eco_list";
    public final static String ECO_UPGRADES = "eco_upgrades";
    public final static String GATHERING_RATES = "gathering_rates";
    public final static String HIDDEN_BONUS = "hidden_bonus";
    public final static String HIDDEN_BONUS_EFFECT = "hidden_bonus_effect";
    public final static String HISTORY_GROUPS = "history_groups";
    public final static String HISTORY_LIST = "history_list";
    public final static String HISTORY_TEXT = "history_text";
    public final static String PERFORMANCE_GROUPS = "performance_groups";
    public final static String PERFORMANCE_LIST = "performance_list";
    public final static String STAT_LIST = "stat_list";
    public final static String TAUNT_LIST = "taunt_list";
    public final static String TECH_AVAILABILITY = "tech_availability";
    public final static String TECH_BONUS = "tech_bonus";
    public final static String TECH_EFFECT = "tech_effect";
    public final static String TECH_GROUPS = "tech_groups";
    public final static String TECH_LIST = "tech_list";
    public final static String TECH_STATS = "tech_stats";
    public final static String TECH_TREE_QUIZ = "tech_tree_quiz";
    public final static String TECH_UPGRADES = "tech_upgrades";
    public final static String TYPE_LIST = "type_list";
    public final static String UNIT_ARMOR = "unit_armor";
    public final static String UNIT_ATTACK = "unit_attack";
    public final static String UNIT_AVAILABILITY = "unit_availability";
    public final static String UNIT_BONUS = "unit_bonus";
    public final static String UNIT_GROUPS = "unit_groups";
    public final static String UNIT_LIST = "unit_list";
    public final static String UNIT_PERFORMANCE = "unit_performance";
    public final static String UNIT_STATS = "unit_stats";
    public final static String UNIT_UPGRADES = "unit_upgrades";
    public final static String STRINGS = "strings";


    public static String BASE_DIR = "D:\\Proyectos\\AoE2DatabaseData\\";
    private static  List<Unit> unitList;
    private static  List<Building> buildingList;
    private static  List<Technology> techList;
    private static  List<Civilization> civList;
    private static  List<ClassElement> classList;
    private static  List<TypeElement> typeList;
    private static  List<TauntElement> tauntList;
    private static  List<PerformanceElement> performanceList;
    private static  List<HistoryElement> historyList;
    private static  List<Stat> statList;
    private static  List<EcoStat> ecoStatsList;
    private static  List<GatheringRates> gatheringRates;
    public static  List<CivBonus> bonusList;
    private static  List<HiddenBonus> hiddenBonusList;

    private static  StringData stringData;

    private static  GroupContainer unitGroups;
    private static  GroupContainer buildingGroups;
    private static  GroupContainer techGroups;
    private static  GroupContainer civGroups;
    private static  GroupContainer performanceGroups;
    private static  GroupContainer historyGroups;
    private static  TechTreeQuizQuestions techTreeQuizQuestions;

    private static  EcoUpgrades ecoUpgrades;
    private static  String currentLanguage;


    public static void loadDatabase(){
        Reader r = new Reader();
        currentLanguage = "en";

        stringData = r.readStringData(Arrays.asList("en", "es"));

        unitList = r.readUnits();
        buildingList = r.readBuildings();
        techList = r.readTechnologies();
        civList = r.readCivilizations();

        classList = r.getClassList(r.readImageEditableList(Database.CLASS));
        typeList = r.getTypeList(r.readImageEditableList(Database.TYPE));
        performanceList = r.getPerformanceList(r.readImageEditableList(Database.PERFORMANCE));
        historyList = r.getHistoryList(r.readImageEditableList(Database.HISTORY));
        tauntList = r.getTauntList(r.readEditableList(Database.TAUNT));
        statList =  r.readStats();
        ecoStatsList = r.readEcoStats();
        gatheringRates = r.readGatheringRates();

        bonusList = r.getCivBonuses(r.readBonuses(Database.BONUS));
        hiddenBonusList = r.getHiddenBonuses(r.readBonuses(Database.HIDDEN_BONUS));

        unitGroups = r.readGroups(Database.UNIT_GROUPS);
        buildingGroups = r.readGroups(Database.BUILDING_GROUPS);
        techGroups = r.readGroups(Database.TECH_GROUPS);
        civGroups = r.readGroups(Database.CIVILIZATION_GROUPS);
        performanceGroups = r.readGroups(Database.PERFORMANCE_GROUPS);
        historyGroups = r.readGroups(Database.HISTORY_GROUPS);
        techTreeQuizQuestions = r.readTechTreeQuestions();
        ecoUpgrades = r.readEcoUpgrades();
    }

    public static  String getString(String key){
        return stringData.getString(key, currentLanguage);
    }

    public static  String getString(String key, String language){
        return stringData.getString(key, language);
    }

    public static void setString(String key, String string, String language){
        stringData.setString(key, string, language);
    }

    public static  String getImage(String imageName){
        if (imageName.startsWith("g_")) return "images/"+imageName+".gif";
        else return "images/"+imageName+".png";
    }


    public static  String getSound(String soundName, String language){
        if (soundName.startsWith("t_")) return "sound/"+language+"/" + soundName +".ogg";
        else return "sound/en/" + soundName +".ogg";
    }


    public static  Unit getUnit(int id){
        if (id == 0) return Unit.getNone();
        return unitList.get(id - 1);
    }

    public static  Building getBuilding(int id){
        if (id == 0) return Building.getNone();
        return buildingList.get(id - 1);
    }

    public static Technology getTechnology(int id){
        if (id == 0) return Technology.getNone();
        if (id == -1) return Technology.getDarkAge();
        return techList.get(id - 1);
    }

    public static  Civilization getCivilization(int id){
        return civList.get(id - 1);
    }

    public static  ClassElement getClass(int id){
        return classList.get(id - 1);
    }

    public static  TypeElement getType(int id){
        return typeList.get(id - 1);
    }

    public static  PerformanceElement getPerformance(int id){
        return performanceList.get(id - 1);
    }

    public static  HistoryElement getHistory(int id){
        return historyList.get(id - 1);
    }

    public static  TauntElement getTaunt(int id){
        return tauntList.get(id - 1);
    }

    public static  Stat getStat(int id){
        return statList.get(id - 1);
    }

    public static  EcoStat getEcoStat(int id){
        return ecoStatsList.get(id - 1);
    }

    public static  GatheringRates getGatheringRates(int id){
        return gatheringRates.get(id - 1);
    }

    public static  CivBonus getBonus(int id){
        return bonusList.get(id - 1);
    }

    public static  HiddenBonus getHiddenBonus(int id){
        return hiddenBonusList.get(id - 1);
    }

    public static List<? extends Editable> getResourceList(){
        return Arrays.asList(new ResourceElement(0), new ResourceElement(1), new ResourceElement(2), new ResourceElement(3), new ResourceElement(4));
    }

    public static List<? extends Editable> getEditableList(String type){
        switch (type){
            case Database.UNIT: {
                List<Unit> list1 = new ArrayList<>(unitList);
                list1.add(Unit.getNone());
                return list1;
            }
            case Database.BUILDING: {
                List<Building> list1 = new ArrayList<>(buildingList);
                list1.add(Building.getNone());
                return list1;
            }
            case Database.TECH:
                List<Technology> list1 = new ArrayList<>(techList);
                list1.add(Technology.getNone());
                list1.add(Technology.getDarkAge());
                return list1;
            case Database.CIV: return civList;
            case Database.CLASS: return classList;
            case Database.TYPE: return typeList;
            case Database.PERFORMANCE: return performanceList;
            case Database.TAUNT: return tauntList;
            case Database.HISTORY: return historyList;
            case Database.STAT: return statList;
            case Database.ECO_STAT: return ecoStatsList;
            case Database.GATHERING_RATES: return gatheringRates;
            case Database.BONUS: return bonusList;
            case Database.HIDDEN_BONUS: return hiddenBonusList;
            default: return null;
        }
    }

    public static Editable getEditable(String type, int id){
        switch (type){
            case Database.UNIT: {
                if (id == 0) return Unit.getNone();
                return unitList.get(id - 1);
            }
            case Database.BUILDING: {
                if (id == 0) return Building.getNone();
                return buildingList.get(id - 1);
            }
            case Database.TECH: {
                if (id == 0) return Technology.getNone();
                if (id == -1) return Technology.getDarkAge();
                return techList.get(id - 1);
            }
            case Database.CIV: {
                if (id == 0) return Civilization.getNone();
                return civList.get(id - 1);
            }
            case Database.CLASS: {
                if (id == 0) return ClassElement.getNone();
                return classList.get(id - 1);
            }
            case Database.TYPE: return typeList.get(id - 1);
            case Database.PERFORMANCE: return performanceList.get(id - 1);
            case Database.TAUNT: return tauntList.get(id - 1);
            case Database.HISTORY: return historyList.get(id - 1);
            case Database.STAT: return statList.get(id - 1);
            case Database.ECO_STAT: return ecoStatsList.get(id - 1);
            case Database.GATHERING_RATES: return gatheringRates.get(id - 1);
            case Database.BONUS: return bonusList.get(id - 1);
            case Database.HIDDEN_BONUS: return hiddenBonusList.get(id - 1);
            default:  return Unit.getNone();
        }
    }


    public static  GroupContainer getGroup(String type) {
        switch (type){
            case Database.BUILDING_GROUPS: return buildingGroups;
            case Database.UNIT_GROUPS: return unitGroups;
            case Database.TECH_GROUPS: return techGroups;
            case Database.CIVILIZATION_GROUPS: return civGroups;
            case Database.PERFORMANCE_GROUPS: return performanceGroups;
            case Database.HISTORY_GROUPS: return historyGroups;
            default: return null;
        }
    }

    public static List<String> getStatListNames(){
        return statList.stream().map(stat -> Utils.camelCase(Database.getString(stat.getName()))).collect(Collectors.toList());
    }

    public static List<Unit> getUnitList() {
        return unitList;
    }

    public static List<Building> getBuildingList() {
        return buildingList;
    }

    public static List<Technology> getTechList() {
        return techList;
    }

    public static List<Civilization> getCivList() {
        return civList;
    }

    public static List<ClassElement> getClassList() {
        return classList;
    }

    public static List<TypeElement> getTypeList() {
        return typeList;
    }

    public static List<TauntElement> getTauntList() {
        return tauntList;
    }

    public static List<PerformanceElement> getPerformanceList() {
        return performanceList;
    }

    public static List<HistoryElement> getHistoryList() {
        return historyList;
    }

    public static List<Stat> getStatList() {
        return statList;
    }

    public static List<EcoStat> getEcoStatsList() {
        return ecoStatsList;
    }

    public static List<GatheringRates> getGatheringRates() {
        return gatheringRates;
    }

    public static List<CivBonus> getBonusList() {
        return bonusList;
    }

    public static List<HiddenBonus> getHiddenBonusList() {
        return hiddenBonusList;
    }

    public static  List<Editable> getAllEditables(){
        List<Editable> list = new ArrayList<>();
        list.addAll(unitList);
        list.addAll(buildingList);
        list.addAll(techList);
        list.addAll(civList);
        list.addAll(classList);
        list.addAll(typeList);
        list.addAll(tauntList);
        list.addAll(performanceList);
        list.addAll(historyList);
        list.addAll(statList);
        list.addAll(ecoStatsList);
        list.addAll(gatheringRates);
        list.addAll(bonusList);
        list.addAll(hiddenBonusList);
        return list;
    }

    public static  TechTreeQuizQuestions getTechTreeQuizQuestions() {
        return techTreeQuizQuestions;
    }

    public static  EcoUpgrades getEcoUpgrades(){
        return ecoUpgrades;
    }

    public static  List<String> getImageNamesList() {
        try {
            String path = "META-INF/resources/images";
            PathMatchingResourcePatternResolver scanner = new PathMatchingResourcePatternResolver();
            File file = scanner.getResource(path).getFile();
            return Arrays.stream(Objects.requireNonNull(file.list())).map(e -> {
                Pattern p = Pattern.compile("(.*).png");
                Matcher m = p.matcher(e);
                if (m.find()) return m.group(1);
                return "";
            }).filter(e -> !e.isEmpty()).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
