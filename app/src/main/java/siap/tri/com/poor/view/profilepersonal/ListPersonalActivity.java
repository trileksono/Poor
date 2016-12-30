package siap.tri.com.poor.view.profilepersonal;

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
import siap.tri.com.poor.R;
import siap.tri.com.poor.base.BaseActivity;
import siap.tri.com.poor.interf.RecycleClick;
import siap.tri.com.poor.interf.RecycleLongClick;
import siap.tri.com.poor.model.response.DTOList;
import siap.tri.com.poor.model.response.MWarga;
import siap.tri.com.poor.util.DialogUtil;
import siap.tri.com.poor.util.ScrollRecycle;
import timber.log.Timber;

/**
 * Created by TI04 on 12/29/2016.
 */

public class ListPersonalActivity extends BaseActivity
    implements ListPersonalView, RecycleLongClick, RecycleClick {

  @Bind(R.id.recycle_keluarga) RecyclerView mRecyclerView;
  @Bind(R.id.toolbar) Toolbar mToolbar;

  private static ProgressDialog sDialog;
  private ListPersonalAdapter mAdapter;
  private ListPersonalPresenter mPresenter;
  private String keyword = "";

  @Override protected int getResourceLayout() {
    return R.layout.activity_list;
  }

  @Override protected void onViewReady(Bundle savedInstance) {
    mPresenter = new ListPersonalPresenter();
    mPresenter.attachView(this);

    setSupportActionBar(mToolbar);
    sDialog = DialogUtil.waitDialog(getContext(), "Mohon tunggu");
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    mPresenter.requestPersonal("", 0);
  }

  private void initRecycle(List<MWarga> data) {
    mAdapter = new ListPersonalAdapter(data);
    mRecyclerView.setHasFixedSize(true);
    LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.setAdapter(mAdapter);

    mAdapter.setOnClick(this);
    mAdapter.setOnLongClick(this);
    mRecyclerView.setOnScrollListener(new ScrollRecycle(mLayoutManager) {
      @Override public void onLoadMore(int page) {
        if (page >= mPresenter.maxPage) {
          return;
        }
        mPresenter.requestPersonal(keyword, page);
      }
    });
  }

  @Override public Context getContext() {
    return this;
  }

  @Override public void onSuccess(DTOList<MWarga> object) {
    if (mAdapter == null) {
      initRecycle(object.getData());
    }
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

  @Override public void onItemClick(View v, int position) {
    Timber.d(position + "");
  }

  @Override public void onItemLongClick(View v, int position) {
    Timber.d(position + "");
  }
}
