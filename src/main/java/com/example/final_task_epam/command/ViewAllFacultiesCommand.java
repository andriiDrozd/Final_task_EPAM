package com.example.final_task_epam.command;

import com.example.final_task_epam.model.dao.implement.FacultyDaoImplement;
import com.example.final_task_epam.model.entity.Faculty;
import com.example.final_task_epam.util.Fields;
import com.example.final_task_epam.util.Parameter;
import com.example.final_task_epam.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class ViewAllFacultiesCommand extends Command {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        String page = null;

        List<Faculty> faculties = FacultyDaoImplement.getAllFaculties();
//        Set<Faculty> facultySet=new TreeSet<>(faculties);
        if (!faculties.isEmpty()) {

//            page = Path.PAGE__VIEW__ALL__FACULTIES;
//            if(1 ==(Integer) request.getAttribute("type")) {
//                facultySet=new TreeSet<>(new Comparator<Faculty>() {
//                    @Override
//                    public int compare(Faculty o1, Faculty o2) {
//                        return o1.getName().compareTo(o2.getName());
//                    }
//                });
//                request.setAttribute(Parameter.LIST_OF_FACULTIES, facultySet);
//            } else if(2 ==(Integer) request.getAttribute("type")){
//                facultySet=new TreeSet<>(new Comparator<Faculty>() {
//                    @Override
//                    public int compare(Faculty o1, Faculty o2) {
//                        return o2.getName().compareTo(o1.getName());
//                    }
//                });
//                request.setAttribute(Parameter.LIST_OF_FACULTIES, facultySet);
//            } else if(3 ==(Integer) request.getAttribute("type")) {
//                facultySet=new TreeSet<>(new Comparator<Faculty>() {
//                    @Override
//                    public int compare(Faculty o1, Faculty o2) {
//                        return o1.getCapacity()-o2.getCapacity();
//                    }
//                });
//                request.setAttribute(Parameter.LIST_OF_FACULTIES, facultySet);
//            }else if(3 ==(Integer) request.getAttribute("type")) {
//                facultySet=new TreeSet<>(new Comparator<Faculty>() {
//                    @Override
//                    public int compare(Faculty o1, Faculty o2) {
//                        return o1.getBudgetPlaces()-o2.getBudgetPlaces();
//                    }
//                });
                request.setAttribute(Parameter.LIST_OF_FACULTIES, faculties);
            page = Path.PAGE__VIEW__ALL__FACULTIES;
//            }
        } else {

            page = Path.PAGE__VIEW__ALL__FACULTIES;
            request.setAttribute(Parameter.ERROR, "No any faculties avaliable now(");
        }

        return page;
    }
}
