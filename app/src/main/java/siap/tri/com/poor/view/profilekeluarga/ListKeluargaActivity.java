package siap.tri.com.poor.view.profilekeluarga;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Bind;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import siap.tri.com.poor.R;
import siap.tri.com.poor.base.BaseActivity;
import siap.tri.com.poor.model.Event;
import siap.tri.com.poor.model.response.DTOList;
import siap.tri.com.poor.model.response.MKeluarga;
import siap.tri.com.poor.util.DialogUtil;
import siap.tri.com.poor.util.ScrollRecycle;
import timber.log.Timber;

/**
 * Created by tri on 11/20/16.
 */

public class ListKeluargaActivity extends BaseActivity
    implements ListKeluargaView, ListKeluargaAdapter.OnItemClickListener,
    ListKeluargaAdapter.OnLongItemclickListener {

  @Bind(R.id.recycle_keluarga) RecyclerView mRecyclerView;
  @Bind(R.id.toolbar) Toolbar mToolbar;

  private static ProgressDialog sDialog;
  private ListKeluargaPresenter mPresenter;
  private ListKeluargaAdapter mAdapter;
  private String keyword = "";

  @Override protected int getResourceLayout() {
    return R.layout.activity_list;
  }

  @Override protected void onViewReady(Bundle savedInstance) {
    mPresenter = new ListKeluargaPresenter();
    mPresenter.attachView(this);

    setSupportActionBar(mToolbar);
    sDialog = DialogUtil.waitDialog(getContext(), "Mohon tunggu");
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    mPresenter.requestKeluarga("", 0);
  }

  @Override public Context getContext() {
    return this;
  }

  @Override public void onSuccess(DTOList<MKeluarga> object) {
    if (mAdapter == null) {
      initialRecycle(object.getData());
    }
  }

  private void initialRecycle(List<MKeluarga> data) {
    mAdapter = new ListKeluargaAdapter(data);
    mRecyclerView.setHasFixedSize(true);
    LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.setAdapter(mAdapter);

    mAdapter.setOnItemClickListener(this);
    mAdapter.setOnLongItemclickListener(this);

    mRecyclerView.setOnScrollListener(new ScrollRecycle(mLayoutManager) {
      @Override public void onLoadMore(int page) {
        if (page >= mPresenter.maxPage) {
          return;
        }
        mPresenter.requestKeluarga(keyword, page);
      }
    });
  }

  @Override public void onError(String pesan) {
    showToast(pesan);
  }

  @Override public void showProgress(@Nullable String pesan) {
    sDialog.show();
  }

  @Override public void hideProgress() {
    sDialog.dismiss();
  }

  @Subscribe public void onEvent(Event event) {
    showToast(event.getPesan());
  }

  @Override public void onItemClick(View v, int position) {
    Timber.d(position + "");
  }

  @Override public void onItemLongClick(View v, int position) {
    Timber.d(position + "");
  }
}
