package siap.tri.com.poor.model.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tri on 11/20/16.
 */

public class MJKeluarga implements Parcelable {
  private int idJaminanKeluarga;
  private String status;
  private String noKk;
  private int idJaminan;
  private String nama;

  public int getIdJaminanKeluarga() {
    return idJaminanKeluarga;
  }

  public void setIdJaminanKeluarga(int idJaminanKeluarga) {
    this.idJaminanKeluarga = idJaminanKeluarga;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getNoKk() {
    return noKk;
  }

  public void setNoKk(String noKk) {
    this.noKk = noKk;
  }

  public int getIdJaminan() {
    return idJaminan;
  }

  public void setIdJaminan(int idJaminan) {
    this.idJaminan = idJaminan;
  }

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.idJaminanKeluarga);
    dest.writeString(this.status);
    dest.writeString(this.noKk);
    dest.writeInt(this.idJaminan);
    dest.writeString(this.nama);
  }

  public MJKeluarga() {
  }

  protected MJKeluarga(Parcel in) {
    this.idJaminanKeluarga = in.readInt();
    this.status = in.readString();
    this.noKk = in.readString();
    this.idJaminan = in.readInt();
    this.nama = in.readString();
  }

  public static final Parcelable.Creator<MJKeluarga> CREATOR =
      new Parcelable.Creator<MJKeluarga>() {
        @Override public MJKeluarga createFromParcel(Parcel source) {
          return new MJKeluarga(source);
        }

        @Override public MJKeluarga[] newArray(int size) {
          return new MJKeluarga[size];
        }
      };
}
