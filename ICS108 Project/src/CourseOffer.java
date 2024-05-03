import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class CourseOffer {
    private ArrayList<Section> allSections;
    final private TreeMap<String, ArrayList<Section>> everything;

    public CourseOffer() {
        allSections = new ArrayList<>();
        Section.setAllCourses();
        everything = new TreeMap<String, ArrayList<Section>>();
        allCoursesSectionsMapper();
    }

    private void dataFetch(String courseName) {
        try (Scanner input = new Scanner(new File("CourseOffering.csv"))) {
            input.nextLine();
            input.useDelimiter(",");
            String temp1 = "";
            Section temp3;
            while (input.hasNext()) {
                temp3 = new Section(courseName.toUpperCase());
                temp1 = input.next();
                if (temp3.getCurrentCourse().getCourseName().equals(temp1.substring(0, 8))) {
                    temp3.setSectionNumber(temp1.substring(9));
                    temp3.setType(input.next());
                    input.next();
                    input.next();
                    input.next();
                    temp3.setDaysShort(input.next());
                    if (temp3.getDaysShort().equals("MW")) {
                        temp3.getdays().add("Monday");
                        temp3.getdays().add("Wednesday");
                    } else if (temp3.getDaysShort().equals("UT")) {
                        temp3.getdays().add("Sunday");
                        temp3.getdays().add("Tuesday");
                    } else if (temp3.getDaysShort().equals("UTR")) {
                        temp3.getdays().add("Sunday");
                        temp3.getdays().add("Tuesday");
                        temp3.getdays().add("Thursday");
                    } else if (temp3.getDaysShort().equals("U")) {
                        temp3.getdays().add("Sunday");
                    } else if (temp3.getDaysShort().equals("M")) {
                        temp3.getdays().add("Monday");
                    } else if (temp3.getDaysShort().equals("T")) {
                        temp3.getdays().add("Tuesday");
                    } else if (temp3.getDaysShort().equals("W")) {
                        temp3.getdays().add("Wednesday");
                    } else if (temp3.getDaysShort().equals("R")) {
                        temp3.getdays().add("Thursday");
                    } else if (temp3.getDaysShort().equals("UMTWR")) {
                        temp3.getdays().add("Sunday");
                        temp3.getdays().add("Monday");
                        temp3.getdays().add("Tuesday");
                        temp3.getdays().add("Wednesday");
                        temp3.getdays().add("Thursday");
                    }
                    temp3.setTime(input.next());
                    allSections.add(temp3);
                }

                input.nextLine();

            }
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }

    public ArrayList<Section> getAllSections() {
        return allSections;
    }

    private void allCoursesSectionsMapper() {
        Course[] temp = Section.getAllCourses();
        for (int i = 0; i < temp.length; i++) {
            dataFetch(temp[i].getCourseName());
            everything.put(temp[i].getCourseName(), allSections);
            allSections = new ArrayList<>();
        }

    }

    public TreeMap<String, ArrayList<Section>> getEverything() {
        return everything;
    }

}
