package siap.tri.com.poor;

import android.app.Application;
import io.paperdb.Paper;
import rx.Scheduler;
import rx.schedulers.Schedulers;
import siap.tri.com.poor.retrofit.API;
import timber.log.Timber;

/**
 * Created by TI04 on 11/10/2016.
 */

public class App extends Application {

  public static App instance;
  private API mService;
  private Scheduler defaultSubscribeScheduler;

  @Override public void onCreate() {
    super.onCreate();
    instance = this;
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree() {
        @Override protected String createStackElementTag(StackTraceElement element) {
          return super.createStackElementTag(element) + " : " + element.getLineNumber();
        }
      });
    }
    Paper.init(this);
    Timber.i("Launch App");
  }

  public static App getInstance() {
    return instance;
  }

  @Override public void onTerminate() {
    super.onTerminate();
    Timber.i("Terminate App");
  }

  public API getService() {
    if (mService == null) {
      mService = API.Factory.getApi();
    }
    return mService;
  }

  public Scheduler defaultSubscribeScheduler() {
    if (defaultSubscribeScheduler == null) {
      defaultSubscribeScheduler = Schedulers.io();
    }
    return defaultSubscribeScheduler;
  }
}
