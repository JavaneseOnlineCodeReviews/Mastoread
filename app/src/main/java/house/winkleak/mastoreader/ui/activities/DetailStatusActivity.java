package house.winkleak.mastoreader.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import house.winkleak.mastoreader.R;
import house.winkleak.mastoreader.data.storage.StatusDTO;
import house.winkleak.mastoreader.util.ConstantManager;

public class DetailStatusActivity extends AppCompatActivity {
        private StatusDTO mStatusDTO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_status);
        mStatusDTO = getIntent().getParcelableExtra(ConstantManager.DETAIL_STATUS_ACTIVITY_INTENT);
        Log.d("ppp", "onCreate: ");
    }
}
