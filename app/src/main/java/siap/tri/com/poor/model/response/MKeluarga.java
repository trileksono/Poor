package siap.tri.com.poor.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/**
 * Created by tri on 11/20/16.
 */

public class MKeluarga implements Parcelable {
  private String noKk;
  private String kecamatan;
  private String lon;
  private String kelurahan;
  private String lat;
  private String alamat;
  private String noRt;
  private String noRw;
  private String statusKota;
  private String userId;
  private String statusAktif;
  private List<MWarga> anggotaKeluarga;
  private List<MFotoKeluarga> fotoKeluarga;
  private List<MJaminan> listJaminan;
  private List<MJKeluarga> jKeluarga;
  private boolean kriteriaKeluarga;

  public String getNoKk() {
    return noKk;
  }

  public void setNoKk(String noKk) {
    this.noKk = noKk;
  }

  public String getKecamatan() {
    return kecamatan;
  }

  public void setKecamatan(String kecamatan) {
    this.kecamatan = kecamatan;
  }

  public String getLon() {
    return lon;
  }

  public void setLon(String lon) {
    this.lon = lon;
  }

  public String getKelurahan() {
    return kelurahan;
  }

  public void setKelurahan(String kelurahan) {
    this.kelurahan = kelurahan;
  }

  public String getLat() {
    return lat;
  }

  public void setLat(String lat) {
    this.lat = lat;
  }

  public String getAlamat() {
    return alamat;
  }

  public void setAlamat(String alamat) {
    this.alamat = alamat;
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

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getStatusAktif() {
    return statusAktif;
  }

  public void setStatusAktif(String statusAktif) {
    this.statusAktif = statusAktif;
  }

  public List<MWarga> getAnggotaKeluarga() {
    return anggotaKeluarga;
  }

  public void setAnggotaKeluarga(List<MWarga> anggotaKeluarga) {
    this.anggotaKeluarga = anggotaKeluarga;
  }

  public List<MFotoKeluarga> getFotoKeluarga() {
    return fotoKeluarga;
  }

  public void setFotoKeluarga(List<MFotoKeluarga> fotoKeluarga) {
    this.fotoKeluarga = fotoKeluarga;
  }

  public List<MJaminan> getListJaminan() {
    return listJaminan;
  }

  public void setListJaminan(List<MJaminan> listJaminan) {
    this.listJaminan = listJaminan;
  }

  public List<MJKeluarga> getjKeluarga() {
    return jKeluarga;
  }

  public void setjKeluarga(List<MJKeluarga> jKeluarga) {
    this.jKeluarga = jKeluarga;
  }

  public boolean isKriteriaKeluarga() {
    return kriteriaKeluarga;
  }

  public void setKriteriaKeluarga(boolean kriteriaKeluarga) {
    this.kriteriaKeluarga = kriteriaKeluarga;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.noKk);
    dest.writeString(this.kecamatan);
    dest.writeString(this.lon);
    dest.writeString(this.kelurahan);
    dest.writeString(this.lat);
    dest.writeString(this.alamat);
    dest.writeString(this.noRt);
    dest.writeString(this.noRw);
    dest.writeString(this.statusKota);
    dest.writeString(this.userId);
    dest.writeString(this.statusAktif);
    dest.writeTypedList(this.anggotaKeluarga);
    dest.writeTypedList(this.fotoKeluarga);
    dest.writeTypedList(this.listJaminan);
    dest.writeTypedList(this.jKeluarga);
    dest.writeByte(this.kriteriaKeluarga ? (byte) 1 : (byte) 0);
  }

  public MKeluarga() {
  }

  protected MKeluarga(Parcel in) {
    this.noKk = in.readString();
    this.kecamatan = in.readString();
    this.lon = in.readString();
    this.kelurahan = in.readString();
    this.lat = in.readString();
    this.alamat = in.readString();
    this.noRt = in.readString();
    this.noRw = in.readString();
    this.statusKota = in.readString();
    this.userId = in.readString();
    this.statusAktif = in.readString();
    this.anggotaKeluarga = in.createTypedArrayList(MWarga.CREATOR);
    this.fotoKeluarga = in.createTypedArrayList(MFotoKeluarga.CREATOR);
    this.listJaminan = in.createTypedArrayList(MJaminan.CREATOR);
    this.jKeluarga = in.createTypedArrayList(MJKeluarga.CREATOR);
    this.kriteriaKeluarga = in.readByte() != 0;
  }

  public static final Parcelable.Creator<MKeluarga> CREATOR = new Parcelable.Creator<MKeluarga>() {
    @Override public MKeluarga createFromParcel(Parcel source) {
      return new MKeluarga(source);
    }

    @Override public MKeluarga[] newArray(int size) {
      return new MKeluarga[size];
    }
  };
}
