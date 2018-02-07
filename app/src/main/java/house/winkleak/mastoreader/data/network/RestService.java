package house.winkleak.mastoreader.data.network;

import java.util.List;

import house.winkleak.mastoreader.data.network.response.Status;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Happy on 06.02.2018.
 * Интерфейс необходимый для работы Retrofit
 * содержит в себе запросы к API
 */

public interface RestService {

@GET("timelines/tag/[tag]")
    Call<List<Status>> getTimelineByTag(@Path("tag") String tag);

@GET("timelines/public/")
    Call<List<Status>> getTimelinePublic();

}

