package house.winkleak.mastoreader.ui.fragments;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import house.winkleak.mastoreader.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class StatusListFragment extends Fragment {

    public StatusListFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_status_list, container, false);
    }
}
