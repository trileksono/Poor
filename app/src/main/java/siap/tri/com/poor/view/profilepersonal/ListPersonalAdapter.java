package siap.tri.com.poor.view.profilepersonal;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.amulyakhare.textdrawable.TextDrawable;
import java.util.List;
import siap.tri.com.poor.R;
import siap.tri.com.poor.interf.RecycleClick;
import siap.tri.com.poor.interf.RecycleLongClick;
import siap.tri.com.poor.model.response.MWarga;

/**
 * Created by TI04 on 12/29/2016.
 */

public class ListPersonalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private List<MWarga> data;
  private RecycleClick onClick;
  private RecycleLongClick onLongClick;

  public ListPersonalAdapter(List<MWarga> data) {
    this.data = data;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.content_list_personal, parent, false);
    return new PersonalHolder(view);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    MWarga warga = data.get(position);
    PersonalHolder hold = (PersonalHolder) holder;
    hold.mTxtKk.setText(warga.getNik());
    hold.mTxtRt.setText(warga.getNoRt());
    hold.mTxtRw.setText(warga.getNoRw());

    String huruf = "";
    if (warga.getNama() != null) {
      hold.mTxtNama.setText(warga.getNama());
      huruf = String.valueOf(warga.getNama().charAt(0));
    }
    if (warga.getStatusAktif() != null) {
      switch (warga.getStatusAktif()) {
        case "0":
          hold.mTxtStatusAktif.setText("Meninggal");
          break;
        case "1":
          hold.mTxtStatusAktif.setText("Aktif");
          break;
        case "2":
          hold.mTxtStatusAktif.setText("Pindah rumah");
          break;
        case "3":
          hold.mTxtStatusAktif.setText("Mampu");
          break;
        case "4":
          hold.mTxtStatusAktif.setText("Tidak Ditemukan");
      }
    } else {
      hold.mTxtStatusAktif.setText("Aktif");
    }
    TextDrawable tex = null;
    if (warga.getStatusKota() != null) {
      switch (warga.getStatusKota()) {
        case "0":
          hold.mTxtStatus.setText(" Belum cek NIK");
          hold.mColorStatus.setBackgroundColor(Color.parseColor("#eeeeee"));
          hold.mLayoutRt.setVisibility(View.GONE);
          tex = TextDrawable.builder().buildRound(huruf, Color.parseColor("#eeeeee"));
          hold.mIconWord.setImageDrawable(tex);
          break;
        case "1":
          hold.mTxtStatus.setText(" Kota Tangerang");
          hold.mColorStatus.setBackgroundColor(Color.parseColor("#03A9F4"));
          hold.mLayoutRt.setVisibility(View.VISIBLE);
          tex = TextDrawable.builder().buildRound(huruf, Color.parseColor("#03A9F4"));
          hold.mIconWord.setImageDrawable(tex);
          break;
        case "2":
          hold.mTxtStatus.setText(" Luar Kota Tangerang");
          hold.mColorStatus.setBackgroundColor(Color.parseColor("#FF5252"));
          hold.mLayoutRt.setVisibility(View.VISIBLE);
          tex = TextDrawable.builder().buildRound(huruf, Color.parseColor("#FF5252"));
          hold.mIconWord.setImageDrawable(tex);
          break;
      }
    } else {
      hold.mTxtStatus.setText(" Belum cek NIK");
      hold.mColorStatus.setBackgroundColor(Color.parseColor("#eeeeee"));
      hold.mLayoutRt.setVisibility(View.GONE);
    }
  }

  @Override public int getItemCount() {
    try{
      return data.size();
    }catch (Exception e){
      return 0;
    }
  }

  @Override public long getItemId(int position) {
    return position;
  }

  public void add(MWarga mData) {
    this.data.add(mData);
    notifyItemInserted(data.size() - 1);
  }

  public void clear() {
    data.clear();
    notifyDataSetChanged();
  }

  public void setOnClick(RecycleClick onClick) {
    this.onClick = onClick;
  }

  public void setOnLongClick(RecycleLongClick onLongClick) {
    this.onLongClick = onLongClick;
  }

  private class PersonalHolder extends RecyclerView.ViewHolder
      implements View.OnClickListener, View.OnLongClickListener {
    @Bind(R.id.txt_kk) TextView mTxtKk;
    @Bind(R.id.txt_rt) TextView mTxtRt;
    @Bind(R.id.layout_rt) LinearLayout mLayoutRt;
    @Bind(R.id.txt_rw) TextView mTxtRw;
    @Bind(R.id.txt_status_aktif) TextView mTxtStatusAktif;
    @Bind(R.id.color_status) View mColorStatus;
    @Bind(R.id.txt_status) TextView mTxtStatus;
    @Bind(R.id.relative_card) RelativeLayout mRelativeCard;
    @Bind(R.id.card) CardView mCard;
    @Bind(R.id.txt_nama) TextView mTxtNama;
    @Bind(R.id.icon_word) ImageView mIconWord;

    public PersonalHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      mRelativeCard.setOnClickListener(this);
      mRelativeCard.setOnLongClickListener(this);
    }

    @Override public void onClick(View v) {
      onClick.onItemClick(v, getAdapterPosition());
    }

    @Override public boolean onLongClick(View v) {
      if (onLongClick != null) {
        onLongClick.onItemLongClick(v, getAdapterPosition());
        return true;
      }
      return false;
    }
  }
}
