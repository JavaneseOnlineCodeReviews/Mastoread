package house.winkleak.mastoreader.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import house.winkleak.mastoreader.R;
import house.winkleak.mastoreader.data.managers.DataManager;
import house.winkleak.mastoreader.data.storage.StatusDTO;
import house.winkleak.mastoreader.util.ConstantManager;
import house.winkleak.mastoreader.util.RelativeTimeConverter;

public class DetailStatusActivity extends AppCompatActivity {
        private StatusDTO mStatusDTO;
        private ImageView mAvatar, mContentImg;
        private TextView mName, mNickname, mCreatedAt, mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_status);

        mAvatar = findViewById(R.id.avatar);
        mName = findViewById(R.id.dysplay_name);
        mNickname = findViewById(R.id.account_nickname);
        mCreatedAt = findViewById(R.id.created_at);
        mContent = findViewById(R.id.status_text);
        mContentImg = findViewById(R.id.content_image);

        mStatusDTO = getIntent().getParcelableExtra(ConstantManager.DETAIL_STATUS_ACTIVITY_INTENT);

        setStatusData();

    }
    //загружаем данные в View из StatusDTO, если есть вложенная картинка, загружаем и ее
    private void setStatusData(){
        DataManager.getInstance().getPicasso()
                .load(mStatusDTO.getAvatarUrl())
                .resize(120, 120)
                .centerCrop()
                .into(mAvatar);

        if(mStatusDTO.getContentMediaUrls().size()!=0){
            String contentImgUrl = mStatusDTO.getContentMediaUrls().get(0);
            DataManager.getInstance().getPicasso()
                    .load(contentImgUrl)
                    .into(mContentImg);
        }

        mName.setText(mStatusDTO.getName());
        mNickname.setText(String.format("@%s", mStatusDTO.getNickname()));
        String relativeTime = RelativeTimeConverter.getRelativeTime(mStatusDTO.getCreatedAt());
        mCreatedAt.setText(relativeTime);
        String text = Html.fromHtml(mStatusDTO.getContent()).toString();
        mContent.setText(text);
    }
}
