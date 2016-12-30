package siap.tri.com.poor.util;

import bolts.Task;
import io.paperdb.Paper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by TI04 on 11/10/2016.
 */

public class PrefUtil {

  public static void setStringPref(final String key, final String value) {
    Task.call(new Callable<Void>() {
      @Override public Void call() throws Exception {
        Paper.book().write(key, value);
        return null;
      }
    }, Task.BACKGROUND_EXECUTOR);
  }

  public static String getStringPref(String key) {
    return Paper.book().read(key);
  }

  public static <T> void setMapPref(final String key, final T value) {
    Task.call(new Callable<Object>() {
      @Override public Object call() throws Exception {
        Paper.book().write(key, value);
        return null;
      }
    }, Task.BACKGROUND_EXECUTOR);
  }

  public static HashSet<String> getHashPref(String key) {
    return Paper.book().read(key, new HashSet<String>());
  }

  public static HashMap getMapPref(String key) {
    return Paper.book().read(key, new HashMap());
  }

  public static void setListPref(final String key, final String value) {
    Task.callInBackground(new Callable<Void>() {
      @Override public Void call() throws Exception {
        LinkedList<String> queue = Paper.book().read(key, new LinkedList<String>());
        queue.addLast(value);
        Paper.book().write(key, queue);
        return null;
      }
    });
  }

  public static List<String> getListPref(String key) {
    return Paper.book().read(key, new LinkedList<String>());
  }

  public static List<String> getListLogin(String key) {
    HashMap<String, String> map = Paper.book().read(key);
    List<String> list = new ArrayList<>();
    if (map != null) {
      for (String user : map.keySet()) {
        list.add(map.get(user));
      }
    }
    return list;
  }
}
