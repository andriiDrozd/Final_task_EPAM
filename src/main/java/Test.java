import com.example.final_task_epam.model.dao.implement.FacultyDaoImplement;
import com.example.final_task_epam.model.dao.implement.SubjectDaoImplement;
import com.example.final_task_epam.model.entity.Faculty;
import com.example.final_task_epam.model.entity.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class Test {
    public static void main(String[] args) {
        List<Faculty> faculties = FacultyDaoImplement.getAllFaculties();
        Set<Faculty> facultySet=new TreeSet<>(faculties);
        facultySet=new TreeSet<>(new Comparator<Faculty>() {
            @Override
            public int compare(Faculty o1, Faculty o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (Faculty c: facultySet) {
            System.out.println(c.toString());
        }
    };
}

