package house.winkleak.mastoreader.data.network;

public interface OnDownloadCompleteListener {
    void onDownloaded();
    void onDownloadFailed(String error);
}
