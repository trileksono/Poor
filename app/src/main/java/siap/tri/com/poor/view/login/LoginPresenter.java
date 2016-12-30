package siap.tri.com.poor.view.login;

import org.greenrobot.eventbus.EventBus;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import siap.tri.com.poor.App;
import siap.tri.com.poor.R;
import siap.tri.com.poor.base.BasePresenter;
import siap.tri.com.poor.model.Event;
import siap.tri.com.poor.model.LoginBody;
import siap.tri.com.poor.model.response.DTO;
import siap.tri.com.poor.model.response.MLogin;
import siap.tri.com.poor.retrofit.API;

/**
 * Created by tri on 11/12/16.
 */

public class LoginPresenter implements BasePresenter<LoginView> {

  private LoginView mLoginView;
  private Subscription mSubscription;
  protected DTO<MLogin> data;

  @Override public void attachView(LoginView view) {
    this.mLoginView = view;
  }

  @Override public void detachView() {
    this.mLoginView = null;
    if (mSubscription != null) mSubscription.unsubscribe();
  }

  public void doLogin(String nip, String password) {
    mLoginView.showProgress(null);
    if (mSubscription != null) mSubscription.unsubscribe();
    final App app = App.getInstance();
    API service = app.getService();
    mSubscription = service.RLogin(new LoginBody(nip, password), "application/json")
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(app.defaultSubscribeScheduler())
        .subscribe(new Subscriber<DTO<MLogin>>() {
          @Override public void onCompleted() {
            mLoginView.hideProgress();
            mLoginView.onSuccess(data);
          }

          @Override public void onError(Throwable e) {
            mLoginView.hideProgress();
            if (isHttp404(e)) {
              EventBus.getDefault().post(new Event(app.getString(R.string.error404)));
            } else {
              EventBus.getDefault().post(new Event(app.getString(R.string.http_error)));
            }
          }

          @Override public void onNext(DTO<MLogin> mLoginDTO) {
            data = mLoginDTO;
          }
        });
  }

  private static boolean isHttp404(Throwable error) {
    return error instanceof HttpException && ((HttpException) error).code() == 404;
  }
}
