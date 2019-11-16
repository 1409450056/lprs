package com.example.demo.controller;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.ocr.AipOcr;
import com.baidu.aip.util.Base64Util;
import com.example.demo.utils.AuthService;
import com.example.demo.utils.HttpUtil;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Objects;

@CrossOrigin
@Api(value = "车牌识别")
@RestController

public class OCRController {
    public static final String APP_ID = "16965760";
    public static final String API_KEY = "RqpVroiLSbzRjOuzstlPNAxx";
    public static final String SECRET_KEY = "oUsGUGdkqmkW15GKmeKSwKXYNQhS6Xsa";
    String accessToken = null;
    public OCRController() {
        accessToken = AuthService.getAuth("lUdZzexrpdhCMxPR4I2WPEpW"
                ,"c289IroyOLDgEt9YuDzc4Gq9WlKQkqtS");

    }

    @ResponseBody
    @PostMapping("api/ocrimg")
    public String ocrimg(@RequestParam("file") MultipartFile file) throws IOException {
        if (Objects.isNull(file) || file.isEmpty()) {
            return "文件为空，请重新上传";
        }
        String number;
        try {
            HashMap<String, String> options = new HashMap<String, String>();
            options.put("language_type", "CHN_ENG");
            AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
            byte[] bite = file.getBytes();
            JSONObject jsonObject = client.plateLicense(bite, options);
            System.out.println(jsonObject.toString());
            number = jsonObject.toString();
        }catch(Exception e){
            System.err.println(e);
            number = null;
        }
        return number;
    }

    public String getPlate(MultipartFile file) throws IOException {
        String number;
        try {
            HashMap<String, String> options = new HashMap<String, String>();
            options.put("language_type", "CHN_ENG");
            AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
            byte[] bite = file.getBytes();
            JSONObject jsonObject = client.plateLicense(bite, options);
            jsonObject = jsonObject.getJSONObject("words_result");
            number = jsonObject.getString("number");
            System.out.println(number);

        } catch (Exception e) {
            System.err.println(e);
            number = null;
        }
        return number;
    }
    public JSONObject getCarInfo(MultipartFile file) throws IOException {
        JSONObject jsonObject = null;
        try {
            HashMap<String, String> options = new HashMap<String, String>();
            options.put("language_type", "CHN_ENG");
            AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
            byte[] bite = file.getBytes();
            jsonObject = client.carDetect(bite, options);
        } catch (Exception e) {
            System.err.println(e);
            jsonObject = null;
        }
        return jsonObject;
    }

    public String getCarInfo2(MultipartFile file) throws IOException {
        String result;
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/vehicle_detect";
        try {
            byte[] imgData = file.getBytes();
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            String param = "image=" + imgParam;
            result = (HttpUtil.post(url,accessToken,param));
        } catch (Exception e) {
            System.err.println(e);
            result = null;
        }
        return result;
    }
}

