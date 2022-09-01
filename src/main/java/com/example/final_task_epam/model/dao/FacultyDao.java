package com.example.final_task_epam.model.dao;

import com.example.final_task_epam.model.entity.Faculty;

import java.sql.Connection;
import java.util.List;

public interface FacultyDao {


    static List<Faculty> getAllFaculties() {
        return null;
    }


    static Faculty getById(Connection connection, int facultyId) {
        return null;
    }


    static int insert(Connection connection, Faculty faculty) {
        return 0;
    }

}
