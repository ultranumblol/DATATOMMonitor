package datatom.com.datatommonitor.Api


import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * Created by wgz on 2017/7/25.
 */

interface ApiService {

    @Headers("Domain-Name: push")
    @GET("/v2/book/{id}")
    fun getPushMsg(@Path("id") id: Int): Observable<ResponseBody>


    @Headers("Domain-Name: addpro")
    @GET("")
    fun addProduction(
                      @Path("username") username : String,
                      @Path("pwd") pwd : String

                      ) :Observable<String>

}
