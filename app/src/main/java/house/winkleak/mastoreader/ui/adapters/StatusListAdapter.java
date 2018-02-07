package house.winkleak.mastoreader.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import house.winkleak.mastoreader.R;
import house.winkleak.mastoreader.data.network.response.Status;

public class StatusListAdapter extends RecyclerView.Adapter<StatusListAdapter.ViewHolder> {
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

        holder.name.setText(status.getAccount().getUsername());
        holder.nickname.setText(status.getAccount().getDisplayName());
        holder.createdAt.setText(status.getCreatedAt());
        //String statusText = Html.fromHtml(status.getContent());
        String text = Html.fromHtml(status.getContent()).toString();
        holder.statusText.setText(text, TextView.BufferType.SPANNABLE);
    }

    @Override
    public int getItemCount() {
        return mStatusList.size();
    }

     static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView avatar;
        TextView name, nickname, createdAt, statusText;
        public ViewHolder(View itemView) {
             super(itemView);
             avatar = itemView.findViewById(R.id.avatar);
             name = itemView.findViewById(R.id.account_name);
             nickname = itemView.findViewById(R.id.account_nickname);
             createdAt = itemView.findViewById(R.id.created_at);
             statusText = itemView.findViewById(R.id.status_text);
         }
     }
}
