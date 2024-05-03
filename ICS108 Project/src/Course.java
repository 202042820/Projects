import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

public class Course {
    private String courseName, preRequisite, coRequuisite;
    private int creditHours;

    public Course(String courseName) {
        this.courseName = courseName;
        dataFetch();
    }

    private void dataFetch() {
        try (FileInputStream fis = new FileInputStream(new File("DegreePlan.xls"))) {
            HSSFWorkbook wb = new HSSFWorkbook(fis);
            HSSFSheet sheet = wb.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getCell(0).getStringCellValue().equals(courseName)) {
                    creditHours = (int) row.getCell(1).getNumericCellValue();
                    preRequisite = row.getCell(2).getStringCellValue();
                    coRequuisite = "None";
                    coRequuisite = row.getCell(3).getStringCellValue();
                }
            }

        } catch (IOException e) {

        }

    }

    public String toString() {
        return courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCoRequuisite() {
        return coRequuisite;
    }

    public void setCoRequuisite(String coRequuisite) {
        this.coRequuisite = coRequuisite;
    }

    public String getPreRequisite() {
        return preRequisite;
    }

    public void setPreRequisite(String preRequisite) {
        this.preRequisite = preRequisite;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

}