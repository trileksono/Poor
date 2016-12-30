package siap.tri.com.poor.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/**
 * Created by TI04 on 11/10/2016.
 */

public class MWarga implements Parcelable {

  private String noKk;
  private String nik;
  private String tglLahir;
  private String hubungan;
  private String nama;
  private String foto;
  private String alamat;
  private String kecamatan;
  private String kelurahan;
  private String noRt;
  private String noRw;
  private String statusKota;
  private String statusAktif;
  private String userId;
  private List<MJPersonal> jaminan;

  public String getNoKk() {
    return noKk;
  }

  public void setNoKk(String noKk) {
    this.noKk = noKk;
  }

  public String getNik() {
    return nik;
  }

  public void setNik(String nik) {
    this.nik = nik;
  }

  public String getTglLahir() {
    return tglLahir;
  }

  public void setTglLahir(String tglLahir) {
    this.tglLahir = tglLahir;
  }

  public String getHubungan() {
    return hubungan;
  }

  public void setHubungan(String hubungan) {
    this.hubungan = hubungan;
  }

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public String getFoto() {
    return foto;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }

  public String getAlamat() {
    return alamat;
  }

  public void setAlamat(String alamat) {
    this.alamat = alamat;
  }

  public String getKecamatan() {
    return kecamatan;
  }

  public void setKecamatan(String kecamatan) {
    this.kecamatan = kecamatan;
  }

  public String getKelurahan() {
    return kelurahan;
  }

  public void setKelurahan(String kelurahan) {
    this.kelurahan = kelurahan;
  }

  public String getNoRt() {
    return noRt;
  }

  public void setNoRt(String noRt) {
    this.noRt = noRt;
  }

  public String getNoRw() {
    return noRw;
  }

  public void setNoRw(String noRw) {
    this.noRw = noRw;
  }

  public String getStatusKota() {
    return statusKota;
  }

  public void setStatusKota(String statusKota) {
    this.statusKota = statusKota;
  }

  public String getStatusAktif() {
    return statusAktif;
  }

  public void setStatusAktif(String statusAktif) {
    this.statusAktif = statusAktif;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public List<MJPersonal> getJaminan() {
    return jaminan;
  }

  public void setJaminan(List<MJPersonal> jaminan) {
    this.jaminan = jaminan;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.noKk);
    dest.writeString(this.nik);
    dest.writeString(this.tglLahir);
    dest.writeString(this.hubungan);
    dest.writeString(this.nama);
    dest.writeString(this.foto);
    dest.writeString(this.alamat);
    dest.writeString(this.kecamatan);
    dest.writeString(this.kelurahan);
    dest.writeString(this.noRt);
    dest.writeString(this.noRw);
    dest.writeString(this.statusKota);
    dest.writeString(this.statusAktif);
    dest.writeString(this.userId);
    dest.writeTypedList(this.jaminan);
  }

  public MWarga() {
  }

  protected MWarga(Parcel in) {
    this.noKk = in.readString();
    this.nik = in.readString();
    this.tglLahir = in.readString();
    this.hubungan = in.readString();
    this.nama = in.readString();
    this.foto = in.readString();
    this.alamat = in.readString();
    this.kecamatan = in.readString();
    this.kelurahan = in.readString();
    this.noRt = in.readString();
    this.noRw = in.readString();
    this.statusKota = in.readString();
    this.statusAktif = in.readString();
    this.userId = in.readString();
    this.jaminan = in.createTypedArrayList(MJPersonal.CREATOR);
  }

  public static final Parcelable.Creator<MWarga> CREATOR = new Parcelable.Creator<MWarga>() {
    @Override public MWarga createFromParcel(Parcel source) {
      return new MWarga(source);
    }

    @Override public MWarga[] newArray(int size) {
      return new MWarga[size];
    }
  };
}
