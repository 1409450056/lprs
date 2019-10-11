package com.example.demo.utils;
import com.example.demo.controller.OCRController;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OperateImage {

    public static void main(String[] args) throws IOException {
        int i = 966;
        OCRController ocrController = new OCRController();

        while(i<=1000) {
            File image = new File("E:/workspace-idea/车辆抓拍/"+i+".jpg");
            FileInputStream input = new FileInputStream(image);
            MultipartFile multipartFile = new MockMultipartFile("image",
                    image.getName(), "image/jpg", IOUtils.toByteArray(input));
            String jsonObject = ocrController.getCarInfo2(multipartFile);
            Gson gson = new Gson();
            Map<String,Object> resultmap = new HashMap<>();
            resultmap = gson.fromJson(jsonObject, resultmap.getClass());
            Map<String, Double> car_map = (Map<String, Double>) resultmap.get("vehicle_num");
            double car_number = car_map.get("motorbike") + car_map.get("tricycle")+car_map.get("car")+car_map.get("truck")+car_map.get("bus")+car_map.get("carplate");
            ArrayList locations = (ArrayList) resultmap.get("vehicle_info");
            int j = (int)car_number;
            while(j>=1) {
                try {
                    LinkedTreeMap<String,Object> location = (LinkedTreeMap<String, Object>) locations.get((int)j-1);
                    LinkedTreeMap<String,Double> locationmap = (LinkedTreeMap<String, Double>) location.get("location");
                    Thumbnails.of("E:\\workspace-idea\\车辆抓拍\\"+i+".jpg").sourceRegion(locationmap.get("left").intValue(),locationmap.get("top").intValue(),locationmap.get("width").intValue(),locationmap.get("height").intValue()).size(800,800).toFile("E:\\workspace-idea\\processed\\"+i+"-"+j+".jpg");
                    j--;

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            i++;
        }
    }
}
