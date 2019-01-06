package com.bay.sparkspringboot.sparkdemo.model;

import java.util.Date;

public class TbIUser {
    private String reqSid;

    private String promotionId;

    private Long userId;

    private String serialNumber;

    private String productId;

    private String provinceCode;

    private String eparchyCode;

    private Long payFee;

    private Date rechargeTime;

    private Date operaTime;

    private String kafkaType;

    private Date requestTime;

    private String retCode;

    private String retMsg;

    private Date logTime;

    @Override
    public String toString() {
        return "TbIUser{" +
                "reqSid='" + reqSid + '\'' +
                ", promotionId='" + promotionId + '\'' +
                ", userId=" + userId +
                ", serialNumber='" + serialNumber + '\'' +
                ", productId='" + productId + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", eparchyCode='" + eparchyCode + '\'' +
                ", payFee=" + payFee +
                ", rechargeTime=" + rechargeTime +
                ", operaTime=" + operaTime +
                ", kafkaType='" + kafkaType + '\'' +
                ", requestTime=" + requestTime +
                ", retCode='" + retCode + '\'' +
                ", retMsg='" + retMsg + '\'' +
                ", logTime=" + logTime +
                '}';
    }

    public String getReqSid() {
        return reqSid;
    }

    public void setReqSid(String reqSid) {
        this.reqSid = reqSid == null ? null : reqSid.trim();
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId == null ? null : promotionId.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    public String getEparchyCode() {
        return eparchyCode;
    }

    public void setEparchyCode(String eparchyCode) {
        this.eparchyCode = eparchyCode == null ? null : eparchyCode.trim();
    }

    public Long getPayFee() {
        return payFee;
    }

    public void setPayFee(Long payFee) {
        this.payFee = payFee;
    }

    public Date getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(Date rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    public Date getOperaTime() {
        return operaTime;
    }

    public void setOperaTime(Date operaTime) {
        this.operaTime = operaTime;
    }

    public String getKafkaType() {
        return kafkaType;
    }

    public void setKafkaType(String kafkaType) {
        this.kafkaType = kafkaType == null ? null : kafkaType.trim();
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode == null ? null : retCode.trim();
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg == null ? null : retMsg.trim();
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
}