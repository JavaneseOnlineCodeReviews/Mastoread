package house.winkleak.mastoreader.ui.activities;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import house.winkleak.mastoreader.R;
import house.winkleak.mastoreader.data.managers.DataManager;
import house.winkleak.mastoreader.data.network.OnDownloadListener;
import house.winkleak.mastoreader.data.network.StatusListFetcher;
import house.winkleak.mastoreader.ui.fragments.StatusListFragment;
import house.winkleak.mastoreader.util.ConstantManager;

public class StatusListActivity extends AppCompatActivity implements OnDownloadListener {

    public static final String STATUS_LIST_FRAGMENT = "status_list_fragment";
    private DataManager mDataManager;
    private CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mCoordinatorLayout = findViewById(R.id.coordinator);

        mDataManager = DataManager.getInstance();
        //Если фрагмент не существует, создаем новый
        if(findViewById(R.id.fragment_container) != null){
            if(getSupportFragmentManager().findFragmentByTag(STATUS_LIST_FRAGMENT)==null) {
                StatusListFragment listFragment = StatusListFragment.newInstance();
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                        listFragment,
                        STATUS_LIST_FRAGMENT)
                        .commit();

            }
        }
    }

// Заполняет меню элементами, помещает их в ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
                   if(query.isEmpty()){
                       mDataManager.getPreferencesManager().saveSearchTag(query);
                   }

                return false;
            }
        });
        return true;
    }
// срабатывает при клике на любой элемент меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            StatusListFetcher.fetchStatusList(StatusListActivity.this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//колбэк срабатывает при удачной загрузке данных из сети, находит фрагмент, и заставляет обновить данные адаптера
    @Override
    public void onDownloaded() {
        StatusListFragment fragment = (StatusListFragment) getSupportFragmentManager().findFragmentByTag(STATUS_LIST_FRAGMENT);
        if(fragment!= null){
            fragment.updateAdapter();
        }
    }
//срабатывает при неудачной попытке загрузки данных из сети
    @Override
    public void onDownloadFailed(String error) {
            Snackbar.make(mCoordinatorLayout, error, Snackbar.LENGTH_LONG).show();

    }
}
