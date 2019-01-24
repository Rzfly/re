package com.shu.re.Repository.Custom;
import com.shu.re.Model.Course;
import java.util.List;

public interface CourseRepositoryCustom {

        public List<Course> getDividedCourses(String sql);
        public int findByNum(String key);
}
