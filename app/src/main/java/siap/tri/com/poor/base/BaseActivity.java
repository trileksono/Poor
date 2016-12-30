package siap.tri.com.poor.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.Toast;
import butterknife.ButterKnife;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by TI04 on 11/10/2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

  protected Context mContext = this;
  protected LayoutInflater mInflater;
  protected EventBus mBus = EventBus.getDefault();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getResourceLayout());
    ButterKnife.bind(this);
    mInflater = LayoutInflater.from(mContext);
    onViewReady(savedInstanceState);
    mBus = EventBus.getDefault();
    mBus.register(this);
  }

  @Override public void onBackPressed() {
    if (getFragmentManager().getBackStackEntryCount() > 0) {
      getFragmentManager().popBackStack();
    } else {
      super.onBackPressed();
    }
  }

  protected void showToast(String message) {
    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
  }

  public FragmentManager getBaseFragmentManager() {
    return super.getSupportFragmentManager();
  }

  protected abstract int getResourceLayout();

  protected abstract void onViewReady(Bundle savedInstance);
}
