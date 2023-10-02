package com.example.myweatherprediction_3;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView output;
    Button submit, clear, exit;
   RadioButton cloudsLow, cloudsHigh, temperatureNormal, temperatureHot, windStrong, windWeak;
    EditText out;
    //Attibute ของ class
    String ans;
    // สร้าง Object สำหรับแต่ละกลุ่ม
    CloudsGroup cloudsGroup = new CloudsGroup();
    TemperatureGroup temperatureGroup = new TemperatureGroup();
    WindGroup windGroup = new WindGroup();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = findViewById(R.id.textview);
        submit = findViewById(R.id.submit);
        clear = findViewById(R.id.clear);
        exit = findViewById(R.id.exit);
        out = findViewById(R.id.Out);

        cloudsLow = findViewById(R.id.clouds_low);
        cloudsHigh = findViewById(R.id.clouds_high);

        temperatureNormal = findViewById(R.id.temperature_normal);
        temperatureHot = findViewById(R.id.temperature_hot);

        windStrong = findViewById(R.id.wind_strong);
        windWeak = findViewById(R.id.wind_weak);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getClouds();
                getTemperature();
                getWind();
                checkIfAnyRadioButtonSelected();
                process(cloudsGroup,temperatureGroup,windGroup);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit();
            }
        });
    }
    //เรียกใช้
    public void getClouds(){
        if (cloudsLow.isChecked()){
            cloudsGroup.clouds="มีน้อย";
        } else if (cloudsHigh.isChecked()) {
            cloudsGroup.clouds="มีมาก";
        }
        else {
            cloudsGroup.clouds="";
        }

    }

    public void getTemperature(){
        if (temperatureNormal.isChecked()){
            temperatureGroup.temperature="ปกติ";
        } else if (temperatureHot.isChecked()) {
            temperatureGroup.temperature="ร้อนมาก";
        }
        else {
            temperatureGroup.temperature="";
        }

    }

    public void getWind(){
        if (windStrong.isChecked()){
            windGroup.wind="ลมพัดแรง";
        } else if (windWeak.isChecked()) {
            windGroup.wind="ลมพัดอ่อน";
        }
        else {
            windGroup.wind="";
        }

    }
    //ประมวลผล
    public void process(CloudsGroup cloudsGroup,TemperatureGroup temperatureGroup,WindGroup windGroup) {
        try {

            // เริ่มต้นด้วยการเรียกใช้ checkIfAnyRadioButtonSelected() เพื่อตรวจสอบว่ามี RadioButton ถูกเลือกหรือไม่

            // หลังจากตรวจสอบ RadioButton แล้ว ตรวจสอบค่าของ TextView output เพื่อดูว่ามีการแจ้งเตือนหรือไม่
            // หากมี RadioButton ถูกเลือก จะตรวจสอบค่าที่อยู่บน output เพื่อดูว่ามีข้อความแจ้งเตือนอะไรบน output หากมีข้อความแจ้งเตือนใด ๆ

            //หากตัวแปร ans ไม่ว่าง (มีข้อความอยู่) ให้ทำการ return;
            //ถ้า ans มีข้อความอยู่แล้วในตัวแปรนี้ โค้ดที่เหลือในเมทอดที่มีบรรทัดนี้จะไม่ถูกทำงานและโปรแกรมจะออกจากเมทอดนี้ทันที
            if (!ans.isEmpty()) {
            return;
        }

            // หากไม่มีข้อความแจ้งเตือนอยู่บน output จะดำเนินการตรวจสอบเงื่อนไขต่าง ๆ เพื่อกำหนดข้อความที่จะแสดงบน output
            // ตรวจสอบเงื่อนไขต่าง ๆ เพื่อกำหนดข้อความที่จะแสดงบน TextView output
            if (cloudsGroup.clouds=="มีมาก"&&temperatureGroup.temperature=="ร้อนมาก"&&windGroup.wind=="ลมพัดอ่อน"){
                ans="ฝนตก";
            }
            else {
                ans="ฝนไม่ตก";
            }
            output.setText(ans);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // การแจ้งเตือน

    public void checkIfAnyRadioButtonSelected(){
        boolean anyRadioButtonSelected= cloudsLow.isChecked() || cloudsHigh.isChecked()
                ||temperatureNormal.isChecked() ||temperatureHot.isChecked()
                ||windStrong.isChecked()||windWeak.isChecked();

        if (!anyRadioButtonSelected) {
            ans = "กรุณาเลือกให้ครบทุกช่อง";
            // หากมี RadioButton ใด ๆ ถูกเลือก (ค่า anyRadioButtonSelected เป็น true) จะล้างข้อความที่อาจเคยแสดงไว้ก่อนหน้า

        } else {
            ans = ""; // เราจะล้างข้อความที่อาจเคยแสดงไว้ก่อนหน้า
        }

        boolean cloudsSelected = cloudsLow.isChecked()||cloudsHigh.isChecked();
        boolean temperatureSelected = temperatureNormal.isChecked()||temperatureHot.isChecked();
        boolean windSelected = windStrong.isChecked()||windWeak.isChecked();

        if (cloudsSelected && !temperatureSelected && !windSelected) {
            ans = "กรุณาเลือกอุณหภูมิและแรงลมด้วย";
        } else if (!cloudsSelected && temperatureSelected && !windSelected) {
            ans = "กรุณาเลือกเมฆฝนและแรงลมด้วย";
        } else if (!cloudsSelected && !temperatureSelected && windSelected) {
            ans = "กรุณาเลือกเมฆฝนและอุณหภูมิด้วย";
        } else if (!cloudsSelected && temperatureSelected && windSelected) {
            ans = "กรุณาเลือกเมฆฝนด้วย";
        } else if (cloudsSelected && !temperatureSelected && windSelected) {
            ans = "กรุณาเลือกอุณหภูมิด้วย";
        } else if (cloudsSelected && temperatureSelected && !windSelected) {
            ans = "กรุณาเลือกแรงลมด้วย";
        }
        output.setText(ans);
    }

    //เคลียปุ่มทั้งหมด ด้วย id xml
    public void clear() {
        cloudsLow.setChecked(false);
        cloudsHigh.setChecked(false);
        temperatureNormal.setChecked(false);
        temperatureHot.setChecked(false);
        windStrong.setChecked(false);
        windWeak.setChecked(false);
        output.setText("");
    }

    public void exit() {
        if (out.getText().toString().equals("99")) {
            finish(); // ปิดแอปพลิเคชัน
        }
    }}




