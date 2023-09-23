package com.example.myweatherprediction_2;

import android.widget.RadioButton;

// สร้าง Class แต่ละกลุ่ม เพื่อให้ Main Class เรียกใ้ช้งานง่ายๆ และเป็นสัดเป็นส่วน
public class TemperatureGroup {
    private RadioButton temperatureNormal;
    private RadioButton temperatureHot;

    // นี่คือ Constructor method
    public TemperatureGroup(RadioButton normal, RadioButton hot) {
        this.temperatureNormal = normal;
        this.temperatureHot = hot;
    }

    public boolean isTemperatureNormalChecked() {
        return temperatureNormal.isChecked();
    }

    public boolean isTemperatureHotChecked() {
        return temperatureHot.isChecked();
    }
}
