/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2015 - 2016, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotools.gml3.simple;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;

import org.geotools.geometry.jts.WKTReader2;
import org.geotools.gml3.GML;
import org.w3c.dom.Document;
/**
 * Unit test for GeometryCollectionEncoder
 * 
 * @author 
 */
public class GeometryCollectionEncoderTest extends GeometryEncoderTestSupport {

    public void testGeometryCollectionEncoder() throws ParseException, Exception {
        GeometryCollectionEncoder gce = new GeometryCollectionEncoder(gtEncoder,"gml", GML.NAMESPACE);
        Geometry geometry = new WKTReader2().read(
            "GEOMETRYCOLLECTION (LINESTRING"
            + " (180 200, 160 180), POINT (19 19), POINT (20 10))");
        Document doc = encode(gce, geometry);
        assertEquals(1,
            xpath.getMatchingNodes("//gml:LineString", doc).getLength());
        assertEquals(2, xpath.getMatchingNodes("//gml:Point", doc).getLength());
        assertEquals(1,
            xpath.getMatchingNodes("//gml:GeometryCollection", doc).getLength());
        assertEquals("180 200 160 180",
            xpath.evaluate(
                "//gml:GeometryCollection/gml:LineString/gml:posList", doc));
        assertEquals("19 19",
            xpath.evaluate(
                "//gml:GeometryCollection/gml:Point/gml:pos", doc));
    }
}
