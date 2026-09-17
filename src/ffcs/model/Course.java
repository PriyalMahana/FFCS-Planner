package ffcs.model;

import java.util.List;

public class Course {

    private String courseCode;
    private String courseName;
    private String facultyName;
    private int credits;
    private String type;
    private List<Slot> slots;

    public Course(String courseCode, String courseName, String facultyName,
                  int credits, String type, List<Slot> slots) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.facultyName = facultyName;
        this.credits = credits;
        this.type = type;
        this.slots = slots;
    }

    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public String getFacultyName() { return facultyName; }
    public int getCredits() { return credits; }
    public String getType() { return type; }
    public List<Slot> getSlots() { return slots; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(courseCode).append(" | ").append(courseName)
          .append(" | ").append(facultyName)
          .append(" | ").append(credits).append(" credits")
          .append(" | ").append(type).append(" | Slots: ");
        for (Slot s : slots) {
            sb.append(s.getSlotCode()).append(" ");
        }
        return sb.toString().trim();
    }
}
