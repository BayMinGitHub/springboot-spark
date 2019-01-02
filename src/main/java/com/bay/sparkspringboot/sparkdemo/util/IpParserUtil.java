package com.bay.sparkspringboot.sparkdemo.util;

import com.alibaba.fastjson.JSONObject;
import com.bay.sparkspringboot.sparkdemo.util.ip.IPSeeker;
import lombok.NonNull;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author by BayMin, Date on 2019/1/2.
 */
public class IpParserUtil extends IPSeeker {
    public RegionInfo parserIpByIpSeeker(String ip) {
        RegionInfo info = new RegionInfo();
        // 判断IP是否为空
        if (StringUtils.isEmpty(ip) || StringUtils.isEmpty(ip.trim()))
            return null;
        try {
            //ip不为空，正常解析
            String country = super.getCountry(ip);
            if ("局域网".equals(country)) { // 配置局域网信息
                info.setCountry("中国");
                info.setProvince("北京");
                info.setCity("昌平区");
            } else if (country != null || StringUtils.isNotEmpty(ip.trim())) {
                //查找省的位置
                info.setCountry("中国");
                assert country != null;
                int index = country.indexOf("省");
                if (index > 0) {
                    //设置省份
                    info.setProvince(country.substring(0, index + 1));
                    //判断是否有市
                    int index2 = country.indexOf("市");
                    if (index2 > 0) {
                        //设置市
                        info.setCity(country.substring(index + 1, Math.min(index2 + 1, country.length())));
                    }
                } else {
                    //代码走到这儿，就代表没有省份.就是直辖市、自治区、特别行政区
                    String flag = country.substring(0, 2);
                    switch (flag) {
                        case "内蒙":
                            info.setProvince("内蒙古");
                            country = country.substring(3);
                            index = country.indexOf("市");
                            if (index > 0) {
                                info.setCity(country.substring(0,
                                        Math.min(index + 1, country.length())));
                            }
                            break;
                        case "广西":
                        case "西藏":
                        case "新疆":
                        case "宁夏":
                            info.setProvince(flag);
                            country = country.substring(2);
                            index = country.indexOf("市");
                            if (index > 0) {
                                info.setCity(country.substring(0,
                                        Math.min(index + 1, country.length())));
                            }
                            break;
                        case "北京":
                        case "上海":
                        case "重庆":
                        case "天津":
                            info.setProvince(flag + "市");
                            country = country.substring(3);
                            index = country.indexOf("区");
                            if (index > 0) {
                                char ch = country.charAt(index - 1);
                                if (ch != '小' && ch != '校' && ch != '军') {
                                    info.setCity(country.substring(0,
                                            Math.min(index + 1, country.length())));
                                }
                            }
                            //在直辖市中如果有县
                            index = country.indexOf("县");
                            if (index > 0) {
                                info.setCity(country.substring(0,
                                        Math.min(index + 1, country.length())));
                            }
                            break;
                        case "香港":
                        case "澳门":
                        case "台湾":
                            info.setProvince(flag + "特别行政区");
                            break;
                        default:
                            break;
                    }
                }
            }
            if (info.getProvince().equals(info.getDEFAULT_VALUE()))
                info.setCountry(country);
        } catch (Exception ignored) {

        }
        info.setIp(ip);
        return info;
    }

    public static RegionInfo parserIpByAPI(@NonNull String ip) {
        String url = "http://apicloud.mob.com/ip/query?key=29935e3509fec&ip=" + ip;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        RegionInfo regionInfo = null;
        try {
            httpClient = HttpClients.createDefault();
            List<BasicNameValuePair> postParams = new ArrayList<>();
            postParams.add(new BasicNameValuePair("key", "29935e3509fec"));
            postParams.add(new BasicNameValuePair("ip", ip));
            HttpPost post = new HttpPost(url);
            HttpEntity httpEntity = new UrlEncodedFormEntity(postParams, "UTF-8");
            post.setEntity(httpEntity);
            response = httpClient.execute(post);
            String content = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = JSONObject.parseObject(content);
            Object result = jsonObject.get("result");
            regionInfo = JSONObject.parseObject(result.toString(), RegionInfo.class);
            return regionInfo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return regionInfo;
    }


    public static class RegionInfo {
        private final String DEFAULT_VALUE = "unknown";
        private String ip = DEFAULT_VALUE;
        private String country = DEFAULT_VALUE;
        private String province = DEFAULT_VALUE;
        private String city = DEFAULT_VALUE;
        private String district = DEFAULT_VALUE;

        @Override
        public String toString() {
            return ip + " " + country + " " + province + " " + city + " " + district;
        }

        public String getDEFAULT_VALUE() {
            return DEFAULT_VALUE;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }
    }
}
