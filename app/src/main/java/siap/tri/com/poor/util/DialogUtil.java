package siap.tri.com.poor.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import siap.tri.com.poor.R;

/**
 * Created by tri on 11/12/16.
 */

public class DialogUtil {

  public static ProgressDialog waitDialog(Context context, String message) {
    if (message == null) return waitDialog(context);
    ProgressDialog progressDialog = new ProgressDialog(context);
    progressDialog.setMessage(message);
    progressDialog.setIndeterminateDrawable(ContextCompat.getDrawable(context, R.drawable.loading));
    progressDialog.setCancelable(false);
    return progressDialog;
  }

  public static ProgressDialog waitDialog(Context context) {
    ProgressDialog progressDialog = new ProgressDialog(context);
    progressDialog.setIndeterminateDrawable(ContextCompat.getDrawable(context, R.drawable.loading));
    progressDialog.setCancelable(false);
    return progressDialog;
  }
}
