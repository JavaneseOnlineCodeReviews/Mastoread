package house.winkleak.mastoreader.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import house.winkleak.mastoreader.R;
import house.winkleak.mastoreader.data.managers.DataManager;
import house.winkleak.mastoreader.data.network.OnDownloadCompleteListener;
import house.winkleak.mastoreader.data.network.StatusListFetcher;
import house.winkleak.mastoreader.ui.fragments.StatusListFragment;

public class StatusListActivity extends AppCompatActivity implements OnDownloadCompleteListener {
    public static final String STATUS_LIST_FRAGMENT = "status_list_fragment";
    private DataManager mDataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDataManager = DataManager.getInstance();

        if(findViewById(R.id.fragment_container) != null){
            StatusListFragment listFragment = StatusListFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                    listFragment,
                    STATUS_LIST_FRAGMENT)
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_status_list, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView()  ;
        searchView.setQueryHint(getString(R.string.hint_search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mDataManager.getPreferencesManager().saveSearchTag(query);
                StatusListFetcher.fetchStatusList(StatusListActivity.this);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                   if(query.isEmpty() || query.equals("")){
                       mDataManager.getPreferencesManager().saveSearchTag(query);
                   }

                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            StatusListFetcher.fetchStatusList(StatusListActivity.this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDownloaded() {
        StatusListFragment fragment = (StatusListFragment) getSupportFragmentManager().findFragmentByTag(STATUS_LIST_FRAGMENT);
        if(fragment!= null){
            fragment.updateAdapter();
        }
    }
}
