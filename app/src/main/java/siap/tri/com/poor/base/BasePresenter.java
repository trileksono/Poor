package siap.tri.com.poor.base;

/**
 * Created by TI04 on 11/10/2016.
 */

public interface BasePresenter<V> {

  void attachView(V view);

  void detachView();
}
