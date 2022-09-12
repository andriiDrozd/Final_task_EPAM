package com.example.final_task_epam.filter;

import com.example.final_task_epam.role.UserRole;

public class AdminCommandFilter extends CommandAccessFilter {
    {
        exclusiveCommands ="adminCommands";
        userRole= UserRole.ADMIN;
        System.out.println("adminFilter");
//        logMessage="non-admin user tried to perform: ";

    }
}
