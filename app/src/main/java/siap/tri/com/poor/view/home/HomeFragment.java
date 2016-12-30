package siap.tri.com.poor.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.Bind;
import java.util.List;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import siap.tri.com.poor.R;
import siap.tri.com.poor.base.BaseFragment;
import siap.tri.com.poor.util.Constant;
import siap.tri.com.poor.view.login.LoginActivity;
import timber.log.Timber;

/**
 * Created by tri on 11/18/16.
 */

public class HomeFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks {

  @Bind(R.id.recycle_menu) RecyclerView mRecycleMenu;

  private HomeAdapter mAdapter;
  public static final int REQ_LOCATION = 69;

  @Override protected int getResourceLayout() {
    return R.layout.fragment_home;
  }

  @Override protected void onViewReady(@Nullable Bundle savedInstanceState) {
    setupAdapter();
  }

  private void setupAdapter() {
    mAdapter = new HomeAdapter();
    mRecycleMenu.setHasFixedSize(true);
    mRecycleMenu.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));
    mRecycleMenu.setAdapter(mAdapter);
    mAdapter.setOnClickMenu(new HomeAdapter.OnClickMenu() {
      @Override public void onClickMenu(int position) {
        switch (position) {
          case 0:
            startActivity(
                //new Intent(getActivity().getApplicationContext(), ListKeluargaActivity.class));
                new Intent(getActivity().getApplicationContext(), LoginActivity.class));
            break;
          case 1:
            startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));
            //startActivity(new Intent(getActivity().getApplicationContext(), ListPersonal.class));
            break;
          case 2:
            startJkeluarga();
            break;
          case 3:
            startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));
            //new Intent(getActivity().getApplicationContext(), JPersonalActivity.class));
            break;
        }
      }
    });
  }

  @AfterPermissionGranted(REQ_LOCATION) private void startJkeluarga() {
    Timber.d("Permission ");
    if (EasyPermissions.hasPermissions(getContext(), Constant.LOKASI_PERMISSION)) {
      Timber.d("Location granted");
      startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));
    } else {
      EasyPermissions.requestPermissions(this, "Please granted", REQ_LOCATION,
          Constant.LOKASI_PERMISSION);
    }
  }

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
  }

  @Override public void onPermissionsGranted(int requestCode, List<String> perms) {
  }

  @Override public void onPermissionsDenied(int requestCode, List<String> perms) {
    if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
      Constant.askPermission(getActivity(), REQ_LOCATION);
    }
  }
}
