package siap.tri.com.poor.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by tri on 11/20/16.
 */

public abstract class ScrollRecycle extends RecyclerView.OnScrollListener {

  private int page = 0;
  private int previousTotal = 0;
  private boolean loading = true;
  private LinearLayoutManager mLayoutManager;

  protected ScrollRecycle(LinearLayoutManager layoutManager) {
    this.mLayoutManager = layoutManager;
  }

  @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    int visibleItemCount = recyclerView.getChildCount();
    int totalItemCount = mLayoutManager.getItemCount();
    int firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

    if (loading && totalItemCount > previousTotal) {
      loading = false;
      previousTotal = totalItemCount;
    }

    int visibleThreshold = 3;
    if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
      page++;
      onLoadMore(page);
      loading = true;
    }
  }

  public abstract void onLoadMore(int page);
}
