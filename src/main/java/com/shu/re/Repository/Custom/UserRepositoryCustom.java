package com.shu.re.Repository.Custom;
import java.util.List;

public interface UserRepositoryCustom {
    public List<Object> getCustomEntity(String uid);
    public List<Object> getDividedUsers(String sql);
}
