package house.winkleak.mastoreader.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import house.winkleak.mastoreader.R;
import house.winkleak.mastoreader.data.managers.DataManager;
import house.winkleak.mastoreader.data.network.OnDownloadCompleteListener;
import house.winkleak.mastoreader.data.network.StatusListFetcher;
import house.winkleak.mastoreader.data.network.response.Status;
import house.winkleak.mastoreader.data.storage.StatusDTO;
import house.winkleak.mastoreader.ui.activities.DetailStatusActivity;
import house.winkleak.mastoreader.ui.adapters.StatusListAdapter;
import house.winkleak.mastoreader.util.ConstantManager;

public class StatusListFragment extends Fragment implements StatusListAdapter.OnStatusCardClickListener {

    private DataManager mDataManager;
    private List<Status> mStatusList;
    private RecyclerView mRecyclerView;
    private StatusListAdapter mStatusListAdapter;
    private OnDownloadCompleteListener mDownloadCompleteListener;
    public StatusListFragment() {
    }
    public static StatusListFragment newInstance(){
        return new StatusListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataManager = DataManager.getInstance();
        mStatusList = mDataManager.getStatusList();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mDownloadCompleteListener = (OnDownloadCompleteListener)  context;
    }

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

    @Override
    public void onStatusCardClick(int position) {
        StatusDTO statusDto = new StatusDTO(mStatusList.get(position));
        Intent detailStatusIntent = new Intent(getContext(), DetailStatusActivity.class);
        detailStatusIntent.putExtra(ConstantManager.DETAIL_STATUS_ACTIVITY_INTENT ,statusDto);
        startActivity(detailStatusIntent);
    }
    public void updateAdapter(){
        mStatusListAdapter.notifyDataSetChanged();
    }
}
