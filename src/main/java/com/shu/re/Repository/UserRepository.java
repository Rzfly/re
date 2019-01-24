package com.shu.re.Repository;

import com.shu.re.Model.User;
import com.shu.re.Repository.Custom.UserRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>,UserRepositoryCustom{
    Page<User> findByUuid(Pageable pageable);
}
