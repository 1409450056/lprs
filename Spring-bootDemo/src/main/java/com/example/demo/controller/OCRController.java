package com.example.demo.controller;

import com.baidu.aip.ocr.AipOcr;
import io.swagger.annotations.Api;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

@CrossOrigin
@Api(value = "车牌识别")
@RestController
public class OCRController {
    public static final String APP_ID = "16965760";
    public static final String API_KEY = "RqpVroiLSbzRjOuzstlPNAxx";
    public static final String SECRET_KEY = "oUsGUGdkqmkW15GKmeKSwKXYNQhS6Xsa";

    @ResponseBody
    @PostMapping("api/ocrimg")
    public String ocrimg(@RequestParam("file") MultipartFile file) throws IOException {
        if (Objects.isNull(file) || file.isEmpty()) {
            return "文件为空，请重新上传";
        }
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        byte[] bite = file.getBytes();
        JSONObject jsonObject = client.plateLicense(bite, options);
        System.out.println(jsonObject.toString());
        return jsonObject.toString();
    }

    public String getPlate(MultipartFile file) throws IOException {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        byte[] bite = file.getBytes();
        JSONObject jsonObject = client.plateLicense(bite, options);
        String plate;
        jsonObject = jsonObject.getJSONObject("words_result");
        plate = jsonObject.getString("number");
        System.out.println(plate);
        return plate;
    }
}
