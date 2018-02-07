package house.winkleak.mastoreader.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import house.winkleak.mastoreader.R;
import house.winkleak.mastoreader.data.managers.DataManager;
import house.winkleak.mastoreader.data.network.response.Status;

public class StatusListAdapter extends RecyclerView.Adapter<StatusListAdapter.ViewHolder> {
    private static final String TAG = "StatusListAdapter";
    private List<Status> mStatusList;

    public StatusListAdapter(List<Status> statusList) {
        this.mStatusList = statusList;
    }

    @Override
    public StatusListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_status_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StatusListAdapter.ViewHolder holder, int position) {
        Status status = mStatusList.get(position);


        String relativeTime = getRelativeTime(status.getCreatedAt());


        DataManager.getInstance().getPicasso()
                .load(status.getAccount().getAvatar())
                .resize(120, 120)
                .centerCrop()
                .into(holder.avatar);

        holder.name.setText(status.getAccount().getDisplayName());
        holder.nickname.setText(String.format("@%s", status.getAccount().getUsername()));
        holder.createdAt.setText(relativeTime);
        String text = Html.fromHtml(status.getContent()).toString();
        holder.statusText.setText(text);
    }

    private String getRelativeTime(String createdAt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date date1 = sdf.parse(createdAt);
            long time = date1.getTime();
            Time now = new Time();
            now.setToNow();
            Log.d(TAG, "time = " + time);

            return DateUtils.getRelativeTimeSpanString(time,
                    now.toMillis(true),
                    DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return createdAt;
    }

    @Override
    public int getItemCount() {
        return mStatusList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView name, nickname, createdAt, statusText;

         ViewHolder(View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            name = itemView.findViewById(R.id.account_name);
            nickname = itemView.findViewById(R.id.account_nickname);
            createdAt = itemView.findViewById(R.id.created_at);
            statusText = itemView.findViewById(R.id.status_text);
        }
    }

}
