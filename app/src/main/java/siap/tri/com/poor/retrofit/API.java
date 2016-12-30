package siap.tri.com.poor.retrofit;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import siap.tri.com.poor.App;
import siap.tri.com.poor.BuildConfig;
import siap.tri.com.poor.model.LoginBody;
import siap.tri.com.poor.model.response.DTO;
import siap.tri.com.poor.model.response.DTOList;
import siap.tri.com.poor.model.response.MKeluarga;
import siap.tri.com.poor.model.response.MLogin;
import siap.tri.com.poor.model.response.MWarga;
import siap.tri.com.poor.util.Constant;
import siap.tri.com.poor.util.PrefUtil;
import timber.log.Timber;

/**
 * Created by TI04 on 11/10/2016.
 */

public interface API {

  @POST("api/login") Observable<DTO<MLogin>> RLogin(@Body LoginBody body,
      @Header("Content-Type") String contentType);

  //
  //@GET("api/findKeluarga?")
  //Call<DTO<CariKeluarga>> findKeluarga(@Query("nik") String nik);
  //
  //@GET("api/jaminan?")
  //Call<DTOList<ListJaminan>> getJaminan(@Query("tipe") String tipe);
  //
  //@GET("api/cariPenduduk")
  //Call<DTO<MWarga>> cariPenduduk(@Query("nik") String nik);
  //
  //@GET("api/findPenduduk")
  //Call<DTO<MWarga>> findPenduduk(@Query("nik") String nik);
  //
  //@POST("api/jPersonal")
  //Call<DTO> simpanPenduduk(@Body RequestBody requestBody);
  //
  //@POST("api/updateJPersonal")
  //Call<DTO> updatePenduduk(@Body RequestBody requestBody);
  //
  //@POST("api/profilePenduduk/{nik}")
  //Call<DTO> updateFotoPersonal(@Body RequestBody requestBody, @Path("nik") String nik);
  //
  //@GET("api/findJKeluarga")
  //Call<DTO<CariKeluarga>> cariJKeluarga(@Query("kk") String kk);
  //
  //@POST("api/jKeluarga")
  //Call<DTO> saveJKeluarga(@Body RequestBody requestBody);
  //
  //@POST("api/updateJKeluarga")
  //Call<DTOList<KriteriaRes.DatasBean>> updateJKeluarga(@Body RequestBody requestBody);
  //
  //@POST("api/addFotoKeluarga/{kk}")
  //Call<DTOList<FotoKeluarga>> tambahFoto(@Body RequestBody requestBody, @Path("kk") String kk);
  //
  //@POST("api/kriteriaKeluarga")
  //Call<DTO> saveKriteria(@Body RequestBody body);
  //
  //@GET("api/getKriteriaKeluarga")
  //Call<DTO<KriteriaRes>> getAllKriteria(@Query("kk") String kk);
  //
  //@POST("api/updateKriteriaKeluarga")
  //Call<DTO> updateKriteriaKeluarga(@Body List<KriteriaReq> list);
  //
  //@POST("api/updateFotoLantai")
  //Call<DTOList> updateFotoLantai(@Body RequestBody body);
  //
  //@POST("api/updateFotoAtap")
  //Call<DTOList> updateFotoAtap(@Body RequestBody body);
  //
  //@POST("api/updateFotoDinding")
  //Call<DTOList> updateFotoDinding(@Body RequestBody body);
  //
  //@POST("api/updateFotoMck")
  //Call<DTOList> updateFotoMck(@Body RequestBody body);
  //
  @GET("api/getKeluarga/{page}?") Observable<DTOList<MKeluarga>> getKeluargaPagging(
      @Path("page") int page, @Query("noKk") String kk);

  //
  @GET("api/getPersonal/{page}?") Observable<DTOList<MWarga>> getPersonalPagging(
      @Path("page") int page, @Query("nik") String nik);

  class Factory {
    private static final String CACHE_CONTROL = "Cache-Control";

    public static API getApi() {
      return new Retrofit.Builder().baseUrl(Constant.URL_BASE)
          .client(mClient())
          .addConverterFactory(GsonConverterFactory.create())
          .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
          .build()
          .create(API.class);
    }

    private static OkHttpClient mClient() {
      return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor())
          //.addInterceptor(whenOfflineCacheInterceptor())
          //.addNetworkInterceptor(cacheInterceptor())
          .addInterceptor(setCookie())
          .addInterceptor(getCookie())
          .connectTimeout(Constant.CONNECT_TIME_OUT, TimeUnit.SECONDS)
          .writeTimeout(Constant.WRITE_TIME_OUT, TimeUnit.SECONDS)
          .readTimeout(Constant.READ_TIME_OUT, TimeUnit.SECONDS)
          //.cache(buatCache())
          .build();
    }

    private static Cache buatCache() {
      Cache cache = null;
      try {
        cache = new Cache(new File(App.getInstance().getCacheDir(), "http-cache"),
            10 * 1024 * 1024); // 10 MB
      } catch (Exception e) {
        Timber.e(e, "Error creating cache file");
      }
      return cache;
    }

    private static HttpLoggingInterceptor httpLoggingInterceptor() {
      HttpLoggingInterceptor httpLoggingInterceptor =
          new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override public void log(String message) {
              Timber.d(message);
            }
          });
      httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.HEADERS
          : HttpLoggingInterceptor.Level.NONE);
      return httpLoggingInterceptor;
    }

    public static Interceptor cacheInterceptor() {
      return new Interceptor() {
        @Override public Response intercept(Chain chain) throws IOException {
          Response response = chain.proceed(chain.request());

          // re-write response header to force use of cache
          CacheControl cacheControl =
              new CacheControl.Builder().maxAge(2, TimeUnit.MINUTES).build();

          return response.newBuilder().header(CACHE_CONTROL, cacheControl.toString()).build();
        }
      };
    }

    public static Interceptor whenOfflineCacheInterceptor() {
      return new Interceptor() {
        @Override public Response intercept(Chain chain) throws IOException {
          Request request = chain.request();

          /*if (!App.memilikiNetwork()) {
            CacheControl cacheControl =
                new CacheControl.Builder().maxStale(7, TimeUnit.DAYS).build();

            request = request.newBuilder().cacheControl(cacheControl).build();
          }*/
          return chain.proceed(request);
        }
      };
    }

    public static Interceptor setCookie() {
      return new Interceptor() {
        @Override public Response intercept(Chain chain) throws IOException {
          Response originalResponse = chain.proceed(chain.request());

          if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
              cookies.add(header);
            }
            PrefUtil.setMapPref(Constant.PREF_SES, cookies);
          }

          return originalResponse;
        }
      };
    }

    public static Interceptor getCookie() {
      return new Interceptor() {
        @Override public Response intercept(Chain chain) throws IOException {
          Request.Builder builder = chain.request().newBuilder();
          HashSet<String> preferences = PrefUtil.getHashPref(Constant.PREF_SES);
          if (preferences != null) {
            for (String cookie : preferences) {
              builder.addHeader("Cookie", cookie);
              // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
              Timber.i(cookie);
            }
          }

          return chain.proceed(builder.build());
        }
      };
    }
  }
}
