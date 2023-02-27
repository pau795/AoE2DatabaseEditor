package com.aoedb.editor.data.items;

import java.util.List;

public class GroupContainer {
    public static class Group{
        boolean alphabeticalOrder;
        private List<GroupCategory> categories;

        public boolean isAlphabeticalOrder() {
            return alphabeticalOrder;
        }

        public void setAlphabeticalOrder(boolean alphabeticalOrder) {
            this.alphabeticalOrder = alphabeticalOrder;
        }

        public List<GroupCategory> getCategories() {
            return categories;
        }

        public void setCategories(List<GroupCategory> categories) {
            this.categories = categories;
        }
    }
    public static class GroupCategory{
        private String name;
        private List<Integer> orderIDs;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Integer> getOrderIDs() {
            return orderIDs;
        }

        public void setOrderIDs(List<Integer> orderIDs) {
            this.orderIDs = orderIDs;
        }
    }

    private List<Group> groupList;

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }
}
