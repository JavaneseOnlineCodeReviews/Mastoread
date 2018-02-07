package house.winkleak.mastoreader.data.network;

import android.support.annotation.NonNull;

import java.util.List;

import house.winkleak.mastoreader.data.managers.DataManager;
import house.winkleak.mastoreader.data.network.response.Status;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusListFetcher {

    /**
     * Выбирает каким методом пользоваться, в зависимости от того пустой ли тэг
     */
    public static void fetchStatusList(final OnDownloadCompleteListener downloadCompleteListener){

        String searchTag = DataManager.getInstance().getPreferencesManager().getSearchTag();
        if(searchTag.equals("")|| searchTag.isEmpty()){
            fetchRecentPublic(downloadCompleteListener);
        }else {
            fetchStatusesByTeg(downloadCompleteListener, searchTag);
        }
    }



    /**
     * Обращается к сети с помощью Datamanager
     * и пытается получить последние добавленные публичные статусы
     */
    private static void fetchRecentPublic(final OnDownloadCompleteListener downloadCompleteListener) {
        Call<List<Status>> call = DataManager.getInstance().getPublicStatuses();
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


    /**
     * Обращается к сети с помощью Datamanager
     * и пытается получить статусы по тегу
     */
    private static void fetchStatusesByTeg(final OnDownloadCompleteListener downloadCompleteListener, String tag) {
        Call<List<Status>> call = DataManager.getInstance().getStatusesByTag(tag);
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

