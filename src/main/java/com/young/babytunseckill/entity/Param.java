package com.young.babytunseckill.entity;

public class Param {
    private Integer gpId;

    private String gpParamName;

    private String gpParamValue;

    private Integer goodsId;

    private Integer gpOrder;

    public Integer getGpId() {
        return gpId;
    }

    public void setGpId(Integer gpId) {
        this.gpId = gpId;
    }

    public String getGpParamName() {
        return gpParamName;
    }

    public void setGpParamName(String gpParamName) {
        this.gpParamName = gpParamName == null ? null : gpParamName.trim();
    }

    public String getGpParamValue() {
        return gpParamValue;
    }

    public void setGpParamValue(String gpParamValue) {
        this.gpParamValue = gpParamValue == null ? null : gpParamValue.trim();
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGpOrder() {
        return gpOrder;
    }

    public void setGpOrder(Integer gpOrder) {
        this.gpOrder = gpOrder;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", gpId=").append(gpId);
        sb.append(", gpParamName=").append(gpParamName);
        sb.append(", gpParamValue=").append(gpParamValue);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", gpOrder=").append(gpOrder);
        sb.append("]");
        return sb.toString();
    }
}