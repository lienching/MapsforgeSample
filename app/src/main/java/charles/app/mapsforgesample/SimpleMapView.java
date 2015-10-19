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
        setContentView(mapView);
        worldMap = new MapFile(Constant.PATH_WORLDMAP);
        taiwanMap = new MapFile(Constant.PATH_TAIWANMAP);
        multiMapDataStore.addMapDataStore(worldMap,false,false);
        multiMapDataStore.addMapDataStore(taiwanMap,true,true);
        multiMapDataStore.setStartPosition(new LatLong(23, 121));
        multiMapDataStore.setStartZoomLevel((byte) 7);
        tileCache = AndroidUtil.createTileCache(this, "mapcache", mapView.getModel().displayModel.getTileSize(), 1f, this.mapView.getModel().frameBufferModel.getOverdrawFactor());
        tileRendererLayer = new TileRendererLayer(tileCache,multiMapDataStore,mapView.getModel().mapViewPosition,false,true, AndroidGraphicFactory.INSTANCE);
        tileRendererLayer.setXmlRenderTheme(InternalRenderTheme.OSMARENDER);
        mapView.getModel().mapViewPosition.setMapPosition(new MapPosition(new LatLong(23, 121), (byte) 1));
        mapView.getLayerManager().getLayers().add(tileRendererLayer);
    }

}