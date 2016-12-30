package siap.tri.com.poor.model.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tri on 11/12/16.
 */

public class MLogin implements Parcelable {
  private String kecamatan;
  private String jabatan;
  private String idPegawai;
  private String kodeUnor;
  private String userId;
  private String idUnor;
  private String nama;
  private String nip;
  private String kelurahan;

  public String getKecamatan() {
    return kecamatan;
  }

  public void setKecamatan(String kecamatan) {
    this.kecamatan = kecamatan;
  }

  public String getJabatan() {
    return jabatan;
  }

  public void setJabatan(String jabatan) {
    this.jabatan = jabatan;
  }

  public String getIdPegawai() {
    return idPegawai;
  }

  public void setIdPegawai(String idPegawai) {
    this.idPegawai = idPegawai;
  }

  public String getKodeUnor() {
    return kodeUnor;
  }

  public void setKodeUnor(String kodeUnor) {
    this.kodeUnor = kodeUnor;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getIdUnor() {
    return idUnor;
  }

  public void setIdUnor(String idUnor) {
    this.idUnor = idUnor;
  }

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public String getNip() {
    return nip;
  }

  public void setNip(String nip) {
    this.nip = nip;
  }

  public String getKelurahan() {
    return kelurahan;
  }

  public void setKelurahan(String kelurahan) {
    this.kelurahan = kelurahan;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.kecamatan);
    dest.writeString(this.jabatan);
    dest.writeString(this.idPegawai);
    dest.writeString(this.kodeUnor);
    dest.writeString(this.userId);
    dest.writeString(this.idUnor);
    dest.writeString(this.nama);
    dest.writeString(this.nip);
    dest.writeString(this.kelurahan);
  }

  public MLogin() {
  }

  protected MLogin(Parcel in) {
    this.kecamatan = in.readString();
    this.jabatan = in.readString();
    this.idPegawai = in.readString();
    this.kodeUnor = in.readString();
    this.userId = in.readString();
    this.idUnor = in.readString();
    this.nama = in.readString();
    this.nip = in.readString();
    this.kelurahan = in.readString();
  }

  public static final Parcelable.Creator<MLogin> CREATOR = new Parcelable.Creator<MLogin>() {
    @Override public MLogin createFromParcel(Parcel source) {
      return new MLogin(source);
    }

    @Override public MLogin[] newArray(int size) {
      return new MLogin[size];
    }
  };
}
