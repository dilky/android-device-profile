package net.dilkyzhart.mydeviceprofile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import net.dilkyzhart.mydeviceprofile.appinfo.AppInfoDetailActivity;
import net.dilkyzhart.mydeviceprofile.appinfo.AppInfoListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnAppList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AppInfoListActivity.class);
                startActivity(intent);
            }
        });
    }
}
