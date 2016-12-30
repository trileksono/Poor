package siap.tri.com.poor.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;

/**
 * Created by tri on 11/18/16.
 */

public abstract class BaseFragment<Data extends Parcelable> extends Fragment {

  protected Context mContext;
  protected Data mData;
  protected LayoutInflater mInflater;

  public BaseFragment() {

  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mContext = getActivity();
    mInflater = LayoutInflater.from(getActivity());
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = mInflater.inflate(getResourceLayout(), container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if (savedInstanceState != null) {
      mData = savedInstanceState.getParcelable("mDatas");
    }
    onViewReady(savedInstanceState);
  }

  public void setData(Data data) {
    this.mData = data;
  }

  public Data getData() {
    return mData;
  }

  protected abstract int getResourceLayout();

  protected abstract void onViewReady(@Nullable Bundle savedInstanceState);

  @Override public void onSaveInstanceState(Bundle outState) {
    outState.putParcelable("mDatas", mData);
    super.onSaveInstanceState(outState);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override public void onDestroy() {
    mData = null;
    super.onDestroy();
  }

  protected void showToast(String message) {
    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
  }
}
