package com.jygis.geocoding.spatial;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;

import java.io.IOException;
import org.geotools.filter.text.cql2.CQL;
import org.geotools.filter.text.cql2.CQLException;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.Filter;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GeoCentroid {

   public Point GeoCenter(SimpleFeatureSource fc,String Query) throws IOException, CQLException {
      Point pt=null;
       GeometryFactory gf=new GeometryFactory();
       Filter filter = CQL.toFilter(Query);

       // Filter filter = CQL.toFilter("strConcat(A3,A4)="+EMD+BONBU+"'");
       //Filter filter = CQL.toFilter("jibun='"+Addr+"'");

       SimpleFeatureCollection features = fc.getFeatures(filter);
       SimpleFeatureIterator iterator= features.features();
       if(iterator.hasNext()==true) {
           SimpleFeature feature = (SimpleFeature) iterator.next();
           Geometry geometry = (Geometry) feature.getDefaultGeometry();
           pt = geometry.getCentroid();
       } else {
           pt = gf.createPoint(new Coordinate(0.0,0.0));
           System.out.println("pt!!!null");
       }
        iterator.close();
       return pt;
   }

}
