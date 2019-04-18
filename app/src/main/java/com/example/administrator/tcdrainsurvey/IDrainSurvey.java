package com.example.administrator.tcdrainsurvey;

/**
 * @author HaiRun
 * @time 2019/4/18.8:47
 */

public interface IDrainSurvey {

    /**
     * 获取水体名称
     * @return
     */
    String getWaterName();

    /**
     * 获取地段 、 河段名称
     * @return
     */
    String getRoadName();

    /**
     * 获取调查日期
     * @return
     */
    String getDate();

    /**
     * 获取天气情况
     * @return
     */
    String getWeather();

    /**
     * 获取检查单位
     * @return
     */
    String getUnit();

    /**
     * 获取调查人员
     * @return
     */
    String getSurveyMan();

    /**
     * 获取排水口编号
     * @return
     */
    String getWaterNum();

    /**
     * 获取调查时间
     * @return
     */
    String getTime();

    /**
     * 获取WaterType
     * @return
     */
    String getWaterType();

    /**
     * 获取坐标X
     * @return
     */
    double getX();

    /**
     * 获取坐标Y
     * @return
     */
    double getY();

    /**
     * 获取断面形式
     * @return
     */
    String getDsType();

    /**
     * 获取断面尺寸
     * @return
     */
    double getDsSize();

    /**
     * 获取材质
     * @return
     */
    String getTexture();

    /**
     * 末端控制
     * @return
     */
    String getEndControl();


    /**
     * 出流形式
     * @return
     */
    String getFlowType();

    /**
     * 官底高程
     * @return
     */
    double getH();

    /**
     * 水体常水位
     * @return
     */
    String getWaterLevel();

    /**
     * 旱天排水量
     * @return
     */
    String getDryDis();

    /**
     * 旱天排水水质
     * @return
     */
    String getDryQuality();

    /**
     * 雨天排水量
     * @return
     */
    String getDrainDis();

    /**
     * 雨天排水水质
     * @return
     */
    String getDrainQuality();

    /**
     * 照片名字
     * @return
     */
    String getPicName();

    /**
     * 备注
     * @return
     */
    String getRemark();



}
