package siap.tri.com.poor.model;

/**
 * Created by tri on 11/13/16.
 */

public class LoginBody {

  private String username;
  private String password;

  public LoginBody(String nip, String password) {
    this.username = nip;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
