package siap.tri.com.poor.model.response;

import java.util.List;

/**
 * Created by tri on 11/20/16.
 */

public class DTOList<T> {

  private String message;
  private boolean success;
  private String errorCode;
  private List<T> data;

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }
}