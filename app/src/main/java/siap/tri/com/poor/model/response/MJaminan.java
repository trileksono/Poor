package siap.tri.com.poor.model.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tri on 11/20/16.
 */

public class MJaminan implements Parcelable {
  private int tipeJaminan;
  private int total;
  private String jaminan;
  private int idJaminan;

  public int getTipeJaminan() {
    return tipeJaminan;
  }

  public void setTipeJaminan(int tipeJaminan) {
    this.tipeJaminan = tipeJaminan;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public String getJaminan() {
    return jaminan;
  }

  public void setJaminan(String jaminan) {
    this.jaminan = jaminan;
  }

  public int getIdJaminan() {
    return idJaminan;
  }

  public void setIdJaminan(int idJaminan) {
    this.idJaminan = idJaminan;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.tipeJaminan);
    dest.writeInt(this.total);
    dest.writeString(this.jaminan);
    dest.writeInt(this.idJaminan);
  }

  public MJaminan() {
  }

  protected MJaminan(Parcel in) {
    this.tipeJaminan = in.readInt();
    this.total = in.readInt();
    this.jaminan = in.readString();
    this.idJaminan = in.readInt();
  }

  public static final Parcelable.Creator<MJaminan> CREATOR = new Parcelable.Creator<MJaminan>() {
    @Override public MJaminan createFromParcel(Parcel source) {
      return new MJaminan(source);
    }

    @Override public MJaminan[] newArray(int size) {
      return new MJaminan[size];
    }
  };
}
