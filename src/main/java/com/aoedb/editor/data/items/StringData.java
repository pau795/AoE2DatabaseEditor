package com.aoedb.editor.data.items;

import java.util.HashMap;

public class StringData {

    HashMap<String,HashMap<String, String>> langMap;

    public StringData(){
        this.langMap = new HashMap<>();
    }

    public String getString(String key, String language){
        return langMap.get(language).get(key);
    }

    public void addLangMap(String lang, HashMap<String, String> map){
        this.langMap.put(lang, map);
    }



}
