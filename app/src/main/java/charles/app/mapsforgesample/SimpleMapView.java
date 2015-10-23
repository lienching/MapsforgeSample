package charles.app.mapsforgesample;

import android.app.Activity;
import android.os.Bundle;

import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.MapPosition;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.util.AndroidUtil;
import org.mapsforge.map.android.view.MapView;
import org.mapsforge.map.layer.cache.TileCache;
import org.mapsforge.map.layer.renderer.TileRendererLayer;
import org.mapsforge.map.reader.MapDataStore;
import org.mapsforge.map.reader.MapFile;
import org.mapsforge.map.reader.MultiMapDataStore;
import org.mapsforge.map.rendertheme.InternalRenderTheme;

/**
 * Created by lienching on 10/17/15.
 */
public class SimpleMapView extends Activity {

    private MapView mapView;
    private MultiMapDataStore multiMapDataStore;
    private MapDataStore worldMap,taiwanMap;
    private TileCache tileCache;
    private TileRendererLayer tileRendererLayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mapView = new MapView(this);
        mapView.setClickable(true);
        mapView.getMapScaleBar().setVisible(true);
        mapView.setBuiltInZoomControls(true);
        mapView.getMapZoomControls().setZoomLevelMin((byte) 5);
        mapView.getMapZoomControls().setZoomLevelMax((byte) 20);

        worldMap = new MapFile(Constant.PATH_WORLDMAP);
        taiwanMap = new MapFile(Constant.PATH_TAIWANMAP);
        multiMapDataStore = new MultiMapDataStore(MultiMapDataStore.DataPolicy.RETURN_ALL);

        tileCache = AndroidUtil.createTileCache(this, "mapcache", mapView.getModel().displayModel.getTileSize(), 1f, this.mapView.getModel().frameBufferModel.getOverdrawFactor());
        setContentView(mapView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        multiMapDataStore.addMapDataStore(worldMap,true,true);
        multiMapDataStore.addMapDataStore(taiwanMap,false,false);
        multiMapDataStore.setStartPosition(new LatLong(23, 121));
        multiMapDataStore.setStartZoomLevel((byte) 7);
        tileRendererLayer = new TileRendererLayer(tileCache,multiMapDataStore,mapView.getModel().mapViewPosition,false,true, AndroidGraphicFactory.INSTANCE);
        tileRendererLayer.setXmlRenderTheme(InternalRenderTheme.OSMARENDER);

        mapView.getModel().mapViewPosition.setMapPosition(new MapPosition(new LatLong(23, 121), (byte) 7));
        mapView.getLayerManager().getLayers().add(tileRendererLayer);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mapView.destroyAll();
    }
}
