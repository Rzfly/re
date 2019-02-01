package com.shu.re.Repository;

import com.shu.re.Model.Course;
import com.shu.re.Repository.Custom.CourseRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String>,CourseRepositoryCustom{
    Page<Course> findAll(Pageable pageable);
    Course findByUuid(String uuid);
//    Course findById(String id);
}
