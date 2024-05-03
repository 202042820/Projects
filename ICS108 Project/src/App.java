import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class App {
    public static void main(String[] args) throws Exception {
        CourseOffer co = new CourseOffer();
        TreeMap<String, ArrayList<Section>> finory = co.getEverything();
        Course[] css = Section.getAllCourses();
        Iterator<Section> it = finory.get(css[0].getCourseName()).iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
