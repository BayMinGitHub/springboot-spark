package com.bay.sparkspringboot.sparkdemo.util;

import com.alibaba.fastjson.JSONObject;
import com.bay.sparkspringboot.sparkdemo.util.ip.ipip.City;
import com.bay.sparkspringboot.sparkdemo.util.ip.ipip.IPFormatException;
import lombok.NonNull;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author by BayMin, Date on 2019/1/2.
 */
@Component
public class IpParserUtil {
    private static String IPIP_FILE_PATH;
    private static String RESULT_LANGUAGE;

    @Value("${ipip.file.path}")
    private String ipIpFilePath;
    @Value("${ipip.result.language}")
    private String resultLanguage;

    @PostConstruct
    public void getIpIpFilePath() {
        IPIP_FILE_PATH = this.ipIpFilePath;
    }

    @PostConstruct
    public void getResultLanguage() {
        RESULT_LANGUAGE = this.resultLanguage;
    }

    public static RegionInfo parserIpByIpIp(@NonNull String ip) {
        RegionInfo regionInfo = new RegionInfo();
        regionInfo.setIp(ip);
        try {
            City city = new City(IPIP_FILE_PATH);
            String[] cns = city.find(ip, RESULT_LANGUAGE);
            int i = 0;
            for (String info : cns) {
                switch (i++) {
                    case 0:
                        regionInfo.setCountry(info);
                        break;
                    case 1:
                        regionInfo.setProvince(info);
                        break;
                    case 2:
                        regionInfo.setCity(info);
                        break;
                    case 3:
                        regionInfo.setDistrict(info);
                        break;
                }
            }
            return regionInfo;
        } catch (IOException e) {
            System.out.println("IP数据库未找到，请确认文件路径！");
        } catch (IPFormatException e) {
            System.out.println("IP地址格式化失败，返回默认值！");
            return regionInfo;
        }
        return regionInfo;
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
