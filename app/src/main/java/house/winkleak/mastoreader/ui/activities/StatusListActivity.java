package house.winkleak.mastoreader.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import house.winkleak.mastoreader.R;
import house.winkleak.mastoreader.data.network.OnDownloadCompleteListener;
import house.winkleak.mastoreader.ui.fragments.StatusListFragment;

public class StatusListActivity extends AppCompatActivity implements OnDownloadCompleteListener {
    public static final String STATUS_LIST_FRAGMENT = "status_list_fragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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
