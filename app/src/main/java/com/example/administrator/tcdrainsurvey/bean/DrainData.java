package com.example.administrator.tcdrainsurvey.bean;

import android.util.Log;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author HaiRun
 * @time 2019/4/17.19:05
 */

@Entity
public class DrainData {
    /**
     * 主键id
     */
    @Id(autoincrement = true)
    private Long id;

    /**
     *  水体名称
     */
    private String waterName;

    /**
     *  调查地段河段
     */
    private String roadName;

    /**
     * 调查日期
     */
    private String date;

    /**
     * 天气情况
     */
    private String weather;

    /**
     * 调查单位
     */
    private String unit;

    /**
     * 调查人员
     */
    private String surverMan;

    /**
     * 排水口编号
     */
    private String waterNum;

    /**
     * 调查时间
     */
    private String time;

    /**
     * 排水口类型编号
     */
    private String waterType;

    /**
     * 排水口坐标X
     */
    private double X;

    /**
     * 排水口坐标Y
     */
    private double Y;

    /**
     * 排水口断面形式
     */
    private String dsType;

    /**
     * 排水口断面尺寸
     */
    private double dsSize;

    /**
     * 排水口材质
     */
    private String texture;

    /**
     * 排水口末端控制
     */
    private String endControl;

    /**
     * 排水口出流形式
     */
    private String flowType;

    /**
     * 管底高程
     */
    private double h;

    /**
     * 水体常水位
     */
    private String waterLevel;

    /**
     * 旱天排水量
     */
    private String dryDis;

    /**
     * 旱天排水水质
     */
    private String dryQuality;

    /**
     * 雨天排水量
     */
    private String rainyDis;

    /**
     * 雨天排水水质
     */
    private String rainyQuality;

    /**
     * 照片名字
     */
    private String picName;

    /**
     * 备注
     */
    private String remark;

    @Generated(hash = 1698279068)
    public DrainData(Long id, String waterName, String roadName, String date,
            String weather, String unit, String surverMan, String waterNum,
            String time, String waterType, double X, double Y, String dsType,
            double dsSize, String texture, String endControl, String flowType,
            double h, String waterLevel, String dryDis, String dryQuality,
            String rainyDis, String rainyQuality, String picName, String remark) {
        this.id = id;
        this.waterName = waterName;
        this.roadName = roadName;
        this.date = date;
        this.weather = weather;
        this.unit = unit;
        this.surverMan = surverMan;
        this.waterNum = waterNum;
        this.time = time;
        this.waterType = waterType;
        this.X = X;
        this.Y = Y;
        this.dsType = dsType;
        this.dsSize = dsSize;
        this.texture = texture;
        this.endControl = endControl;
        this.flowType = flowType;
        this.h = h;
        this.waterLevel = waterLevel;
        this.dryDis = dryDis;
        this.dryQuality = dryQuality;
        this.rainyDis = rainyDis;
        this.rainyQuality = rainyQuality;
        this.picName = picName;
        this.remark = remark;
    }

    @Generated(hash = 961275311)
    public DrainData() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWaterName() {
        return this.waterName;
    }

    public void setWaterName(String waterName) {
        this.waterName = waterName;
    }

    public String getRoadName() {
        return this.roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeather() {
        return this.weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSurverMan() {
        return this.surverMan;
    }

    public void setSurverMan(String surverMan) {
        this.surverMan = surverMan;
    }

    public String getWaterNum() {
        return this.waterNum;
    }

    public void setWaterNum(String waterNum) {
        this.waterNum = waterNum;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWaterType() {
        return this.waterType;
    }

    public void setWaterType(String waterType) {
        this.waterType = waterType;
    }

    public double getX() {
        return this.X;
    }

    public void setX(double X) {
        this.X = X;
    }

    public double getY() {
        return this.Y;
    }

    public void setY(double Y) {
        this.Y = Y;
    }

    public String getDsType() {
        return this.dsType;
    }

    public void setDsType(String dsType) {
        this.dsType = dsType;
    }

    public double getDsSize() {
        return this.dsSize;
    }

    public void setDsSize(double dsSize) {
        this.dsSize = dsSize;
    }

    public String getTexture() {
        return this.texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public String getEndControl() {
        return this.endControl;
    }

    public void setEndControl(String endControl) {
        this.endControl = endControl;
    }

    public String getFlowType() {
        return this.flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public double getH() {
        return this.h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public String getWaterLevel() {
        return this.waterLevel;
    }

    public void setWaterLevel(String waterLevel) {
        this.waterLevel = waterLevel;
    }

    public String getDryDis() {
        return this.dryDis;
    }

    public void setDryDis(String dryDis) {
        this.dryDis = dryDis;
    }

    public String getDryQuality() {
        return this.dryQuality;
    }

    public void setDryQuality(String dryQuality) {
        this.dryQuality = dryQuality;
    }

    public String getRainyDis() {
        return this.rainyDis;
    }

    public void setRainyDis(String rainyDis) {
        this.rainyDis = rainyDis;
    }

    public String getRainyQuality() {
        return this.rainyQuality;
    }

    public void setRainyQuality(String rainyQuality) {
        this.rainyQuality = rainyQuality;
    }

    public String getPicName() {
        return this.picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}
