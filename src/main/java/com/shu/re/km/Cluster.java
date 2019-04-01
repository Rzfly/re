package com.shu.re.km;

import java.util.ArrayList;
import java.util.List;
 
public class Cluster {
    private int id;// 标识
    private Datasi center;// 中心
    private List<Datasi> members = new ArrayList<Datasi>();// 成员
 
    public Cluster(int id, Datasi center) {
        this.id = id;
        this.center = center;
    }
 
    public Cluster(int id, Datasi center, List<Datasi> members) {
        this.id = id;
        this.center = center;
        this.members = members;
    }
 
    public void addPoint(Datasi newPoint) {
        if (!members.contains(newPoint)){
            members.add(newPoint);
        }else{
            System.out.println("样本数据点 {"+newPoint.toString()+"} 已经存在！");
        }
    }
 
    public int getId() {
        return id;
    }
 
    public Datasi getCenter() {
        return center;
    }
 
    public void setCenter(Datasi center) {
        this.center = center;
    }
 
    public List<Datasi> getMembers() {
        return members;
    }
 
    @Override
    public String toString() {
        String toString = "Cluster \n" + "Cluster_id=" + this.id + ", center:{" + this.center.toString()+"}";
        for (Datasi point : members) {
            toString+="\n"+point.toString();
        }
        return toString+"\n";
    }
}
