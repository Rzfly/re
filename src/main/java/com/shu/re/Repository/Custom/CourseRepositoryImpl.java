package com.shu.re.Repository.Custom;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.shu.re.Model.Course;

import java.math.BigInteger;
import java.util.List;

public class CourseRepositoryImpl implements CourseRepositoryCustom {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    public List<Course> getDividedCourses(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        // Query 接口是 spring-data-jpa 的接口，而 SQLQuery 接口是 hibenate 的接口，这里的做法就是先转成 hibenate 的查询接口对象，然后设置结果转换器
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        System.out.println(query.getResultList());
        return query.getResultList();
    }

    public int findByNum(String key) {
        if (key == "16124400") {
            key = "";
        }
        String hql = ""
                + "select count(c.id) FROM Course c where "
                + " concat("
                + "IFNULL(c.courseName,''),"
                + "IFNULL(c.courseNum,''),"
                + "IFNULL(c.courseTime,''),"
                + ") LIKE '%" + (key == null ? "" : key) + "%' "
                + "";
        try {
            System.err.println(hql);
            Query query = entityManager.createNativeQuery(hql);
            // Query 接口是 spring-data-jpa 的接口，而 SQLQuery 接口是 hibenate 的接口，这里的做法就是先转成 hibenate 的查询接口对象，然后设置结果转换器
            query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            BigInteger t = ((BigInteger) query.getResultList().get(0));
            return t.intValue();
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
