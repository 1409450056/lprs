package com.example.demo.result;

public class UploadResult {
    private boolean success;
    private String error;
    private String plate;
    private String color;
    private String name;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public UploadResult(boolean success, String error, String plate, String url, String color, String name) {
        this.success = success;
        this.error = error;
        this.plate = plate;
        this.url = url;
        this.color = color;
        this.name = name;
    }

    public UploadResult(boolean success, String error, String url) {
        this.success = success;
        this.error = error;
        this.url = url;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;
}
