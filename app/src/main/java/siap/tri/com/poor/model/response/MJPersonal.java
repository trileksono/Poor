package siap.tri.com.poor.model.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TI04 on 11/10/2016.
 */

public class MJPersonal implements Parcelable {

  private int idJaminanPersonal;
  private String status;
  private int idJaminan;
  private String nik;
  private String nama;

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public int getIdJaminanPersonal() {
    return idJaminanPersonal;
  }

  public void setIdJaminanPersonal(int idJaminanPersonal) {
    this.idJaminanPersonal = idJaminanPersonal;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getIdJaminan() {
    return idJaminan;
  }

  public void setIdJaminan(int idJaminan) {
    this.idJaminan = idJaminan;
  }

  public String getNik() {
    return nik;
  }

  public void setNik(String nik) {
    this.nik = nik;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.idJaminanPersonal);
    dest.writeString(this.status);
    dest.writeInt(this.idJaminan);
    dest.writeString(this.nik);
    dest.writeString(this.nama);
  }

  public MJPersonal() {
  }

  protected MJPersonal(Parcel in) {
    this.idJaminanPersonal = in.readInt();
    this.status = in.readString();
    this.idJaminan = in.readInt();
    this.nik = in.readString();
    this.nama = in.readString();
  }

  public static final Creator<MJPersonal> CREATOR = new Creator<MJPersonal>() {
    @Override public MJPersonal createFromParcel(Parcel source) {
      return new MJPersonal(source);
    }

    @Override public MJPersonal[] newArray(int size) {
      return new MJPersonal[size];
    }
  };
}
