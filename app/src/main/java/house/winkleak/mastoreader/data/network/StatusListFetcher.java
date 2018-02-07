package house.winkleak.mastoreader.data.network;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import house.winkleak.mastoreader.data.managers.DataManager;
import house.winkleak.mastoreader.data.network.response.Status;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusListFetcher {

    public static void fetchRecentPublic(final OnDownloadCompleteListener downloadCompleteListener){

        Call<List<Status>> call = DataManager.getInstance().getTimelinesPublic();
        call.enqueue(new Callback<List<Status>>() {
            @Override
            public void onResponse(@NonNull Call<List<Status>> call, @NonNull Response<List<Status>> response) {
                if(response.isSuccessful()) {
                    DataManager.getInstance().setStatusList(response.body());
                    downloadCompleteListener.onDownloaded();

                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Status>> call, @NonNull Throwable t) {

            }
        });
    }
}
