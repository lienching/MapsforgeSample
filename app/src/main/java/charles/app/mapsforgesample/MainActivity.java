package charles.app.mapsforgesample;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import org.mapsforge.map.android.graphics.AndroidGraphicFactory;

public class MainActivity extends AppCompatActivity {

    private EnvironmentCheck checker;
    private MapDownloadManager downloadManager;
    private Button btn_simplemap;
    private Button btn_multimap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidGraphicFactory.createInstance(this.getApplication());

        setContentView(R.layout.activity_main);
        btn_simplemap = (Button)findViewById(R.id.btn_simple_map);
        btn_multimap = (Button)findViewById(R.id.btn_multi_map);

        btn_simplemap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),SimpleMapView.class);
                startActivity(intent);
            }
        });

        checker = new EnvironmentCheck(this);
        downloadManager = new MapDownloadManager(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkMapSource();
    }

    private void checkMapSource(){
        boolean task1,task2;
        task1 = checker.isMapResourceExist("world/world-lowres-0-7.map");
        task2 = checker.isMapResourceExist("asia/taiwan.map");
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
        if(!task1||!task2)
            Toast.makeText(this,"Map Resource is downloading, Please Wait...",Toast.LENGTH_LONG);
    }
}
