package com.stevemu.core;

import com.stevemu.course.Course;
import com.stevemu.course.CourseRepository;
import com.stevemu.review.Review;
import com.stevemu.user.User;
import com.stevemu.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {
    private final CourseRepository courseRepository;
    private final UserRepository users;

    @Autowired
    public DatabaseLoader(CourseRepository courseRepository, UserRepository users) {
        this.courseRepository = courseRepository;
        this.users = users;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        Course course = new Course("java basics", "https://teamtreeshoec.om/library/java-basics");
//        course.addReview(new Review(3, "you are awesome"));
//        courseRepository.save(course);

        String[] templates = {
                "%s for beginner",
                "under the hood: %s",
                "advanced %s"
        };

        String [] buzzwords = {
                "Java",
                "JS",
                "Python",
                "spring hateos"
        };

        List<User> students = Arrays.asList(
                new User("jacobproffer", "Jacob",  "Proffer", "password", new String[] {"ROLE_USER"}),
                new User("mlnorman", "Mike",  "Norman", "password", new String[] {"ROLE_USER"}),
                new User("k_freemansmith", "Karen",  "Freeman-Smith", "password", new String[] {"ROLE_USER"}),
                new User("seth_lk", "Seth",  "Kroger", "password", new String[] {"ROLE_USER"}),
                new User("mrstreetgrid", "Java",  "Vince", "password", new String[] {"ROLE_USER"}),
                new User("anthonymikhail", "Tony",  "Mikhail", "password", new String[] {"ROLE_USER"}),
                new User("boog690", "AJ",  "Teacher", "password", new String[] {"ROLE_USER"}),
                new User("faelor", "Erik",  "Faelor Shafer", "password", new String[] {"ROLE_USER"}),
                new User("christophernowack", "Christopher",  "Nowack", "password", new String[] {"ROLE_USER"}),
                new User("calebkleveter", "Caleb",  "Kleveter", "password", new String[] {"ROLE_USER"}),
                new User("richdonellan", "Rich",  "Donnellan", "password", new String[] {"ROLE_USER"}),
                new User("albertqerimi", "Albert",  "Qerimi", "password", new String[] {"ROLE_USER"})
        );
        users.saveAll(students);
        users.save(new User("steve", "Mu", "stevemu", "123", new String[] {"ROLE_USER", "ROLE_ADMIN"}));

        ArrayList<Course> courseArrayList = new ArrayList<Course>();
        IntStream.range(0, 100)
                .forEach(i -> {
                    String template = templates[i % templates.length];
                    String buzzword = buzzwords[i % buzzwords.length];
                    String title = String.format(template, buzzword);
                    Course course = new Course(title, "example.com");
                    Review review = new Review((i % 5)+1, String.format("More %s please", buzzword));
                    review.setReviewer(students.get(i % students.size()));
                    course.addReview(review);
                    courseArrayList.add(course);
                });

        courseRepository.saveAll(courseArrayList);


    }
}
