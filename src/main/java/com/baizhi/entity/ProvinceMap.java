package com.baizhi.entity;

import java.util.Map;

public class ProvinceMap {
    private String province;
    private Integer count;

    @Override
    public String toString() {
        return "ProvinceMap{" +
                "province='" + province + '\'' +
                ", count=" + count +
                '}';
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ProvinceMap(String province, Integer count) {

        this.province = province;
        this.count = count;
    }

    public ProvinceMap() {

    }
}
