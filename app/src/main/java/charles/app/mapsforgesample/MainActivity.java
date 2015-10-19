package charles.app.mapsforgesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private EnvironmentCheck checker;
    private MapDownloadManager downloadManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checker = new EnvironmentCheck(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkMapSource();
    }

    private void checkMapSource(){
        boolean task1=false,task2=false;
        task1 = checker.isMapResourceExist("world.map");
        task2 = checker.isMapResourceExist("taiwan.map");
        if(!task1){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    downloadManager.downloadMapResource("world/world-lowres-0-7.map");
                }
            });
            thread.run();
        }
        if(!task2){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    downloadManager.downloadMapResource("asia/taiwan.map");
                }
            });
            thread.run();
        }
    }
}
