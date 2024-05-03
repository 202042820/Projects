import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Section {

    final public static String[] coursesDivided = new String[42];
    private ArrayList<String> days = new ArrayList<>();
    private String daysShort;
    private String sectionNumber;
    private String time;
    private Course currentCourse;
    private String type;
    final private static Course[] allCourses = new Course[42];

    public Section(String courseName) {

        for (int i = 0; i < allCourses.length; i++) {
            if (courseName.equals(allCourses[i].getCourseName())) {
                this.currentCourse = allCourses[i];
            }
        }
    }

    public static void nameDivider() {
        try (Scanner input = new Scanner(new File("Names.txt"))) {
            input.useDelimiter(",");
            for (int i = 0; input.hasNext(); i++) {
                coursesDivided[i] = input.next();
            }
        } catch (Exception e) {

        }
    }

    private static void courseCreator() {
        nameDivider();
        for (int i = 0; i < allCourses.length; i++) {
            allCourses[i] = new Course(coursesDivided[i]);
        }
    }

    public static void setAllCourses() {
        courseCreator();
    }

    public static Course[] getAllCourses() {
        return allCourses;
    }

    public String toString() {
        return "Course name: " + currentCourse.getCourseName() + ", Type: " + type + ",  Time: " + this.time
                + ", Days: "
                + Arrays.toString(days.toArray()) + ", Section number: " + sectionNumber;
    }

    public ArrayList<String> getdays() {
        return days;
    }

    public void setdays(ArrayList<String> days) {
        this.days = days;
    }

    public void setDaysShort(String daysShort) {
        this.daysShort = daysShort;
    }

    public String getDaysShort() {
        return daysShort;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setSectionNumber(String sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public String getSectionNumber() {
        return sectionNumber;
    }

    public Course getCurrentCourse() {
        return currentCourse;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime(String time) {
        return time;
    }
}