package siap.tri.com.poor.model.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tri on 11/20/16.
 */

public class MFotoKeluarga implements Parcelable {
  private int idFoto;
  private int idFotoKeluarga;
  private String foto;

  public int getIdFoto() {
    return idFoto;
  }

  public void setIdFoto(int idFoto) {
    this.idFoto = idFoto;
  }

  public int getIdFotoKeluarga() {
    return idFotoKeluarga;
  }

  public void setIdFotoKeluarga(int idFotoKeluarga) {
    this.idFotoKeluarga = idFotoKeluarga;
  }

  public String getFoto() {
    return foto;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.idFoto);
    dest.writeInt(this.idFotoKeluarga);
    dest.writeString(this.foto);
  }

  public MFotoKeluarga() {
  }

  protected MFotoKeluarga(Parcel in) {
    this.idFoto = in.readInt();
    this.idFotoKeluarga = in.readInt();
    this.foto = in.readString();
  }

  public static final Parcelable.Creator<MFotoKeluarga> CREATOR =
      new Parcelable.Creator<MFotoKeluarga>() {
        @Override public MFotoKeluarga createFromParcel(Parcel source) {
          return new MFotoKeluarga(source);
        }

        @Override public MFotoKeluarga[] newArray(int size) {
          return new MFotoKeluarga[size];
        }
      };
}
