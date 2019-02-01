package com.shu.re.Repository.Custom;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import javax.persistence.Query;

public class UserRepositoryImpl implements UserRepositoryCustom{

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Object> getCustomEntity(String uid) {
        String sql = "select u.username from User u where uid = :uid";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("uid", uid);
        // Query 接口是 spring-data-jpa 的接口，而 SQLQuery 接口是 hibenate 的接口，这里的做法就是先转成 hibenate 的查询接口对象，然后设置结果转换器
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    public List<Object> getDividedUsers(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        // Query 接口是 spring-data-jpa 的接口，而 SQLQuery 接口是 hibenate 的接口，这里的做法就是先转成 hibenate 的查询接口对象，然后设置结果转换器
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }
}
