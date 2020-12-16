package com.young.babytunseckill.entity;

public class GoodsDetail {
    private Integer gdId;

    private Integer goodsId;

    private String gdPicUrl;

    private Integer gdOrder;

    public Integer getGdId() {
        return gdId;
    }

    public void setGdId(Integer gdId) {
        this.gdId = gdId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGdPicUrl() {
        return gdPicUrl;
    }

    public void setGdPicUrl(String gdPicUrl) {
        this.gdPicUrl = gdPicUrl == null ? null : gdPicUrl.trim();
    }

    public Integer getGdOrder() {
        return gdOrder;
    }

    public void setGdOrder(Integer gdOrder) {
        this.gdOrder = gdOrder;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", gdId=").append(gdId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", gdPicUrl=").append(gdPicUrl);
        sb.append(", gdOrder=").append(gdOrder);
        sb.append("]");
        return sb.toString();
    }
}