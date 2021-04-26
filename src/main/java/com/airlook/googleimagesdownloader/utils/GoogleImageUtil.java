package com.airlook.googleimagesdownloader.utils;

/**
 * @description:
 * @author:
 * @date: 2021-04-13 21:04
 */
public class GoogleImageUtil {
    
    public static void main(String[] args) {
        double log = 115.24658203;
        double lat = 41.09985352;
        GoogleLonLatToXYZ(log, lat, 16);
    
        double log1 = 117.65258789;
        double lat1 = 41.14428711;
        GoogleLonLatToXYZ(log1, lat1, 16);
    }
    
    /**
     * 谷歌下转换经纬度对应的层行列 wgs84坐标系
     *
     * @param lon  经度
     * @param lat  维度
     * @param zoom 在第zoom层进行转换
     * @return
     */
    public static int[] GoogleLonLatToXYZ(double lon, double lat, int zoom) {
        
        double n = Math.pow(2, zoom);
        double tileX = ((lon + 180) / 360) * n;
        double tileY =
                (1 - (Math.log(Math.tan(Math.toRadians(lat)) + (1 / Math.cos(Math.toRadians(lat)))) / Math.PI)) / 2 * n;
        
        int[] xy = new int[2];
        
        xy[0] = (int) Math.floor(tileX);
        xy[1] = (int) Math.floor(tileY);
        System.out.println(zoom + " " + xy[0] + " " + xy[1]);
        return xy;
    }
    
    
}
