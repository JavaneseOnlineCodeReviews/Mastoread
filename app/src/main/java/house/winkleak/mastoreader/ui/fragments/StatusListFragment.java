package house.winkleak.mastoreader.ui.fragments;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import house.winkleak.mastoreader.R;
import house.winkleak.mastoreader.data.managers.DataManager;
import house.winkleak.mastoreader.data.network.response.Status;
import house.winkleak.mastoreader.ui.adapters.StatusListAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusListFragment extends Fragment {
    private DataManager mDataManager;
    private List<Status> mStatusList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private StatusListAdapter mStatusListAdapter;
    public StatusListFragment() {
    }
    public static StatusListFragment newInstance(){
        return new StatusListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataManager = DataManager.getInstance();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_status_list, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Call<List<Status>> call = mDataManager.getTimelinesPublic();
        call.enqueue(new Callback<List<Status>>() {
            @Override
            public void onResponse(@NonNull Call<List<Status>> call, @NonNull Response<List<Status>> response) {
            if(response.isSuccessful()) {
                mStatusList.addAll(response.body());
                mStatusListAdapter = new StatusListAdapter(mStatusList);
                mRecyclerView.setAdapter(mStatusListAdapter);
             }
            }

            @Override
            public void onFailure(@NonNull Call<List<Status>> call, @NonNull Throwable t) {

            }
        });
        return view;
    }
}
