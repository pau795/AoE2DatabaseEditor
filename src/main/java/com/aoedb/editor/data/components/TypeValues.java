package com.aoedb.editor.data.components;

import java.util.List;

public class TypeValues {

    public static class TypeList{

        String name;
        List<TypeElement> typeElements;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<TypeElement> getTypeElements() {
            return typeElements;
        }

        public void setTypeElements(List<TypeElement> typeElements) {
            this.typeElements = typeElements;
        }
    }

    public static class TypeElement{
        Integer typeID;
        Double TypeValue;

        public int getTypeID() {
            return typeID;
        }

        public void setTypeID(int typeID) {
            this.typeID = typeID;
        }

        public double getTypeValue() {
            return TypeValue;
        }

        public void setTypeValue(double typeValue) {
            TypeValue = typeValue;
        }
    }

    List<TypeList> typeLists;

    public List<TypeList> getTypeLists() {
        return typeLists;
    }

    public void setTypeLists(List<TypeList> typeLists) {
        this.typeLists = typeLists;
    }
}
