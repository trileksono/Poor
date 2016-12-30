package siap.tri.com.poor.view;

import android.content.Context;
import android.support.annotation.Nullable;

/**
 * Created by TI04 on 11/10/2016.
 */

public interface MvpView<T> {

  Context getContext();

  void onSuccess(T object);

  void onError(String pesan);

  void showProgress(@Nullable String pesan);

  void hideProgress();
}
