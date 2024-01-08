package com.aoedb.editor.database;

import com.aoedb.editor.data.bonus.Bonus;
import com.aoedb.editor.data.bonus.CivBonus;
import com.aoedb.editor.data.bonus.HiddenBonus;
import com.aoedb.editor.data.components.Upgrades.UpgradeList;
import com.aoedb.editor.data.items.*;
import com.aoedb.editor.data.components.EffectContainer.EffectItem;
import com.aoedb.editor.data.entity.Building;
import com.aoedb.editor.data.entity.Civilization;
import com.aoedb.editor.data.entity.Civilization.UniqueUnit;
import com.aoedb.editor.data.items.GroupContainer.Group;
import com.aoedb.editor.data.items.GroupContainer.GroupCategory;

import com.aoedb.editor.data.components.*;
import com.aoedb.editor.data.entity.Technology;
import com.aoedb.editor.data.entity.Unit;
import com.aoedb.editor.data.simple.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Reader {

    private static String getPath(String path) {
        return Database.BASE_DIR + path +".xml";
    }

    private HashMap<String, String> readLangMap(String language){
        try {
            HashMap<String, String> b = new HashMap<>();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            String path = Database.BASE_DIR + language + "/strings.xml";
            Document doc = docBuilder.parse(path);
            NodeList list = doc.getElementsByTagName("string");
            for (int i = 0; i< list.getLength(); ++i) {
                Element element = (Element) list.item(i);
                String key = element.getAttribute("name");
                String content = element.getTextContent();
                b.put(key, content);
            }
            return b;
        }
        catch (ParserConfigurationException | IOException | SAXException e){
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public StringData readStringData(List<String> languages){
        StringData stringData = new StringData();
        for (String lang : languages){
            HashMap<String, String> langMap = readLangMap(lang);
            stringData.addLangMap(lang, langMap);
        }
        return stringData;
    }

    public List<Editable>  readEditableList(String type) {
        List<Editable> b = new ArrayList<>();
        try{
            String file = Utils.getFileName(type);
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(getPath(file));
            NodeList list = doc.getElementsByTagName("item");
            for (int i = 0; i < list.getLength(); ++i) {
                Editable e =  new TauntElement(i + 1);
                b.add(e);
            }
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return b;
    }

    public List<ImageEditable>  readImageEditableList(String type) {
        List<ImageEditable> b = new ArrayList<>();
        try{
            String file = Utils.getFileName(type);
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(getPath(file));
            NodeList list = doc.getElementsByTagName("item");

            for (int i = 0; i < list.getLength(); ++i) {
                Element element = (Element) list.item(i);
                ImageEditable e;
                switch (type){
                    case Database.CLASS:
                        e = new ClassElement(i + 1);
                        break;
                    case Database.HISTORY:
                        e = new HistoryElement(i + 1);
                        break;
                    case Database.PERFORMANCE:
                        e = new PerformanceElement(i + 1);
                        break;
                    default:
                        e = new TypeElement(i + 1);
                        break;
                }
                e.setImagePath(element.getAttribute("image"));
                b.add(e);
            }
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return b;
    }

    public List<Bonus> readBonuses(String type){
        try{
            String file = Utils.getFileName(type);
            List<Bonus> bonusList = new ArrayList<>();
            String effectFile = Utils.getEffectFileName(file);
            List<EffectContainer> effects = readBonusEffectContainer(effectFile);
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(getPath(file));
            NodeList list = doc.getElementsByTagName("item");
            for (int i = 0; i < list.getLength(); ++i) {
                Bonus bonus = type.equals(Database.BONUS) ? new CivBonus(i + 1) : new HiddenBonus(i + 1);
                bonus.setBonusEffect(effects.get(i));
                bonusList.add(bonus);
            }
            return bonusList;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Stat> readStats(){
        List<Stat> b = new ArrayList<>();
        try{
            String file = Utils.getFileName(Database.STAT);
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(getPath(file));
            NodeList list = doc.getElementsByTagName("item");
            for (int i = 0; i < list.getLength(); ++i) {
                Element element = (Element) list.item(i);
                Stat e =  new Stat(i + 1);
                e.setAddition(Boolean.parseBoolean(element.getAttribute("addition")));
                b.add(e);
            }
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return b;
    }

    public List<EcoStat> readEcoStats(){
        List<EcoStat> b = new ArrayList<>();
        try{
            String file = Utils.getFileName(Database.ECO_STAT);
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(getPath(file));
            NodeList list = doc.getElementsByTagName("item");
            for (int i = 0; i < list.getLength(); ++i) {
                Element element = (Element) list.item(i);
                EcoStat e = new EcoStat(i + 1);
                e.setValue(element.getAttribute("value"));
                b.add(e);
            }
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return b;
    }

    public List<GatheringRates> readGatheringRates(){
        List<GatheringRates> b = new ArrayList<>();
        try{
            String file = Utils.getFileName(Database.GATHERING_RATES);
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(getPath(file));
            NodeList list = doc.getElementsByTagName("item");
            for (int i = 0; i < list.getLength(); ++i) {
                Element element = (Element) list.item(i);
                GatheringRates e = new GatheringRates(i + 1);
                e.setEcoID(Integer.parseInt(element.getAttribute("ecoID")));
                e.setResourceIcon(element.getAttribute("resourceIcon"));
                e.setStatIcon(element.getAttribute("statIcon"));
                b.add(e);
            }
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return b;
    }

    public List<ClassElement> getClassList(List<ImageEditable> list){
        return list.stream().map(e -> (ClassElement) e).collect(Collectors.toList());
    }

    public List<HistoryElement> getHistoryList(List<ImageEditable> list){
        return list.stream().map(e -> (HistoryElement) e).collect(Collectors.toList());
    }

    public List<PerformanceElement> getPerformanceList(List<ImageEditable> list){
        return list.stream().map(e -> (PerformanceElement) e).collect(Collectors.toList());
    }

    public List<TypeElement> getTypeList(List<ImageEditable> list){
        return list.stream().map(e -> (TypeElement) e).collect(Collectors.toList());
    }

    public List<TauntElement> getTauntList(List<Editable> list){
        return list.stream().map(e -> (TauntElement) e).collect(Collectors.toList());
    }

    public List<CivBonus> getCivBonuses(List<Bonus> list){
        return list.stream().map(b -> (CivBonus) b).collect(Collectors.toList());
    }

    public List<HiddenBonus> getHiddenBonuses(List<Bonus> list){
        return list.stream().map(b -> (HiddenBonus) b).collect(Collectors.toList());
    }

    public GroupContainer readGroups(String file){
        try {
            GroupContainer container = new GroupContainer();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(getPath(file));
            NodeList list = doc.getElementsByTagName("group");
            List<Group> groupList = new ArrayList<>();
            for (int i = 0; i< list.getLength(); ++i){
                Element groupElement = (Element) list.item(i);
                Group group = new Group();
                group.setAlphabeticalOrder(Boolean.parseBoolean(groupElement.getAttribute("alphabeticalOrder")));
                NodeList categoryListNode = groupElement.getElementsByTagName("category");
                List<GroupCategory> categoryList = new ArrayList<>();
                for (int j = 0; j < categoryListNode.getLength(); ++j){
                    Element categoryElement = (Element) categoryListNode.item(j);
                    GroupCategory category = new GroupCategory();
                    category.setName(categoryElement.getAttribute("name"));
                    List<Integer> idList = new ArrayList<>();
                    String[] stringIDs = categoryElement.getAttribute("ids").split(" ");
                    for (String s:  stringIDs) idList.add(Integer.parseInt(s));
                    category.setOrderIDs(idList);
                    categoryList.add(category);

                }
                group.setCategories(categoryList);
                groupList.add(group);
            }
            container.setGroupList(groupList);
            return container;

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return new GroupContainer();
    }

    private List<Upgrades> readUpgrades(String file) {
        try {
            List<Upgrades> list = new ArrayList<>();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(getPath(file));
            NodeList nodeList = doc.getElementsByTagName("item");
            for (int z = 0; z < nodeList.getLength(); ++z) {
                Node n = nodeList.item(z);
                Upgrades container = new Upgrades();
                List<UpgradeList> upgradesList = new ArrayList<>();
                NodeList bList = n.getChildNodes();
                for (int i = 0; i < bList.getLength(); ++i) {
                    Node n1 = bList.item(i);
                    if (n1.getNodeType() == Node.ELEMENT_NODE) {
                        Element e = (Element) n1;
                        int headerID = Integer.parseInt(e.getAttribute("id"));
                        String[] ids = e.getAttribute("upgrades").split(" ");
                        List<Integer> idList = new ArrayList<>();
                        for (String u : ids) {
                            int uid = Integer.parseInt(u);
                            idList.add(uid);
                        }
                        UpgradeList upgrades = new UpgradeList(headerID);
                        upgrades.setSubList(idList);
                        upgradesList.add(upgrades);
                    }
                }
                container.setExpandableList(upgradesList);
                list.add(container);
            }

            return list;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Trainable> readTrainable(String file) {
        try {
            List<Trainable> trainableList = new ArrayList<>();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(getPath(file));
            NodeList list = doc.getElementsByTagName("item");
            for (int z = 0; z < list.getLength(); ++z) {
                Node item = list.item(z);
                NodeList applicationList = item.getChildNodes();
                Trainable trainable = new Trainable();
                for (int i = 0; i < applicationList.getLength(); ++i) {
                    Node entryNode = applicationList.item(i);
                    if (entryNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element app = (Element) entryNode;
                        String tag = app.getTagName();
                        String[] idList = app.getTextContent().split(" ");
                        List<Integer> list1 = new ArrayList<>();
                        for (String sid : idList) list1.add(Integer.parseInt(sid));
                        if (tag.equals("units")) trainable.setUnitList(list1);
                        else if (tag.equals("techs")) trainable.setTechList(list1);
                    }
                }
                trainableList.add(trainable);
            }
            return trainableList;
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Availability> readAvailability(String file) {
        try {
            List<Availability> availabilityList = new ArrayList<>();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(getPath(file));
            NodeList list = doc.getElementsByTagName("item");
            for (int z = 0; z < list.getLength(); ++z) {
                Element item = (Element) list.item(z);
                String[] civIDs1 = item.getAttribute("civs").split(" ");
                Availability availability = new Availability();
                List<Integer> list1 = new ArrayList<>();
                for (String s : civIDs1) list1.add(Integer.parseInt(s));
                availability.setAvailableCivs(list1);
                availabilityList.add(availability);
            }
            return availabilityList;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<BonusContainer> readBonusContainer(String file){
        try {
            List<BonusContainer> map = new ArrayList<>();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(getPath(file));
            NodeList list = doc.getElementsByTagName("item");
            for (int z = 0; z < list.getLength(); ++z) {
                Element item = (Element) list.item(z);
                BonusContainer bc = new BonusContainer();
                Element bonusElement = (Element) item.getElementsByTagName("bonusList").item(0);
                Element teamBonusElement = (Element) item.getElementsByTagName("teamBonusList").item(0);
                Element uniqueTechElement = (Element) item.getElementsByTagName("uniqueTechList").item(0);
                Element hiddenBonusElement = (Element) item.getElementsByTagName("hiddenBonusList").item(0);
                List<Integer> bonusIds = new ArrayList<>();
                List<Integer> teamBonusIds = new ArrayList<>();
                List<Integer> uniqueTechsIds = new ArrayList<>();
                List<Integer> hiddenBonusIds = new ArrayList<>();
                if (bonusElement.hasAttribute("ids")) {
                    String[] ids = bonusElement.getAttribute("ids").split(" ");
                    for (String sid : ids) bonusIds.add(Integer.parseInt(sid));
                }
                if (teamBonusElement.hasAttribute("ids")) {
                    String[] ids = teamBonusElement.getAttribute("ids").split(" ");
                    for (String sid : ids) teamBonusIds.add(Integer.parseInt(sid));
                }
                if (uniqueTechElement.hasAttribute("ids")) {
                    String[] ids = uniqueTechElement.getAttribute("ids").split(" ");
                    for (String sid : ids) uniqueTechsIds.add(Integer.parseInt(sid));
                }
                if (hiddenBonusElement.hasAttribute("ids")) {
                    String[] ids = hiddenBonusElement.getAttribute("ids").split(" ");
                    for (String sid : ids) hiddenBonusIds.add(Integer.parseInt(sid));
                }
                bc.setBonusList(bonusIds);
                bc.setTeamBonusList(teamBonusIds);
                bc.setUniqueTechList(uniqueTechsIds);
                bc.setHiddenBonusList(hiddenBonusIds);
                map.add(bc);
            }
            return map;
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<TypeValues> readTypeValues(String file){
        try {
            List<TypeValues> typeValues = new ArrayList<>();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(getPath(file));
            NodeList list = doc.getElementsByTagName("item");
            for (int z = 0; z < list.getLength(); ++z) {
                Node item = list.item(z);
                NodeList entryList = item.getChildNodes();
                TypeValues values = new TypeValues();
                List<TypeValues.TypeList> typeLists1 = new ArrayList<>();
                for (int i = 0; i < entryList.getLength(); ++i) {
                    Node entryNode = entryList.item(i);
                    if (entryNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element entry = (Element) entryNode;
                        TypeValues.TypeList typeList = new TypeValues.TypeList();
                        List<TypeValues.TypeElement> typeElementList = new ArrayList<>();
                        String groupName = entry.getAttribute("nameID");
                        NodeList valList = entry.getChildNodes();
                        for (int j = 0; j < valList.getLength(); ++j) {
                            Node valueNode = valList.item(j);
                            if (valueNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element value = (Element) valList.item(j);
                                int typeID = Integer.parseInt(value.getAttribute("type"));
                                double val = Double.parseDouble(value.getTextContent());
                                TypeValues.TypeElement element = new TypeValues.TypeElement();
                                element.setTypeID(typeID);
                                element.setTypeValue(val);
                                typeElementList.add(element);
                            }
                        }
                        typeList.setName(groupName);
                        typeList.setTypeElements(typeElementList);
                        typeLists1.add(typeList);
                    }

                }
                values.setTypeLists(typeLists1);
                typeValues.add(values);
            }
            return typeValues;
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private List<EffectContainer> readBonusEffectContainer(String file) {
        try {
            List<EffectContainer> map = new ArrayList<>();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(getPath(file));
            NodeList list = doc.getElementsByTagName("item");
            for (int z = 0; z < list.getLength(); ++z) {
                Element item = (Element) list.item(z);
                EffectContainer container = new EffectContainer();
                Element effectsInfo = (Element) item.getElementsByTagName("info").item(0);
                boolean staggered;
                switch (file){
                    case Database.TECH_EFFECT:
                        container.setCivID(0);
                        staggered = false;
                        container.setTeamBonus(false);
                        container.setGlobalFilter(Database.NONE);
                        break;
                    case Database.HIDDEN_BONUS_EFFECT:
                        container.setCivID(0);
                        staggered = true;
                        container.setTeamBonus(false);
                        container.setGlobalFilter(Database.NONE);
                        break;
                    default: //Database.BONUS_EFFECT:
                        container.setCivID(Integer.parseInt(effectsInfo.getAttribute("civ")));
                        staggered = Boolean.parseBoolean(effectsInfo.getAttribute("staggered"));
                        container.setTeamBonus(Boolean.parseBoolean(effectsInfo.getAttribute("teamBonus")));
                        container.setGlobalFilter(effectsInfo.getAttribute("globalFilter"));
                        break;
                }
                Element effectsData = (Element) item.getElementsByTagName("effects").item(0);
                Element statEffect = (Element) effectsData.getElementsByTagName("statEffect").item(0);
                Element costEffect = (Element) effectsData.getElementsByTagName("costEffect").item(0);
                Element ecoEffect = (Element) effectsData.getElementsByTagName("ecoEffect").item(0);
                Element attackValueEffect = (Element) effectsData.getElementsByTagName("attackValueEffect").item(0);
                Element armorValueEffect = (Element) effectsData.getElementsByTagName("armorValueEffect").item(0);
                ArrayList<EffectItem> sEa = processBonusEffect(staggered, statEffect);
                ArrayList<EffectItem> cEa = processBonusEffect(staggered, costEffect);
                ArrayList<EffectItem> eEa = processBonusEffect(staggered, ecoEffect);
                ArrayList<EffectItem> attVEa = processBonusEffect(staggered, attackValueEffect);
                ArrayList<EffectItem> armVEa = processBonusEffect(staggered, armorValueEffect);
                container.setStatsEffects(sEa);
                container.setCostEffects(cEa);
                container.setEcoEffects(eEa);
                container.setAttackEffects(attVEa);
                container.setArmorEffects(armVEa);
                container.setStaggered(staggered);
                map.add(container);
            }
            return map;
        }
        catch (ParserConfigurationException | IOException | SAXException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private ArrayList<EffectItem> processBonusEffect(boolean staggered, Element item){
        ArrayList<EffectItem> eff = new ArrayList<>();
        NodeList effectList = item.getElementsByTagName("effectItem");
        for(int i = 0; i < effectList.getLength(); ++i){
            EffectItem effect = new EffectItem(staggered);
            Element effectItem = (Element) effectList.item(i);
            Element filter = (Element) effectItem.getElementsByTagName("filter").item(0);
            Element operation = (Element) effectItem.getElementsByTagName("operation").item(0);
            if (filter.hasAttributes()){
                effect.setFilter(filter.getAttribute("class"));
                if (filter.hasAttribute("affectsSecondaryProjectile"))
                    effect.setAffectsSecondaryProjectile(Boolean.parseBoolean(filter.getAttribute("affectsSecondaryProjectile")));
                else effect.setAffectsSecondaryProjectile(false);
                if (filter.hasAttribute("requiredTech"))
                    effect.setTechRequirement(Integer.parseInt(filter.getAttribute("requiredTech")));
                else effect.setTechRequirement(0);
                ArrayList<Integer> filterIds = new ArrayList<>();
                String[] fIDs = filter.getAttribute("ids").split(" ");
                for (String s : fIDs){
                    if (s.equals("@")) filterIds.add(-1);
                    else filterIds.add(Integer.parseInt(s));
                }
                effect.setFilterEntitiesIDs(filterIds);
            }
            effect.setStatID(operation.getAttribute("stat"));
            effect.setOperator(operation.getAttribute("operator"));

            effect.setValue(operation.getAttribute("value"));
            eff.add(effect);
        }
        return eff;
    }

    public EcoUpgrades readEcoUpgrades(){
        try {
            EcoUpgrades ecoUpgrades = new EcoUpgrades();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(getPath(Database.ECO_UPGRADES));
            Element upgradeListElement = (Element) doc.getElementsByTagName("item").item(0);
            String[] upgradeIDs = upgradeListElement.getTextContent().split(" ");
            List<Integer> upgrades = new ArrayList<>();
            for (String s: upgradeIDs) upgrades.add(Integer.parseInt(s));
            ecoUpgrades.setUpgrades(upgrades);
            return ecoUpgrades;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return new EcoUpgrades();
    }

    public TechTreeQuizQuestions readTechTreeQuestions(){
        try {
            TechTreeQuizQuestions questions = new TechTreeQuizQuestions();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(getPath(Database.TECH_TREE_QUIZ));
            Element units = (Element) doc.getElementsByTagName("units").item(0);
            Element techs = (Element) doc.getElementsByTagName("techs").item(0);
            String[] unitIDs = units.getAttribute("ids").split(" ");
            String[] techIDs = techs.getAttribute("ids").split(" ");
            List<Integer> unitList = new ArrayList<>();
            List<Integer> techList = new ArrayList<>();
            for (String s: unitIDs) unitList.add(Integer.parseInt(s));
            for (String s: techIDs) techList.add(Integer.parseInt(s));
            questions.setUnits(unitList);
            questions.setTechs(techList);
            return questions;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return new TechTreeQuizQuestions();
    }

    public List<PerformanceContainer> readPerformanceContainer(String file){
        try {
            List<PerformanceContainer> performanceList = new ArrayList<>();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(getPath(file));
            NodeList list = doc.getElementsByTagName("item");
            for (int z = 0; z < list.getLength(); ++z) {
                Element n = (Element) list.item(z);
                PerformanceContainer container = new PerformanceContainer();
                Element strong = (Element) n.getElementsByTagName("strong").item(0);
                ArrayList<Integer> strongList = new ArrayList<>();
                if (strong.hasAttribute("ids")) {
                    String[] strongIDs = strong.getAttribute("ids").split(" ");
                    for (String s : strongIDs) {
                        int id = Integer.parseInt(s);
                        strongList.add(id);
                    }
                }
                Element weak = (Element) n.getElementsByTagName("weak").item(0);
                ArrayList<Integer> weakList = new ArrayList<>();
                if (weak.hasAttribute("ids")) {
                    String[] weakIDs = weak.getAttribute("ids").split(" ");
                    for (String s : weakIDs) {
                        int id = Integer.parseInt(s);
                        weakList.add(id);
                    }
                }
                container.setStrong(strongList);
                container.setWeak(weakList);
                performanceList.add(container);
            }
            return performanceList;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Unit> readUnits(){
        try {
            List<Unit> unitList = new ArrayList<>();

            List<Upgrades> upgrades = readUpgrades(Database.UNIT_UPGRADES);
            List<Availability> availability = readAvailability(Database.UNIT_AVAILABILITY);
            List<BonusContainer> bonusContainer = readBonusContainer(Database.UNIT_BONUS);
            List<TypeValues> attackValues = readTypeValues(Database.UNIT_ATTACK);
            List<TypeValues> armorValues = readTypeValues(Database.UNIT_ARMOR);
            List<PerformanceContainer> performanceContainer = readPerformanceContainer(Database.UNIT_PERFORMANCE);


            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();

            Document docList = docBuilder.parse(getPath(Database.UNIT_LIST));
            Document doc = docBuilder.parse(getPath(Database.UNIT_STATS));
            NodeList list1 = docList.getElementsByTagName("item");
            NodeList list = doc.getElementsByTagName("item");
            for (int i = 0; i < list.getLength(); ++i) {
                Element unitItem = (Element) list.item(i);
                Element unitItemList = (Element) list1.item(i);
                Unit u = new Unit(i + 1);

                //UNIT LIST
                u.setImagePath(unitItemList.getAttribute("image"));

                //UNIT STATS
                Element stats = (Element) unitItem.getElementsByTagName("stats").item(0);
                NamedNodeMap statsList = stats.getAttributes();
                for (int j = 0; j < statsList.getLength(); ++j) {
                    Attr attr = (Attr) statsList.item(j);
                    String statName = attr.getName();
                    String valueStr = attr.getValue();
                    u.setStat(statName, valueStr);
                }

                //UNIT COST
                Element cost = (Element) unitItem.getElementsByTagName("cost").item(0);
                NodeList resourceList = cost.getElementsByTagName("resource");
                for (int j = 0; j < resourceList.getLength(); ++j) {
                    Element res = (Element) resourceList.item(j);
                    String resName = res.getAttribute("name");
                    int resValue = Integer.parseInt(res.getTextContent());
                    u.setCost(resName, resValue);
                }

                //UNIT INFO
                Element baseInfo = (Element) unitItem.getElementsByTagName("baseInfo").item(0);
                Element development = (Element) unitItem.getElementsByTagName("development").item(0);

                int buildingID = Integer.parseInt(baseInfo.getAttribute("trainingBuildingID"));
                int ageID = Integer.parseInt(baseInfo.getAttribute("ageID"));
                int classID = Integer.parseInt(baseInfo.getAttribute("classID"));

                int rTechID = Integer.parseInt(development.getAttribute("requiredTechID"));
                int pUpgradeID = Integer.parseInt(development.getAttribute("previousUpgradeID"));
                int nUpgradeID = Integer.parseInt(development.getAttribute("nextUpgradeID"));

                u.setCreatorID(buildingID);
                u.setAgeID(ageID);
                u.setClassID(classID);

                u.setRequiredTechnologyID(rTechID);
                u.setUpgradedFromID(pUpgradeID);
                u.setNextUpgradeID(nUpgradeID);

                u.setUpgrades(upgrades.get(i));
                u.setAvailability(availability.get(i));
                u.setBonusContainer(bonusContainer.get(i));
                u.setAttackList(attackValues.get(i));
                u.setArmorList(armorValues.get(i));
                u.setPerformance(performanceContainer.get(i));

                unitList.add(u);
            }
            return unitList;
        } catch (ParserConfigurationException | IOException | SAXException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }



    public List<Building> readBuildings(){
        try {
            List<Building> buildingList = new ArrayList<>();

            List<Upgrades> upgrades = readUpgrades(Database.BUILDING_UPGRADES);
            List<Availability> availability = readAvailability(Database.BUILDING_AVAILABILITY);
            List<BonusContainer> bonusContainer = readBonusContainer(Database.BUILDING_BONUS);
            List<TypeValues> attackValues = readTypeValues(Database.BUILDING_ATTACK);
            List<TypeValues> armorValues = readTypeValues(Database.BUILDING_ARMOR);
            List<Trainable> trainable = readTrainable(Database.BUILDING_TRAINABLE);


            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document docList = docBuilder.parse(getPath(Database.BUILDING_LIST));
            Document doc = docBuilder.parse(getPath(Database.BUILDING_STATS));
            NodeList list1 = docList.getElementsByTagName("item");
            NodeList list = doc.getElementsByTagName("item");
            for (int i = 0; i < list.getLength(); ++i) {
                Element buildingItem = (Element) list.item(i);
                Element buildingItemList = (Element) list1.item(i);
                Building b = new Building(i + 1);

                //BUILDING LIST
                b.setImagePath(buildingItemList.getAttribute("image"));

                //BUILDING STATS
                Element stats = (Element) buildingItem.getElementsByTagName("stats").item(0);
                NamedNodeMap statsList = stats.getAttributes();
                for (int j = 0; j < statsList.getLength(); ++j) {
                    Attr attr = (Attr) statsList.item(j);
                    String statName = attr.getName();
                    String valueStr = attr.getValue();
                    b.setStat(statName, valueStr);
                }

                //BUILDING COST
                Element cost = (Element) buildingItem.getElementsByTagName("cost").item(0);
                NodeList resourceList = cost.getElementsByTagName("resource");
                for (int j = 0; j < resourceList.getLength(); ++j) {
                    Element res = (Element) resourceList.item(j);
                    String resName = res.getAttribute("name");
                    int resValue = Integer.parseInt(res.getTextContent());
                    b.setCost(resName, resValue);
                }

                //BUILDING INFO
                Element baseInfo = (Element) buildingItem.getElementsByTagName("baseInfo").item(0);
                Element development = (Element) buildingItem.getElementsByTagName("development").item(0);

                int buildingID = Integer.parseInt(baseInfo.getAttribute("builderUnitID"));
                int ageID = Integer.parseInt(baseInfo.getAttribute("ageID"));
                int classID = Integer.parseInt(baseInfo.getAttribute("classID"));


                int rBuildingID = Integer.parseInt(development.getAttribute("requiredBuildingID"));
                int rTechID = Integer.parseInt(development.getAttribute("requiredTechID"));
                int pUpgradeID = Integer.parseInt(development.getAttribute("previousUpgradeID"));
                int nUpgradeID = Integer.parseInt(development.getAttribute("nextUpgradeID"));

                b.setCreatorID(buildingID);
                b.setAgeID(ageID);
                b.setClassID(classID);

                b.setRequiredBuilding(rBuildingID);
                b.setRequiredTechnologyID(rTechID);
                b.setUpgradedFromID(pUpgradeID);
                b.setNextUpgradeID(nUpgradeID);

                b.setUpgrades(upgrades.get(i));
                b.setAvailability(availability.get(i));
                b.setBonusContainer(bonusContainer.get(i));
                b.setAttackList(attackValues.get(i));
                b.setArmorList(armorValues.get(i));
                b.setTrainable(trainable.get(i));
                buildingList.add(b);
            }
            return buildingList;
        } catch (ParserConfigurationException | IOException | SAXException e){
            e.printStackTrace();
        }
        return new ArrayList<>();

    }

    public List<Technology> readTechnologies(){
        try {
            List<Technology> technologyList = new ArrayList<>();

            List<Upgrades> upgrades = readUpgrades(Database.TECH_UPGRADES);
            List<Availability> availability = readAvailability(Database.TECH_AVAILABILITY);
            List<BonusContainer> bonusContainer = readBonusContainer(Database.TECH_BONUS);
            List<EffectContainer> effectContainers = readBonusEffectContainer(Database.TECH_EFFECT);


            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(getPath(Database.TECH_STATS));
            Document docList = docBuilder.parse(getPath(Database.TECH_LIST));
            NodeList list = doc.getElementsByTagName("item");
            NodeList list1 = docList.getElementsByTagName("item");
            for (int i = 0; i < list.getLength(); ++i) {
                Element techItem = (Element) list.item(i);
                Element techItemList = (Element) list1.item(i);
                Technology t = new Technology(i + 1);

                //TECH LIST
                t.setImagePath(techItemList.getAttribute("image"));

                //TECH STATS
                Element stats = (Element) techItem.getElementsByTagName("stats").item(0);
                NamedNodeMap statsList = stats.getAttributes();
                for (int j = 0; j < statsList.getLength(); ++j) {
                    Attr attr = (Attr) statsList.item(j);
                    String statName = attr.getName();
                    String valueStr = attr.getValue();
                    t.setStat(statName, valueStr);
                }

                //TECH COST
                Element cost = (Element) techItem.getElementsByTagName("cost").item(0);
                NodeList resourceList = cost.getElementsByTagName("resource");
                for (int j = 0; j < resourceList.getLength(); ++j) {
                    Element res = (Element) resourceList.item(j);
                    String resName = res.getAttribute("name");
                    int resValue = Integer.parseInt(res.getTextContent());
                    t.setCost(resName, resValue);
                }

                //TECH INFO
                Element baseInfo = (Element) techItem.getElementsByTagName("baseInfo").item(0);
                Element development = (Element) techItem.getElementsByTagName("development").item(0);

                int buildingID = Integer.parseInt(baseInfo.getAttribute("researchBuildingID"));
                int ageID = Integer.parseInt(baseInfo.getAttribute("ageID"));

                int rTechID = Integer.parseInt(development.getAttribute("requiredTechID"));
                int nUpgradeID = Integer.parseInt(development.getAttribute("nextUpgradeID"));

                t.setCreatorID(buildingID);
                t.setAgeID(ageID);

                t.setRequiredTechnologyID(rTechID);
                t.setNextUpgradeID(nUpgradeID);

                t.setUpgrades(upgrades.get(i));
                t.setAvailability(availability.get(i));
                t.setBonusContainer(bonusContainer.get(i));
                t.setTechEffect(effectContainers.get(i));
                technologyList.add(t);
            }
            return technologyList;
        } catch (ParserConfigurationException | IOException | SAXException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Civilization> readCivilizations(){
        try {
            List<Civilization> civMap =  new ArrayList<>();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(getPath(Database.CIVILIZATION_INFO));
            Document docList = docBuilder.parse(getPath(Database.CIVILIZATION_LIST));
            NodeList list = doc.getElementsByTagName("item");
            NodeList list1 = docList.getElementsByTagName("item");
            for (int i = 0; i < list.getLength(); ++i) {
                Element item = (Element) list.item(i);
                Element itemList = (Element) list1.item(i);
                Civilization civ = new Civilization(i + 1);

                //TECH LIST
                civ.setImagePath(itemList.getAttribute("image"));

                //CIV BONUS
                Element bonus = (Element) item.getElementsByTagName("bonus").item(0);
                String[] bList = bonus.getAttribute("bonusList").split(" ");
                List<Integer> bonusList = new ArrayList<>();
                for (String sid : bList) {
                    int bID = Integer.parseInt(sid);
                    bonusList.add(bID);
                }
                civ.setBonusList(bonusList);

                //TEAM BONUS
                int teamBonus = Integer.parseInt(bonus.getAttribute("teamBonus"));
                civ.setTeamBonus(teamBonus);

                //UNIQUE UNITS
                Element unitSection = (Element) item.getElementsByTagName("uniqueUnits").item(0);
                NodeList units = unitSection.getElementsByTagName(Database.UNIT);
                List<UniqueUnit> uniqueUnitList = new ArrayList<>();
                for (int j = 0; j < units.getLength(); ++j) {
                    Element unit = (Element) units.item(j);
                    UniqueUnit uniqueUnit =  new UniqueUnit();
                    int unitID = Integer.parseInt(unit.getAttribute("unitID"));
                    if (unit.hasAttribute("eliteUnitID")) {
                        int eliteUnitID = Integer.parseInt(unit.getAttribute("eliteUnitID"));
                        uniqueUnit.setEliteID(eliteUnitID);
                    }
                    uniqueUnit.setId(unitID);
                    uniqueUnitList.add(uniqueUnit);
                    civ.setUniqueUnitList(uniqueUnitList);
                }

                //UNIQUE TECHS
                Element techSection = (Element) item.getElementsByTagName("uniqueTechs").item(0);
                int castleUT = Integer.parseInt(techSection.getAttribute("castleTech"));
                int imperialUT = Integer.parseInt(techSection.getAttribute("imperialTech"));
                civ.setCastleUT(castleUT);
                civ.setImperialUT(imperialUT);

                //UNIQUE BUILDINGS
                Element buildingSection = (Element) item.getElementsByTagName("uniqueBuildings").item(0);
                int uniqueBuilding;
                if (buildingSection.hasAttribute("buildingID"))
                    uniqueBuilding = Integer.parseInt(buildingSection.getAttribute("buildingID"));
                else uniqueBuilding = -1;
                civ.setUniqueBuilding(uniqueBuilding);

                civMap.add(civ);
            }
            return civMap;
        }
        catch (ParserConfigurationException | IOException | SAXException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }





}
