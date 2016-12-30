package siap.tri.com.poor.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import butterknife.Bind;
import org.greenrobot.eventbus.Subscribe;
import siap.tri.com.poor.R;
import siap.tri.com.poor.base.BaseActivity;
import siap.tri.com.poor.model.Event;
import siap.tri.com.poor.view.home.HomeFragment;

/**
 * Created by tri on 11/19/16.
 */

public class ContainerActivity extends BaseActivity {
  @Bind(R.id.toolbar) Toolbar mToolbar;
  @Bind(R.id.content_fragment) FrameLayout mContentFragment;

  @Override protected int getResourceLayout() {
    return R.layout.activity_container;
  }

  @Override protected void onViewReady(Bundle savedInstance) {
    getBaseFragmentManager().beginTransaction()
        .replace(R.id.content_fragment, new HomeFragment())
        .commit();
  }


  @Subscribe public void onEvent(Event event) {
    showToast(event.getPesan());
  }

}
