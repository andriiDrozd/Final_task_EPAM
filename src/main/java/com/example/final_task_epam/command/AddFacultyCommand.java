package com.example.final_task_epam.command;

import com.example.final_task_epam.Messages;
import com.example.final_task_epam.model.dao.implement.FacultyDaoImplement;
import com.example.final_task_epam.model.dao.implement.SubjectDaoImplement;
import com.example.final_task_epam.model.entity.Faculty;
import com.example.final_task_epam.model.entity.Subject;
import com.example.final_task_epam.model.validator.FacultyValidator;
import com.example.final_task_epam.util.Parameter;
import com.example.final_task_epam.util.Path;
import com.sun.source.tree.ParenthesizedPatternTree;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddFacultyCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {

        HttpSession session = request.getSession();
        String page = Path.PAGE__ADMIN__MENU;

        String facultyName = request.getParameter(Parameter.FACULTY_NAME);
        String[] subjectsIdStrings = request.getParameterValues(Parameter.SUBJECT_ID);
        String capacityString = request.getParameter(Parameter.CAPACITY);
        String budgetPlaces = request.getParameter(Parameter.BUDGET_PLACES);
        String state = request.getParameter(Parameter.STATE);
        int capacity = Integer.parseInt(capacityString);
        int budget=Integer.parseInt(budgetPlaces);
        if(FacultyValidator.validateSubjectName(facultyName)){
            request.setAttribute("facultyName", facultyName);
            if(FacultyValidator.validateCapacity(capacityString)&& FacultyValidator.validateCapacity(budgetPlaces) && capacity>=budget ) {
                if(subjectsIdStrings!=null) {
                    int state1 = 0;
                    if (state.equals("open")) {
                        state1 = 1;
                    } else if (state.equals("closed")) {
                        state1 = 2;
                    }
                    List<Subject> requiredSubjects = new ArrayList<>();
                    Subject subject;
                    for (String idString : subjectsIdStrings) {
                        subject = new Subject();
                        subject.setSubjectId(Integer.parseInt(idString));
                        requiredSubjects.add(subject);
                    }

                    Faculty faculty = new Faculty();
                    faculty.setName(facultyName);
                    faculty.setCapacity(Integer.parseInt(capacityString));
                    faculty.setRequiredSubjects(requiredSubjects);
                    faculty.setBudgetPlaces(Integer.parseInt(budgetPlaces));
                    faculty.setState(state1);
                    int facultyId = FacultyDaoImplement.insert(faculty);
                    SubjectDaoImplement.insertRequiredSubjects(facultyId, requiredSubjects);
                }else{
                    request.setAttribute("subject_error", Messages.NULL_SUBJECT_ERROR);
                    page=Path.PAGE__ADD_FACULTY;
                }
            }else{
                request.setAttribute("capacity_error", Messages.FACULTY_AND_BUDGET_CAPACITY_ERROR);
                page=Path.PAGE__ADD_FACULTY;
            }
        } else {
            request.setAttribute("faculty_error", Messages.FACULTY_AND_BUDGET_NAMES);
            page=Path.PAGE__ADD_FACULTY;
        }
        return page;
    }
}

