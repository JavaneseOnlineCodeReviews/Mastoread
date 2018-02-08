package house.winkleak.mastoreader.data.storage;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import house.winkleak.mastoreader.data.network.response.Status;

public class StatusDTO implements Parcelable {

    private String mAvatarUrl;
    private String mName;
    private String mNickname;
    private String mCreatedAt;
    private String mContent;
    private List<String> mContentMediaUrls;

    public StatusDTO(Status status) {
        this.mAvatarUrl = status.getAccount().getAvatar();
        this.mName = status.getAccount().getDisplayName();
        this.mNickname = status.getAccount().getUsername();
        this.mCreatedAt = status.getCreatedAt();
        this.mContent = status.getContent();
        this.mContentMediaUrls = status.getImageUrls(status.getMediaAttachments());
    }

    protected StatusDTO(Parcel in) {
        mAvatarUrl = in.readString();
        mName = in.readString();
        mNickname = in.readString();
        mCreatedAt = in.readString();
        mContent = in.readString();
        if (in.readByte() == 0x01) {
            mContentMediaUrls = new ArrayList<>();
            in.readList(mContentMediaUrls, String.class.getClassLoader());
        } else {
            mContentMediaUrls = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mAvatarUrl);
        dest.writeString(mName);
        dest.writeString(mNickname);
        dest.writeString(mCreatedAt);
        dest.writeString(mContent);
        if (mContentMediaUrls == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(mContentMediaUrls);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<StatusDTO> CREATOR = new Parcelable.Creator<StatusDTO>() {
        @Override
        public StatusDTO createFromParcel(Parcel in) {
            return new StatusDTO(in);
        }

        @Override
        public StatusDTO[] newArray(int size) {
            return new StatusDTO[size];
        }
    };

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public String getName() {
        return mName;
    }

    public String getNickname() {
        return mNickname;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public String getContent() {
        return mContent;
    }

    public List<String> getContentMediaUrls() {
        return mContentMediaUrls;
    }
}
