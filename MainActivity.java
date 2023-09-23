package com.example.myweatherprediction_2;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import android.view.Menu;
import android.view.MenuItem;
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

        // สร้าง Object สำหรับแต่ละกลุ่ม
        CloudsGroup cloudsGroup = new CloudsGroup(cloudsLow, cloudsHigh);
        TemperatureGroup temperatureGroup = new TemperatureGroup(temperatureNormal, temperatureHot);
        WindGroup windGroup = new WindGroup(windWeak, windStrong);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                process(cloudsGroup, temperatureGroup, windGroup);
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

    public void process(CloudsGroup cloudsGroup, TemperatureGroup temperatureGroup, WindGroup windGroup) {
        try {
            // เริ่มต้นด้วยการเรียกใช้ checkIfAnyRadioButtonSelected() เพื่อตรวจสอบว่ามี RadioButton ถูกเลือกหรือไม่
            checkIfAnyRadioButtonSelected(cloudsGroup, temperatureGroup, windGroup);

            // หลังจากตรวจสอบ RadioButton แล้ว ตรวจสอบค่าของ TextView output เพื่อดูว่ามีการแจ้งเตือนหรือไม่
            // หากมี RadioButton ถูกเลือก จะตรวจสอบค่าที่อยู่บน output เพื่อดูว่ามีข้อความแจ้งเตือนอะไรบน output หากมีข้อความแจ้งเตือนใด ๆ
            // จะเทียบด้วย equals ถ้ามีข้อความแจ้งเตือนอยู่ เช่น "ลืมติ๊กซักช่อง" หรือ "กรุณาเลือกเมฆด้วย" จะจบการทำงานทันทีด้วยคำสั่ง return

            if (output.getText().toString().equals("กรุณาเลือกให้ครบทุกช่อง") || output.getText().toString().equals("กรุณาเลือกเมฆฝนด้วย")
                    || output.getText().toString().equals("กรุณาเลือกอุณหภูมิด้วย") || output.getText().toString().equals("กรุณาเลือกแรงลมด้วย")
                    || output.getText().toString().equals("กรุณาเลือกอุณหภูมิและแรงลมด้วย") || output.getText().toString().equals("กรุณาเลือกเมฆฝนและแรงลมด้วย")
                    || output.getText().toString().equals("กรุณาเลือกเมฆฝนและอุณหภูมิด้วย")) {
                return;
            }
            // หากไม่มีข้อความแจ้งเตือนอยู่บน output จะดำเนินการตรวจสอบเงื่อนไขต่าง ๆ เพื่อกำหนดข้อความที่จะแสดงบน output
            // ตรวจสอบเงื่อนไขต่าง ๆ เพื่อกำหนดข้อความที่จะแสดงบน TextView output
            if (cloudsGroup.isCloudsHighChecked()) {
                if (temperatureGroup.isTemperatureHotChecked()) {
                    if (windGroup.isWindWeakChecked()) {
                        output.setText("ฝนตก");
                    } else {
                        output.setText("ฝนไม่ตก");
                    }
                } else {
                    output.setText("ฝนไม่ตก");
                }
            } else {
                output.setText("ฝนไม่ตก");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        //การแจ้งเตือน
    public void checkIfAnyRadioButtonSelected(CloudsGroup cloudsGroup, TemperatureGroup temperatureGroup, WindGroup windGroup) {
        boolean anyRadioButtonSelected = cloudsGroup.isCloudsLowChecked() || cloudsGroup.isCloudsHighChecked() ||
                temperatureGroup.isTemperatureNormalChecked() || temperatureGroup.isTemperatureHotChecked() ||
                windGroup.isWindStrongChecked() || windGroup.isWindWeakChecked();

        if (!anyRadioButtonSelected) {
            output.setText("กรุณาเลือกให้ครบทุกช่อง");
            //หากมี RadioButton ใด ๆ ถูกเลือก (ค่า anyRadioButtonSelected เป็น true) จะล้างข้อความที่อาจเคยแสดงไว้ก่อนหน้า
        } else {
            output.setText(""); // เราจะล้างข้อความที่อาจเคยแสดงไว้ก่อนหน้า
        }

        boolean cloudsSelected = cloudsGroup.isCloudsLowChecked() || cloudsGroup.isCloudsHighChecked();
        boolean temperatureSelected = temperatureGroup.isTemperatureNormalChecked() || temperatureGroup.isTemperatureHotChecked();
        boolean windSelected = windGroup.isWindWeakChecked() || windGroup.isWindStrongChecked();

        if (cloudsSelected && !temperatureSelected && !windSelected) {
            output.setText("กรุณาเลือกอุณหภูมิและแรงลมด้วย");
        }else if (!cloudsSelected && temperatureSelected && !windSelected) {
            output.setText("กรุณาเลือกเมฆฝนและแรงลมด้วย");
        } else if (!cloudsSelected && !temperatureSelected && windSelected) {
            output.setText("กรุณาเลือกเมฆฝนและอุณหภูมิด้วย");
        }else if (!cloudsSelected && temperatureSelected && windSelected) {
            output.setText("กรุณาเลือกเมฆฝนด้วย");
        }else if (cloudsSelected && !temperatureSelected && windSelected) {
            output.setText("กรุณาเลือกอุณหภูมิด้วย");
        }else if (cloudsSelected && temperatureSelected && !windSelected) {
            output.setText("กรุณาเลือกแรงลมด้วย");
        }
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
    }

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

    public class WindGroup {
        private RadioButton windWeak;
        private RadioButton windStrong;

        // นี่คือ Constructor method
        //ใช้เพื่อสร้างอ็อบเจ็กต์ของคลาสในหน่วยความจำและกำหนดค่าเริ่มต้นให้กับตัวแปรของคลาสและถูกเรียกโดยอัตโนมัติเมื่อสร้างอ็อบเจ็กต์ใหม่ของคลาส
        public   WindGroup(RadioButton weak, RadioButton strong) {
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
}
