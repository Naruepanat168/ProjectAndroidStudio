package com.example.myweatherprediction_2;

import android.widget.RadioButton;
// สร้าง Class แต่ละกลุ่ม เพื่อให้ Main Class เรียกใ้ช้งานง่ายๆ และเป็นสัดเป็นส่วน

public class WindGroup {
    private RadioButton windWeak;
    private RadioButton windStrong;

    // นี่คือ Constructor method
    //ใช้เพื่อสร้างอ็อบเจ็กต์ของคลาสในหน่วยความจำและกำหนดค่าเริ่มต้นให้กับตัวแปรของคลาสและถูกเรียกโดยอัตโนมัติเมื่อสร้างอ็อบเจ็กต์ใหม่ของคลาส
    public WindGroup(RadioButton weak, RadioButton strong) {
        this.windWeak = weak;
        this.windStrong = strong;
    }

    // method ตรวจสอบว่าปุ่มความแรงของลมอ่อนถูกเลือกหรือไม่
    public boolean isWindWeakChecked() {
        return windWeak.isChecked();
    }
    // method ตรวจสอบว่าปุ่ม ความแรงของลมอ่อนพัดแรง ถูกเลือกหรือไม่
    public boolean  isWindStrongChecked() {
        return windStrong.isChecked();
    }
}
