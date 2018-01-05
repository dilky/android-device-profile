package net.dilkyzhart.mydeviceprofile.appinfo;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import net.dilkyzhart.mydeviceprofile.AppData;
import net.dilkyzhart.mydeviceprofile.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dilky on 2018. 1. 5..
 * 설치된 어플리케이션 목록
 */

public class AppInfoListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    PackageManager packageManager;
    ListView apkList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installed_app_list);

        packageManager = getPackageManager();
        List<PackageInfo> packageList = packageManager
                .getInstalledPackages(PackageManager.GET_PERMISSIONS);

        List<PackageInfo> packageList1 = new ArrayList<>();

        /*To filter out System apps*/
        for(PackageInfo pi : packageList) {
            boolean b = isSystemPackage(pi);
            if(!b) {
                packageList1.add(pi);
            }
        }
        apkList = (ListView) findViewById(R.id.applist);
        apkList.setAdapter(new ApkAdapter(this, packageList1, packageManager));

        apkList.setOnItemClickListener(this);
    }

    /**
     * Return whether the given PackgeInfo represents a system package or not.
     * User-installed packages (Market or otherwise) should not be denoted as
     * system packages.
     *
     * @param pkgInfo
     * @return boolean
     */
    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true : false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long row) {
        PackageInfo packageInfo = (PackageInfo) parent.getItemAtPosition(position);
        AppData appData = (AppData) getApplicationContext();
        appData.setPackageInfo(packageInfo);

        Intent appInfo = new Intent(getApplicationContext(), AppInfoDetailActivity.class);
        startActivity(appInfo);
    }

}
