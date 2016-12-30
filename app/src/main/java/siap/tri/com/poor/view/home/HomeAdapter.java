package siap.tri.com.poor.view.home;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import java.util.ArrayList;
import java.util.List;
import siap.tri.com.poor.R;

/**
 * Created by tri on 11/18/16.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  public OnClickMenu mOnClickMenu;
  List<Integer> listColor = new ArrayList<>();
  List<String> lisJudul = new ArrayList<>();
  List<Drawable> listIcon = new ArrayList();
  private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;

  public HomeAdapter() {
    lisJudul.add("Profile Keluarga");
    lisJudul.add("Profile Personal");
    lisJudul.add("Jaminan Keluarga");
    lisJudul.add("Jaminan Personal");
  }

  public interface OnClickMenu {
    public void onClickMenu(int position);
  }

  public void setOnClickMenu(OnClickMenu onClickMenu) {
    mOnClickMenu = onClickMenu;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View mView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.content_home, parent, false);
    return new MenuHolder(mView);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    MenuHolder hold = (MenuHolder) holder;
    hold.mCardView.setBackgroundColor(mColorGenerator.getRandomColor());
    hold.mTitle.setText(lisJudul.get(position));
  }

  @Override public int getItemCount() {
    return lisJudul.size();
  }

  class MenuHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @Bind(R.id.title) TextView mTitle;
    @Bind(R.id.card_menu) CardView mCardView;

    public MenuHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      mCardView.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
      mOnClickMenu.onClickMenu(getAdapterPosition());
    }
  }
}
