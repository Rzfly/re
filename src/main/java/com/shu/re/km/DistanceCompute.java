package com.shu.re.km;
import java.util.Set;

public class DistanceCompute {
    /**
     * 求欧式距离
     */
    public static double getEuclideanDis(Point p1, Point p2) {
        double count_dis = 0;
        float[] p1_local_array = p1.getlocalArray();
        float[] p2_local_array = p2.getlocalArray();
 
        if (p1_local_array.length != p2_local_array.length) {
            throw new IllegalArgumentException("length of array must be equal!");
        }
 
        for (int i = 0; i < p1_local_array.length; i++) {
            count_dis += Math.pow(p1_local_array[i] - p2_local_array[i], 2);
        }
 
        return Math.sqrt(count_dis);
    }
    
    public static double calcDistance(Point point, Set<Cluster> clusterList) {
        double distance = Double.MAX_VALUE;
        for (Cluster cluster : clusterList) {
            distance = Math.min(distance, calcDistance(point, cluster));
        }
        return distance;
    }
    
    public static double calcDistance(Point data, Cluster cluster) {
        return  getEuclideanDis(data, cluster.getCenter());
    }

}
