package charles.app.mapsforgesample;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;


/**
 * Created by lienching on 10/14/15.
 * The purpose of this class is to define the map resource download methods, so we
 * can easily download a map resource by this class.
 */
public class MapDownloadManager{
    private DownloadManager downloadManager;
    private DownloadManager.Request request;
    private String external_path = Environment.getExternalStorageDirectory().toString();
    private Context context;
    public MapDownloadManager(Context context){
        this.context = context;
        downloadManager = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);

    }
    public void downloadMapResource(String mapfile){
        Uri download_URI = Uri.parse("http://download.mapsforge.org/maps/"+mapfile);
        request = new DownloadManager.Request(download_URI);
        //Set the network type which is allow to process the download request
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        //Set whether this download may proceed over a roaming connection.
        request.setAllowedOverRoaming(false);
        request.setTitle("Mapsforge Map Download");
        request.setDestinationInExternalPublicDir(external_path+"/mapsforge/maps/",mapfile);
        downloadManager.enqueue(request);
    }
}
