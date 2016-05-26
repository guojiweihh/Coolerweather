package com.coolerweather.coolerweather.util;

import android.text.TextUtils;

import com.coolerweather.coolerweather.model.City;
import com.coolerweather.coolerweather.model.CoolerWeatherDB;
import com.coolerweather.coolerweather.model.County;
import com.coolerweather.coolerweather.model.Province;


/**
 * Created by guo on 2016/5/25.
 */
public class Utility {

    /**
     * 解析和处理服务器返回的省级数据
     */
    public synchronized static boolean handleProvincesResponse(CoolerWeatherDB coolerWeatherDB,String response){
        if(!TextUtils.isEmpty(response)){
            String[] allProvinces=response.split(",");
            if(allProvinces!=null&&allProvinces.length>0){
                for (String p:allProvinces) {
                    String[] array=p.split("\\|");
                    Province province=new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    //解析出来的数据储存到Province表
                    coolerWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    public static boolean handleCitiesResponse(CoolerWeatherDB coolerWeatherDB,String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            String[] allCities=response.split(",");
            if(allCities!=null&&allCities.length>0){
                for (String c:allCities) {
                    String[] array=c.split("\\|");
                    City city=new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    coolerWeatherDB.saveCity(city);

                }
                return true;
            }
        }
        return false;
    }
    public static boolean handleCountiesResponse(CoolerWeatherDB coolerWeatherDB,String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            String[] allCounties=response.split(",");
            if(allCounties!=null&&allCounties.length>0){
                for (String c:allCounties) {
                    String[] array=c.split("\\|");
                    County county=new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    coolerWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }


}
