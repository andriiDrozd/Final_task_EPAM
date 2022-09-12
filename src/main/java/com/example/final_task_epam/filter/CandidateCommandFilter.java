package com.example.final_task_epam.filter;

import com.example.final_task_epam.role.UserRole;

import javax.servlet.Filter;

public class CandidateCommandFilter extends CommandAccessFilter implements Filter {
    {
        exclusiveCommands ="candidateCommands";
        userRole=UserRole.CANDIDATE;
        System.out.println("candidateFilter");
//        logMessage="non-applicant user tried to perform: ";


    }

}
