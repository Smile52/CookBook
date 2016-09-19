package com.smile.cookbook.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Smile on 2016/9/13.
 */
public class Food {
    private String name;
    private List<ChildBean> childList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChildBean> getChildList() {
        return childList;
    }

    public void setChildList(List<ChildBean> childList) {
        this.childList = childList;
    }

    public static class ChildBean implements Serializable{
        /**
         * ctgId : 0010001007
         * name : 荤菜
         * parentId : 0010001002
         */

        private CategoryInfoBean categoryInfo;

        public CategoryInfoBean getCategoryInfo() {
            return categoryInfo;
        }

        public void setCategoryInfo(CategoryInfoBean categoryInfo) {
            this.categoryInfo = categoryInfo;
        }

        public static class CategoryInfoBean {
            private String ctgId;
            private String name;
            private String parentId;

            public String getCtgId() {
                return ctgId;
            }

            public void setCtgId(String ctgId) {
                this.ctgId = ctgId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            @Override
            public String toString() {
                return "CategoryInfoBean{" +
                        "ctgId='" + ctgId + '\'' +
                        ", name='" + name + '\'' +
                        ", parentId='" + parentId + '\'' +
                        '}';
            }
        }

    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", childList=" + childList +
                '}';
    }
}
