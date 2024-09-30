package com.aoedb.editor.data.components;

import java.util.ArrayList;
import java.util.List;

public class TypeValues {

    public static class TypeList{

        private String name;
        private List<TypeElement> typeElements;

        public TypeList(){}

        public TypeList(TypeList other){
            this.name = other.name;
            this.typeElements = new ArrayList<>();
            for(TypeElement te : other.typeElements){
                TypeElement newTe = new TypeElement(te);
                this.typeElements.add(newTe);
            }
        }

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
        private Integer typeID;
        private Double TypeValue;

        public TypeElement(){}

        public TypeElement(TypeElement other){
            this.typeID = other.typeID;
            this.TypeValue = other.TypeValue;
        }

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

    private List<TypeList> typeLists;

    public TypeValues(){}

    public TypeValues(TypeValues other){
        this.typeLists = new ArrayList<>();
        for(TypeList tl : other.typeLists){
            TypeList newTl = new TypeList(tl);
            this.typeLists.add(newTl);
        }
    }

    public List<TypeList> getTypeLists() {
        return typeLists;
    }

    public void setTypeLists(List<TypeList> typeLists) {
        this.typeLists = typeLists;
    }
}
