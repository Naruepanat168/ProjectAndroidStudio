package com.example.myweatherprediction_2;

import android.widget.RadioButton;

// สร้าง Class แต่ละกลุ่ม เพื่อให้ Main Class เรียกใ้ช้งานง่ายๆ และเป็นสัดเป็นส่วน

public class CloudsGroup {
    private RadioButton cloudsLow;
    private RadioButton cloudsHigh;

    // นี่คือ Constructor method
    //ใช้เพื่อสร้างอ็อบเจ็กต์ของคลาสในหน่วยความจำและกำหนดค่าเริ่มต้นให้กับตัวแปรของคลาสและถูกเรียกโดยอัตโนมัติเมื่อสร้างอ็อบเจ็กต์ใหม่ของคลาส
    public CloudsGroup(RadioButton low, RadioButton high) {
        this.cloudsLow = low;
        this.cloudsHigh = high;
    }

    public boolean isCloudsLowChecked() {
        return cloudsLow.isChecked(); // จะคืนค่า cloudsLow ถูกเลือกหรือไม่ ถ้า cloudsLow ถูกเลือก isCloudsLowChecked() จะคืนค่า true
    }                                 // และถ้าไม่ถูกเลือกจะคืนค่า false

    public boolean isCloudsHighChecked() {
        return cloudsHigh.isChecked();
    }
}
