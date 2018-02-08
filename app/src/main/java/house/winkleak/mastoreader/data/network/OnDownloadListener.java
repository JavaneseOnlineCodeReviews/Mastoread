package house.winkleak.mastoreader.data.network;

public interface OnDownloadListener {
    void onDownloaded();
    void onDownloadFailed(String error);
}
