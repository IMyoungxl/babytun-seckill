package com.young.babytunseckill.entity;

import java.util.Date;

public class Evaluate {
    private Long evaluateId;

    private String content;

    private Integer stars;

    private Date createTime;

    private Long goodsId;

    public Long getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(Long evaluateId) {
        this.evaluateId = evaluateId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", evaluateId=").append(evaluateId);
        sb.append(", content=").append(content);
        sb.append(", stars=").append(stars);
        sb.append(", createTime=").append(createTime);
        sb.append(", goodsId=").append(goodsId);
        sb.append("]");
        return sb.toString();
    }
}