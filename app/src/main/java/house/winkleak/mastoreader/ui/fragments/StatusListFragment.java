package house.winkleak.mastoreader.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import house.winkleak.mastoreader.R;
import house.winkleak.mastoreader.data.managers.DataManager;
import house.winkleak.mastoreader.data.network.OnDownloadListener;
import house.winkleak.mastoreader.data.network.StatusListFetcher;
import house.winkleak.mastoreader.data.network.response.Status;
import house.winkleak.mastoreader.data.storage.StatusDTO;
import house.winkleak.mastoreader.ui.activities.DetailStatusActivity;
import house.winkleak.mastoreader.ui.adapters.StatusListAdapter;
import house.winkleak.mastoreader.util.ConstantManager;

public class StatusListFragment extends Fragment implements StatusListAdapter.OnStatusCardClickListener {

    private List<Status> mStatusList;
    private RecyclerView mRecyclerView;
    private StatusListAdapter mStatusListAdapter;
    private OnDownloadListener mDownloadCompleteListener;
    public StatusListFragment() {
    }
    public static StatusListFragment newInstance(){
        return new StatusListFragment();
    }
    //Берем список статусов если они были ранее загружены из DataManager
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStatusList = DataManager.getInstance().getStatusList();
    }
//Получаем экземпляр активити чтобы передать в качестве листенера загрузки данных из сети
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mDownloadCompleteListener = (OnDownloadListener)  context;

    }
//иницаилизируем Views, если список статусов пустой просим класс StatusListFetcher его получить из сети
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_status_list, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mStatusListAdapter = new StatusListAdapter(mStatusList, this);
        mRecyclerView.setAdapter(mStatusListAdapter);
        if(mStatusList.size()!=0){
            mStatusListAdapter.notifyDataSetChanged();
        }else {
            StatusListFetcher.fetchStatusList(mDownloadCompleteListener);
        }


        return view;
    }
// срабатывает при нажатии на элемент списка, после чего упаковываем статус в объект StatusDTO и передаем в DetailActivity
    @Override
    public void onStatusCardClick(int position) {
        StatusDTO statusDto = new StatusDTO(mStatusList.get(position));
        Intent detailStatusIntent = new Intent(getContext(), DetailStatusActivity.class);
        detailStatusIntent.putExtra(ConstantManager.DETAIL_STATUS_ACTIVITY_INTENT ,statusDto);
        startActivity(detailStatusIntent);
    }

    // вызывается из активити в случае если данные обновились и необходимо обновить адаптер
    public void updateAdapter(){
        mStatusListAdapter.notifyDataSetChanged();
    }
}
