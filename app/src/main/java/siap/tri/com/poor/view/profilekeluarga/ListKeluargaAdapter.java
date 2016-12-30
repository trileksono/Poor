package siap.tri.com.poor.view.profilekeluarga;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import java.util.List;
import siap.tri.com.poor.R;
import siap.tri.com.poor.model.response.MKeluarga;

/**
 * Created by tri on 11/20/16.
 */

public class ListKeluargaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private List<MKeluarga> data;
  protected OnItemClickListener onItemClickListener;
  protected OnLongItemclickListener onLongItemclickListener;

  public ListKeluargaAdapter(List<MKeluarga> data) {
    this.data = data;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.content_list_keluarga, parent, false);
    return new ListKeluargaHolder(view);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    MKeluarga keluarga = data.get(position);
    ListKeluargaHolder hold = (ListKeluargaHolder) holder;

    hold.mTxtKk.setText(keluarga.getNoKk());
    hold.mTxtRt.setText(keluarga.getNoRt());
    hold.mTxtRw.setText(keluarga.getNoRw());

    if (keluarga.getStatusAktif() != null) {
      switch (keluarga.getStatusAktif()) {
        case "0":
          hold.mTxtStatusAktif.setText("Tidak Aktif");
          break;
        case "1":
          hold.mTxtStatusAktif.setText("Aktif");
          break;
        case "2":
          hold.mTxtStatusAktif.setText("Pindah rumah");
          break;
        case "3":
          hold.mTxtStatusAktif.setText("Meninggal");
          break;
      }
    }

    if (keluarga.getStatusKota() != null) {
      switch (keluarga.getStatusKota()) {
        case "0":
          hold.mTxtStatus.setText(" Belum cek KK");
          hold.mColorStatus.setBackgroundColor(Color.parseColor("#eeeeee"));
          hold.mLayoutRt.setVisibility(View.GONE);
          break;
        case "1":
          hold.mTxtStatus.setText(" Kota Tangerang");
          hold.mColorStatus.setBackgroundColor(Color.parseColor("#03A9F4"));
          hold.mLayoutRt.setVisibility(View.VISIBLE);
          break;
        case "2":
          hold.mTxtStatus.setText(" Luar Kota Tangerang");
          hold.mColorStatus.setBackgroundColor(Color.parseColor("#FF5252"));
          hold.mLayoutRt.setVisibility(View.VISIBLE);
          break;
      }
    } else {
      hold.mTxtStatus.setText(" Belum cek KK");
      hold.mColorStatus.setBackgroundColor(Color.parseColor("#eeeeee"));
      hold.mLayoutRt.setVisibility(View.GONE);
    }
  }

  @Override public int getItemCount() {
    try {
      return data.size();
    } catch (Exception e) {
      return 0;
    }
  }

  @Override public long getItemId(int position) {
    return position;
  }

  public void add(MKeluarga mData) {
    this.data.add(mData);
    notifyItemInserted(data.size() - 1);
  }

  public void clear() {
    data.clear();
    notifyDataSetChanged();
  }

  public interface OnItemClickListener {
    void onItemClick(View v, int position);
  }

  public interface OnLongItemclickListener {
    void onItemLongClick(View v, int position);
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  public void setOnLongItemclickListener(OnLongItemclickListener onLongItemclickListener) {
    this.onLongItemclickListener = onLongItemclickListener;
  }

  private class ListKeluargaHolder extends RecyclerView.ViewHolder
      implements View.OnClickListener, View.OnLongClickListener {
    @Bind(R.id.txt_kk) TextView mTxtKk;
    @Bind(R.id.txt_status_aktif) TextView mTxtStatusAktif;
    @Bind(R.id.txt_rt) TextView mTxtRt;
    @Bind(R.id.txt_rw) TextView mTxtRw;
    @Bind(R.id.color_status) View mColorStatus;
    @Bind(R.id.txt_status) TextView mTxtStatus;
    @Bind(R.id.layout_rt) LinearLayout mLayoutRt;
    @Bind(R.id.card) CardView mCardView;

    public ListKeluargaHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      mCardView.setOnClickListener(this);
      mCardView.setOnLongClickListener(this);
    }

    @Override public void onClick(View view) {
      onItemClickListener.onItemClick(view, getAdapterPosition());
    }

    @Override public boolean onLongClick(View view) {
      if (onLongItemclickListener != null) {
        onLongItemclickListener.onItemLongClick(view, getAdapterPosition());
        return true;
      }
      return false;
    }
  }
}
