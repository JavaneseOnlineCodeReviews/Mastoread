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
import house.winkleak.mastoreader.util.RelativeTimeConverter;

public class StatusListAdapter extends RecyclerView.Adapter<StatusListAdapter.ViewHolder> {
    private List<Status> mStatusList;
    private OnStatusCardClickListener mStatusCardClickListener;

    public StatusListAdapter(List<Status> statusList, OnStatusCardClickListener statusCardClickListener) {
        this.mStatusList = statusList;
        this.mStatusCardClickListener = statusCardClickListener;
    }

    @Override
    public StatusListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_status_list, parent, false);

        return new ViewHolder(view, mStatusCardClickListener);
    }

    @Override
    public void onBindViewHolder(StatusListAdapter.ViewHolder holder, int position) {
        Status status = mStatusList.get(position);

        String relativeTime = RelativeTimeConverter.getRelativeTime(status.getCreatedAt());

        DataManager.getInstance().getPicasso()
                .load(status.getAccount().getAvatar())
                .resize(120, 120)
                .centerCrop()
                .into(holder.avatar);

        holder.name.setText(status.getAccount().getDisplayName());
        holder.nickname.setText(String.format("@%s", status.getAccount().getUsername()));
        holder.createdAt.setText(relativeTime);
        String text = Html.fromHtml(status.getContent()).toString();
        holder.content.setText(text);
    }



    @Override
    public int getItemCount() {
        return mStatusList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView avatar;
        TextView name, nickname, createdAt, content;
        OnStatusCardClickListener mStatusCardClickListener;

         ViewHolder(View itemView, OnStatusCardClickListener statusCardClickListener) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            name = itemView.findViewById(R.id.dysplay_name);
            nickname = itemView.findViewById(R.id.account_nickname);
            createdAt = itemView.findViewById(R.id.created_at);
            content = itemView.findViewById(R.id.status_text);
            mStatusCardClickListener = statusCardClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mStatusCardClickListener.onStatusCardClick(getAdapterPosition());
        }
    }
    public interface OnStatusCardClickListener{
        void onStatusCardClick(int position);

    }

}
