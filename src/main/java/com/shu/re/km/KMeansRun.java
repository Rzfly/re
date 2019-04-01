package com.shu.re.km;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class KMeansRun {
    private int kNum;                             //簇的个数
    private int iterNum = 10;                     //迭代次数
 
    private int iterMaxTimes = 100000;            //单次迭代最大运行次数
    private int iterRunTimes = 0;                 //单次迭代实际运行次数
    private float disDiff = (float) 0.01;         //单次迭代终止条件，两次运行中类中心的距离差
 
    private List<Datasi> original_data;    //用于存放，原始数据集
    private static List<Datasi> pointList = null;  //用于存放，原始数据集所构建的点集
    private DistanceCompute disC = new DistanceCompute();
    private int len = 0;                          //用于记录每个数据点的维度
    private String inicourse = null;
    private int courseCluId = -1;
    public KMeansRun(int k, List<Datasi> original_data) {
        this.kNum = k;
        this.original_data = original_data;
        this.len = original_data.get(0).getlocalArray().length;
        //检查规范
        check();
        //初始化点集。
        pointList = original_data;
        //init();
    }
//
//    public KMeansRun(int k, Map<String,String> qc,List<Course> courseList) {
//        this.kNum = k;
//        this.original_data = original_data;
//        this.len = original_data.get(0).getlocalArray().length;
//        //检查规范
//        check();
//        //初始化点集。
//        pointList = original_data;
//        //init();
//    }

 
    /**
     * 检查规范
     */
    private void check() {
        if (kNum == 0){
            throw new IllegalArgumentException("k must be the number > 0");  
        }
        if (original_data == null){
            throw new IllegalArgumentException("program can't get real data");
        }
    } 
 
    /** 
     * 初始化数据集，把数组转化为Point类型。
     */
//    private void init() {
//        pointList = new ArrayList<Point>();
//        for (int i = 0, j = original_data.size(); i < j; i++){
//            pointList.add(new Point(i, original_data.get(i).getlocalArray()));
//        }
//    }
 
    /** 
     * 最远距离法寻找中心点。
     */  
    private Set<Cluster> chooseCenterCluster(List<Datasi> pointList) {
        Set<Cluster> clusterSet = new HashSet<>();
        while (clusterSet.size() < this.kNum) {
           clusterSet.add(createClusterBasedFurthestData(pointList, clusterSet));
        }
        return clusterSet;  
    }


    /**
     * 随机选取中心点，构建成中心类。
     */
    private Set<Cluster> chooseCenterCluster() {
        Set<Cluster> clusterSet = new HashSet<Cluster>();
        Random random = new Random();
        for (int id = 0; id < kNum; ) {
                Datasi point = pointList.get(random.nextInt(pointList.size()));
            // 用于标记是否已经选择过该数据。
            boolean flag =true;
            for (Cluster cluster : clusterSet) {
                if (cluster.getCenter().equals(point)) {
                    flag = false;
                }
            }
            // 如果随机选取的点没有被选中过，则生成一个cluster
            if (flag) {
                Cluster cluster =new Cluster(id, point);
                clusterSet.add(cluster);
                id++;
            }
        }
        return clusterSet;  
    }

    
    
    /** 基于最远距离创建一个新的簇 */
    private Cluster createClusterBasedFurthestData(List<Datasi> pointList, Set<Cluster> clusterSet) {
        //找离该中心点最远的点
        Datasi furthestDocument = this.findFurthestData(pointList, clusterSet);
        //创建一个新的簇
        Cluster nextCluster = new Cluster(clusterSet.size()+1,furthestDocument);
        return nextCluster;
    }
    
    public Datasi findFurthestData(List<Datasi> pointList,Set<Cluster> clusterSet) {
    	double furthestDistance =  Double.MIN_VALUE;
        Datasi furthestData = null;
        for (int i=0;i<pointList.size();i++) {
            Datasi p = pointList.get(i);
			if(p.getClusterId() != -1) {
                //找最远距离
				double dataDistance = DistanceCompute.calcDistance(p, clusterSet);
                if (dataDistance > furthestDistance) {
                    furthestDistance = dataDistance;
                    furthestData = p;
                }
            }
        }
        return furthestData;
    }
    /**
     * 为每个点分配一个类！
     */
    public void cluster(Set<Cluster> clusterSet,String inicourse){
        // 计算每个点到K个中心的距离，并且为每个点标记类别号
        for (Datasi point : pointList) {
            float min_dis = Integer.MAX_VALUE;
            for (Cluster cluster : clusterSet) {
                float tmp_dis = (float) Math.min(disC.getEuclideanDis(point, cluster.getCenter()), min_dis);
                if (tmp_dis != min_dis) {
                    min_dis = tmp_dis;
                    point.setClusterId(cluster.getId());
                    point.setDist(min_dis);
                    if(inicourse != null)
                    {
                        if(point.getCourse().getUuid().equals(inicourse)){
                            courseCluId = point.getClusterId();
                        }
                    }
                }
            }
        }
        // 新清除原来所有的类中成员。把所有的点，分别加入每个类别
        for (Cluster cluster : clusterSet) {
            cluster.getMembers().clear();
            for (Datasi point : pointList) {
                if (point.getClusterId()==cluster.getId()) {
                    cluster.addPoint(point);
                }
            }
        }
    }
 
    /**
     * 计算每个类的中心位置！
     */
    public boolean calculateCenter(Set<Cluster> clusterSet) {
        boolean ifNeedIter = false; 
        for (Cluster cluster : clusterSet) {
            List<Datasi> point_list = cluster.getMembers();
            float[] sumAll =new float[len];
            // 所有点，对应各个维度进行求和
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < point_list.size(); j++) {
                    sumAll[i] += point_list.get(j).getlocalArray()[i];
                }
            }
            // 计算平均值
            for (int i = 0; i < sumAll.length; i++) {
                sumAll[i] = (float) sumAll[i]/point_list.size();
            }
            // 计算两个新、旧中心的距离，如果任意一个类中心移动的距离大于dis_diff则继续迭代。
            if(disC.getEuclideanDis(cluster.getCenter(), new Point(sumAll)) > disDiff){
                ifNeedIter = true;
            }
            // 设置新的类中心位置
            Datasi d = new Datasi(null,"-1",sumAll);
            cluster.setCenter(d);
        }
        return ifNeedIter;
    }
 
    /**
     * 运行 k-means
     */
    public Set<Cluster> run() {
//        Set<Cluster> clusterSet= chooseCenterCluster();
            Set<Cluster> clusterSet= chooseCenterCluster(pointList);
        boolean ifNeedIter = true; 
        while (ifNeedIter) {
            cluster(clusterSet,inicourse);
            ifNeedIter = calculateCenter(clusterSet);
            iterRunTimes ++ ;
        }
        return clusterSet;
    }
    //String inicourse
    public Set<Cluster> run(String inicourse) {
        this.inicourse = inicourse;
//        Set<Cluster> clusterSet= chooseCenterCluster();
        Set<Cluster> clusterSet= chooseCenterCluster(pointList);
        boolean ifNeedIter = true;
        while (ifNeedIter) {
            cluster(clusterSet,inicourse);
            ifNeedIter = calculateCenter(clusterSet);
            iterRunTimes ++ ;
        }
        return clusterSet;
    }
 
    /**
     * 返回实际运行次数
     */
    public int getIterTimes() {
        return iterRunTimes;
    }

    public int getCourseCluId() {
        return courseCluId;
    }

    public String getInicourse() {
        return inicourse;
    }
}
