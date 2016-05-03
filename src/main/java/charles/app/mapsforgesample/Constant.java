package charles.app.mapsforgesample;

import android.os.Environment;

/**
 * Created by lienching on 10/19/15.
 */
public class Constant {
    private static final String PATH_MAPSFORGE = Environment.getExternalStorageDirectory().toString() + "/mapsforge/maps/";
    public static final String PATH_WORLDMAP = PATH_MAPSFORGE + "world/world-lowres-0-7.map";
    public static final String PATH_TAIWANMAP = PATH_MAPSFORGE + "asia/taiwan.map";

}
