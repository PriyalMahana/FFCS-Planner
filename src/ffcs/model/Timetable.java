package ffcs.model;

import java.util.ArrayList;
import java.util.List;

public class Timetable {

    private List<Course> registeredCourses;
    private int totalCredits;

    public Timetable() {
        registeredCourses = new ArrayList<>();
        totalCredits = 0;
    }

    public void addCourse(Course course) {
        registeredCourses.add(course);
        totalCredits += course.getCredits();
    }

    public boolean removeCourse(String courseCode) {
        for (int i = 0; i < registeredCourses.size(); i++) {
            if (registeredCourses.get(i).getCourseCode().equalsIgnoreCase(courseCode)) {
                totalCredits -= registeredCourses.get(i).getCredits();
                registeredCourses.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public boolean isEmpty() {
        return registeredCourses.isEmpty();
    }
}
