package house.winkleak.mastoreader.data.network.response;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("in_reply_to_id")
    @Expose
    private Object inReplyToId;
    @SerializedName("in_reply_to_account_id")
    @Expose
    private Object inReplyToAccountId;
    @SerializedName("sensitive")
    @Expose
    private Boolean sensitive;
    @SerializedName("spoiler_text")
    @Expose
    private String spoilerText;
    @SerializedName("visibility")
    @Expose
    private String visibility;
    @SerializedName("language")
    @Expose
    private Object language;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("reblogs_count")
    @Expose
    private Integer reblogsCount;
    @SerializedName("favourites_count")
    @Expose
    private Integer favouritesCount;
    @SerializedName("reblog")
    @Expose
    private Object reblog;
    @SerializedName("application")
    @Expose
    private Object application;
    @SerializedName("account")
    @Expose
    private Account account;
    @SerializedName("media_attachments")
    @Expose
    private List<MediaAttachment> mediaAttachments = new ArrayList<>();
    @SerializedName("mentions")
    @Expose
    private List<Object> mentions = null;
    @SerializedName("tags")
    @Expose
    private List<Tag> tags = null;
    @SerializedName("emojis")
    @Expose
    private List<Object> emojis = null;

    public String getCreatedAt() {
        return createdAt;
    }

    public Account getAccount() {
        return account;
    }

    public String getContent() {
        return content;
    }

    public List<MediaAttachment> getMediaAttachments() {
        return mediaAttachments;
    }

    public class Account {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("acct")
        @Expose
        private String acct;
        @SerializedName("display_name")
        @Expose
        private String displayName;
        @SerializedName("locked")
        @Expose
        private Boolean locked;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("note")
        @Expose
        private String note;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("avatar")
        @Expose
        private String avatar;
        @SerializedName("avatar_static")
        @Expose
        private String avatarStatic;
        @SerializedName("header")
        @Expose
        private String header;
        @SerializedName("header_static")
        @Expose
        private String headerStatic;
        @SerializedName("followers_count")
        @Expose
        private Integer followersCount;
        @SerializedName("following_count")
        @Expose
        private Integer followingCount;
        @SerializedName("statuses_count")
        @Expose
        private Integer statusesCount;

        public String getUsername() {
            return username;
        }

        public String getDisplayName() {
            return displayName;
        }

        public String getAvatar() {
            return avatar;
        }
    }

    public class MediaAttachment {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("preview_url")
        @Expose
        private String previewUrl;
        @SerializedName("remote_url")
        @Expose
        private String remoteUrl;
        @SerializedName("text_url")
        @Expose
        private Object textUrl;
        @SerializedName("meta")
        @Expose
        private Meta meta;
        @SerializedName("description")
        @Expose
        private Object description;


        public String getUrl() {
            return url;
        }

        private class Meta {

            @SerializedName("original")
            @Expose
            private Original original;
            @SerializedName("small")
            @Expose
            private Small small;

        }


        private class Original {

            @SerializedName("width")
            @Expose
            private Integer width;
            @SerializedName("height")
            @Expose
            private Integer height;
            @SerializedName("size")
            @Expose
            private String size;
            @SerializedName("aspect")
            @Expose
            private Double aspect;

        }

        private class Small {

            @SerializedName("width")
            @Expose
            private Integer width;
            @SerializedName("height")
            @Expose
            private Integer height;
            @SerializedName("size")
            @Expose
            private String size;
            @SerializedName("aspect")
            @Expose
            private Double aspect;

        }
    }
    private class Tag {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("url")
        @Expose
        private String url;

    }
}