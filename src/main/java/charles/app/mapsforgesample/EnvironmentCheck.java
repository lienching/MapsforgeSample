package charles.app.mapsforgesample;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;

/**
 * Created by lienching on 10/14/15.
 * The purpose of this class is to check the map resource exist in
 * users device.
 */
public class EnvironmentCheck {
    private Context context;
    private String externalpath = Environment.getExternalStorageDirectory().getPath();
    public EnvironmentCheck(Context context){
        this.context = context;
    }

    public boolean isMapResourceExist(String mapfile){
        if(!(new File(externalpath+"/mapsforge").exists()))
            createDirectory();
        File searchFile = new File(externalpath+"/mapforge/maps/"+mapfile);
        if(searchFile.exists())
            return true;
        return false;
    }

    public void createDirectory(){
        File target = new File(externalpath+"/mapsforge/maps");
        target.mkdirs();
        if(target.exists()&&target.isDirectory()){
            Toast.makeText(context, R.string.toast_dir_success, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, R.string.toast_dir_fail_retry,Toast.LENGTH_SHORT).show();
            target.mkdir();
            if(target.exists()&&target.isDirectory()){
                Toast.makeText(context, R.string.toast_dir_success, Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, R.string.toast_dir_fail,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
