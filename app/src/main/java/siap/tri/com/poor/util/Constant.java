package siap.tri.com.poor.util;

import android.Manifest;
import android.app.Activity;
import pub.devrel.easypermissions.AppSettingsDialog;

/**
 * Created by TI04 on 11/10/2016.
 */

public class Constant {

  public static String URL_BASE = "http://opendata.tangerangkota.go.id:8080/";
  public static String IMG_URL = "http://opendata.tangerangkota.go.id:8080/";

  public static long CONNECT_TIME_OUT = 15;
  public static long WRITE_TIME_OUT = 15;
  public static long READ_TIME_OUT = 30;
  public static String IMAGE_DIRECTORY_NAME = "SIAP";

  public static String PREF_SES = "SIAP_PREF";
  public static String PREF_KK = "kk";
  public static String NIP = "nip";
  public static String isLogedIn = "isLogedIn";
  public static String USER_QUEUE = "user_queue";

  public static String[] LOKASI_PERMISSION = {
      Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
      Manifest.permission.ACCESS_NETWORK_STATE
  };

  public static void askPermission(Activity activity, int REQUEST_CODE) {
    new AppSettingsDialog.Builder(activity, "Tanya").setTitle("Title")
        .setPositiveButton("Granted")
        .setNegativeButton("Cancel", null /* click listener */)
        .setRequestCode(REQUEST_CODE)
        .build()
        .show();
  }
}
