package siap.tri.com.poor.view.profilepersonal;

import org.greenrobot.eventbus.EventBus;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import siap.tri.com.poor.App;
import siap.tri.com.poor.R;
import siap.tri.com.poor.base.BasePresenter;
import siap.tri.com.poor.model.Event;
import siap.tri.com.poor.model.response.DTOList;
import siap.tri.com.poor.model.response.MWarga;
import siap.tri.com.poor.retrofit.API;

/**
 * Created by TI04 on 12/29/2016.
 */

public class ListPersonalPresenter implements BasePresenter<ListPersonalView> {

  private ListPersonalView mViews;
  private DTOList<MWarga> mData;
  private Subscription mSubscription;
  protected int maxPage;

  @Override public void attachView(ListPersonalView view) {
    this.mViews = view;
  }

  @Override public void detachView() {
    this.mViews = null;
    if (mSubscription != null) mSubscription.unsubscribe();
  }

  protected void requestPersonal(String status, int page) {
    mViews.showProgress(null);
    if (mSubscription != null) mSubscription.unsubscribe();
    final App app = App.getInstance();
    API service = app.getService();

    mSubscription = service.getPersonalPagging(page, status)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(app.defaultSubscribeScheduler())
        .subscribe(new Observer<DTOList<MWarga>>() {
          @Override public void onCompleted() {
            mViews.hideProgress();
            mViews.onSuccess(mData);
          }

          @Override public void onError(Throwable e) {
            mViews.hideProgress();
            if (e instanceof HttpException) {
              if (isHttp404(e)) {
                EventBus.getDefault().post(new Event(app.getString(R.string.error404)));
              } else {
                EventBus.getDefault().post(new Event(app.getString(R.string.http_error)));
              }
            }
          }

          @Override public void onNext(DTOList<MWarga> mWargas) {
            mData = mWargas;
            if (mData.getData().size() < 1) {
              maxPage = 500;
            }
          }
        });
  }

  private static boolean isHttp404(Throwable error) {
    return error instanceof HttpException && ((HttpException) error).code() == 404;
  }
}
