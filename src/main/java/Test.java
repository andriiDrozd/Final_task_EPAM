import com.example.final_task_epam.model.dao.implement.FacultyDaoImplement;
import com.example.final_task_epam.model.dao.implement.SubjectDaoImplement;
import com.example.final_task_epam.model.entity.Faculty;
import com.example.final_task_epam.model.entity.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class Test {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String password="1";
        MessageDigest md=MessageDigest.getInstance("SHA-512");
        md.update(password.getBytes());

        byte[] digest=md.digest();
        StringBuffer sb=new StringBuffer();
        for(byte b:digest) {
            sb.append(String.format("%02x", b & 0xff));
                  }
        System.out.println(sb.toString());

    }
}

