package com.test.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private LocationListener locationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 안드로이드의 위치를 관리하는 객체가 LocationManager다.
        // 따라서 안드로이드 시스템에, 로케이션 서비스를 이앱이 사용하겠다고,
        // 로케이션 서비스를 요청하여, 로케이션매니져 변수에 저장해 줘야 한다.
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                // 안드로이드가, 폰의 위치 바뀔때 마다, 이 메소드를 호출해줍니다.
                // 폰의 위치가 바뀔때마다 해주고 싶은일을 여기에 작성.
                Log.i("AAA", location.toString());
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // 유저한테, 이 앱은 위치기반 권한이 있어야 한다고 알려야 한다.
            // 유저가 권한 설정을 하고 나면, 처리해야 할 코드를 작성하기 위해서,
            // requestCode 값을 설정한다.
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                5000,   // 밀리세컨드,  1000 : 1초
                0,   // 미터   10m
                locationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 0){
               if(ActivityCompat.checkSelfPermission(MainActivity.this,
                       Manifest.permission.ACCESS_FINE_LOCATION) !=
                       PackageManager.PERMISSION_GRANTED &&
                       ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED){

                   ActivityCompat.requestPermissions(MainActivity.this,
                           new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                   Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
                   return;
               }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    5000,   // 밀리세컨드,  1000 : 1초
                    0,   // 미터   10m
                    locationListener);
        }
    }
}





