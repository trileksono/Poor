package siap.tri.com.poor.view.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.OnClick;
import java.util.HashMap;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import siap.tri.com.poor.R;
import siap.tri.com.poor.base.BaseActivity;
import siap.tri.com.poor.model.Event;
import siap.tri.com.poor.model.response.DTO;
import siap.tri.com.poor.model.response.MLogin;
import siap.tri.com.poor.util.Constant;
import siap.tri.com.poor.util.DialogUtil;
import siap.tri.com.poor.util.PrefUtil;
import siap.tri.com.poor.view.ContainerActivity;

/**
 * Created by tri on 11/12/16.
 */

public class LoginActivity extends BaseActivity implements LoginView {

  @Bind(R.id.nip_login) AutoCompleteTextView mNipLogin;
  @Bind(R.id.password_login) EditText mPasswordLogin;
  @Bind(R.id.checkbox_password) CheckBox mCheckboxPassword;
  @Bind(R.id.btn_login) Button mBtnLogin;

  private static ProgressDialog sDialog;
  private LoginPresenter mPresenter;

  @Override protected int getResourceLayout() {
    return R.layout.activity_login;
  }

  @Override protected void onViewReady(Bundle savedInstance) {
    getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.ic_login));
    mPresenter = new LoginPresenter();
    mPresenter.attachView(this);
    sDialog = DialogUtil.waitDialog(getContext(), "Mohon tunggu");

    List<String> list = PrefUtil.getListLogin(Constant.USER_QUEUE);
    ArrayAdapter<String> adapterNIP =
        new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
    mNipLogin.setAdapter(adapterNIP);

    mCheckboxPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!isChecked) {
          mPasswordLogin.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
          mPasswordLogin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
      }
    });
  }

  @OnClick(R.id.btn_login) void actionLogin() {
    if (mNipLogin.getText().toString().trim().equals("") || mPasswordLogin.getText()
        .toString()
        .trim()
        .equals("")) {
      showToast("Silahkan isi field sesuai dengan akun anda");
      return;
    }
    mPresenter.doLogin(mNipLogin.getText().toString(), mPasswordLogin.getText().toString());
  }

  @Override public void onSuccess(DTO<MLogin> LoginDTO) {
    if (!LoginDTO.getErrorCode().equals("00")) {
      showToast(LoginDTO.getMessage());
      return;
    }
    PrefUtil.setStringPref(Constant.isLogedIn, "1");

    HashMap<String, String> map = new HashMap<>();
    map.put(LoginDTO.getData().getIdPegawai(), LoginDTO.getData().getNip());

    PrefUtil.setMapPref(Constant.USER_QUEUE, map);
    startActivity(new Intent(LoginActivity.this, ContainerActivity.class));
  }

  @Override public void showProgress(String s) {
    sDialog.show();
  }

  @Override public void hideProgress() {
    if (sDialog.isShowing()) sDialog.dismiss();
  }

  @Override protected void onDestroy() {
    mPresenter.detachView();
    super.onDestroy();
  }

  @Override public void onStart() {
    super.onStart();
    if (!mBus.isRegistered(this)) mBus.register(this);
  }

  @Override public void onStop() {
    mBus.unregister(this);
    super.onStop();
  }

  @Override protected void onResume() {
    super.onResume();
    if (PrefUtil.getStringPref(Constant.isLogedIn) == null) {
      return;
    }
    if (PrefUtil.getStringPref(Constant.isLogedIn).equals("1")) {
      startActivity(new Intent(LoginActivity.this, ContainerActivity.class));
      finish();
    }
  }

  @Subscribe public void onEvent(Event event) {
    showToast(event.getPesan());
  }

  @Override public Context getContext() {
    return this;
  }

  @Override public void onError(String pesan) {}
}
