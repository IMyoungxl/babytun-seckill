package com.young.babytunseckill.entity;

public class GoodsCover {
    private Integer gcId;

    private Integer goodsId;

    private String gcPicUrl;

    private String gcThumbUrl;

    private Integer gcOrder;

    public Integer getGcId() {
        return gcId;
    }

    public void setGcId(Integer gcId) {
        this.gcId = gcId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGcPicUrl() {
        return gcPicUrl;
    }

    public void setGcPicUrl(String gcPicUrl) {
        this.gcPicUrl = gcPicUrl == null ? null : gcPicUrl.trim();
    }

    public String getGcThumbUrl() {
        return gcThumbUrl;
    }

    public void setGcThumbUrl(String gcThumbUrl) {
        this.gcThumbUrl = gcThumbUrl == null ? null : gcThumbUrl.trim();
    }

    public Integer getGcOrder() {
        return gcOrder;
    }

    public void setGcOrder(Integer gcOrder) {
        this.gcOrder = gcOrder;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", gcId=").append(gcId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", gcPicUrl=").append(gcPicUrl);
        sb.append(", gcThumbUrl=").append(gcThumbUrl);
        sb.append(", gcOrder=").append(gcOrder);
        sb.append("]");
        return sb.toString();
    }
}