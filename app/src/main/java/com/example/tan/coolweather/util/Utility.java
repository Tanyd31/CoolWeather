package com.example.tan.coolweather.util;

import android.text.TextUtils;

import com.example.tan.coolweather.db.City;
import com.example.tan.coolweather.db.County;
import com.example.tan.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {

    public static boolean handleProvinceResponse(String reponse) {
        if (!TextUtils.isEmpty(reponse)) {
            try {
                JSONArray allProvinces = new JSONArray(reponse);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }else{
            return false;
        }
    }

    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try{
                JSONArray allCitys = new JSONArray(response);
                for (int i = 0; i < allCitys.length(); i++) {
                    JSONObject cityObject = allCitys.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }else {
            return false;
        }
    }

    public static boolean handleCountyResponse(String response, int cityId){
        if (!TextUtils.isEmpty(response)) {
            try{
                JSONArray allCountys = new JSONArray(response);
                for (int i = 0; i < allCountys.length(); i++) {
                    JSONObject countyObject = allCountys.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
            }catch (JSONException e) {
                e.printStackTrace();;
            }
            return true;
        }else {
            return false;
        }
    }
}
