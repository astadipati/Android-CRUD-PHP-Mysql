package net.ramastudio.myretro.model;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiRequestBiodata {

    @FormUrlEncoded
    @POST("insert.php")
//    sesuaikan dengan field yang ingin di isi
    Call<ResponseModel> sendBiodata(@Field("nama") String nama,
                                    @Field("usia") String usia,
                                    @Field("domisili") String domisili);
}
//kita sudah membuat handlingnya tinggal panggil di main karena activitynya di main
