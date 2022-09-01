import com.example.final_task_epam.model.dao.implement.SubjectDaoImplement;
import com.example.final_task_epam.model.entity.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class Test {
    public static void main(String[] args) {
        List<Subject>subjects=SubjectDaoImplement.getRequiredSubjects(2);
        for (Subject s:subjects) {
            System.out.println(s.getName());
        }
    }
}
