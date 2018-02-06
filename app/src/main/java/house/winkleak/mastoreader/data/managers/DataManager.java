package house.winkleak.mastoreader.data.managers;

import java.util.List;

import house.winkleak.mastoreader.data.network.RestService;
import house.winkleak.mastoreader.data.network.ServiceGenerator;
import house.winkleak.mastoreader.data.network.response.Status;
import retrofit2.Call;

/**
 * Created by Happy on 06.02.2018.
 * Класс - точка доступа ко всем данным приложения
 * используется паттеран синглтон
 */

    public class DataManager {
        private static DataManager INSTANCE = null;

        private RestService mRestService;

    private DataManager(){
        mRestService = ServiceGenerator.createService(RestService.class);
    }
    public static DataManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }

    public Call<List<Status>> getTimelinesPublic(){
        return mRestService.getTimelinePublic();
    }
}
