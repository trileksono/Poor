package siap.tri.com.poor.view.profilekeluarga;

import org.greenrobot.eventbus.EventBus;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import siap.tri.com.poor.App;
import siap.tri.com.poor.R;
import siap.tri.com.poor.base.BasePresenter;
import siap.tri.com.poor.model.Event;
import siap.tri.com.poor.model.response.DTOList;
import siap.tri.com.poor.model.response.MKeluarga;
import siap.tri.com.poor.retrofit.API;

/**
 * Created by tri on 11/20/16.
 */

public class ListKeluargaPresenter implements BasePresenter<ListKeluargaView> {

  private ListKeluargaView mView;
  private DTOList<MKeluarga> mData;
  private Subscription mSubscription;
  protected int maxPage;

  @Override public void attachView(ListKeluargaView view) {
    this.mView = view;
  }

  @Override public void detachView() {
    this.mView = null;
    if (mSubscription != null) mSubscription.unsubscribe();
  }

  protected void requestKeluarga(String status, int page) {
    mView.showProgress(null);
    if (mSubscription != null) mSubscription.unsubscribe();
    final App app = App.getInstance();
    API service = app.getService();

    mSubscription = service.getKeluargaPagging(page, status)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(app.defaultSubscribeScheduler())
        .subscribe(new Subscriber<DTOList<MKeluarga>>() {
          @Override public void onCompleted() {
            mView.hideProgress();
            mView.onSuccess(mData);
          }

          @Override public void onError(Throwable e) {
            mView.hideProgress();
            if (e instanceof HttpException) {
              if (isHttp404(e)) {
                EventBus.getDefault().post(new Event(app.getString(R.string.error404)));
              } else {
                EventBus.getDefault().post(new Event(app.getString(R.string.http_error)));
              }
            }
          }

          @Override public void onNext(DTOList<MKeluarga> mKeluargaDTOList) {
            mData = mKeluargaDTOList;
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
