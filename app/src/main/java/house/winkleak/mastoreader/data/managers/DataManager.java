package house.winkleak.mastoreader.data.managers;

import android.content.Context;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import house.winkleak.mastoreader.data.network.PicassoCache;
import house.winkleak.mastoreader.data.network.RestService;
import house.winkleak.mastoreader.data.network.ServiceGenerator;
import house.winkleak.mastoreader.data.network.response.Status;
import house.winkleak.mastoreader.util.MastoReadApplication;
import retrofit2.Call;

/**
 * Created by Happy on 06.02.2018.
 * Класс - точка доступа ко всем данным приложения
 * используется паттеран синглтон
 */

    public class DataManager {
        private static DataManager INSTANCE = null;

        private RestService mRestService;
        private Picasso mPicasso;
        private Context mContext;
        private List<Status> mStatusList;



    private DataManager(){
        this.mContext = MastoReadApplication.getContext();
        this.mRestService = ServiceGenerator.createService(RestService.class);
        this.mPicasso = new PicassoCache(mContext).getPicassoInstance();
        this.mStatusList = new ArrayList<>();
    }

    public static DataManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }

    public Picasso getPicasso() {
        return mPicasso;
    }

    public Call<List<Status>> getTimelinesPublic(){
        return mRestService.getTimelinePublic();
    }

    public List<Status> getStatusList() {
        return mStatusList;
    }

    public void setStatusList(List<Status> statusList) {
        mStatusList.clear();
        mStatusList.addAll(statusList);

    }
}
