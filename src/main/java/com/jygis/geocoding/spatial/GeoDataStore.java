package com.jygis.geocoding.spatial;
import com.jygis.geocoding.PropertiesConfig;
import com.jygis.geocoding.util.FileUtil;
import org.geotools.data.shapefile.ShapefileDataStore;
import java.io.File;
import java.nio.charset.Charset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GeoDataStore {

    @Autowired
    private PropertiesConfig propertiesConfig;

    public ShapefileDataStore GetFeatureDataStore(){
        FileUtil.makeDir(propertiesConfig.getGeoFilePath());

        String geoFilePath = propertiesConfig.getGeoFilePath() + File.separator +propertiesConfig.getGeoFileName();
        ShapefileDataStore dataStore=null;

        try {
            // File f = new File(shapeFileLoc);
            File f = new File(geoFilePath);
            dataStore = new ShapefileDataStore(f.toURL());
            dataStore.setIndexed(true);
            dataStore.setMemoryMapped(true);
            dataStore.setFidIndexed(true);
            dataStore.setBufferCachingEnabled(true);
            dataStore.setCharset(Charset.forName("EUC-KR"));
             } catch (Exception e){
             System.out.println(e.getMessage());
        }
        return dataStore;
    }
}
