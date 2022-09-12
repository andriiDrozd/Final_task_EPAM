package com.example.final_task_epam.controller;

import com.example.final_task_epam.command.*;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        // common commands
        commands.put("login", new LoginCommand());
        commands.put("registry", new RegistryUserCommand());
        commands.put("add_subject", new AddSubjectCommand());
        commands.put("view_subject", new ViewSubjectCommand());
        commands.put("add_faculty", new AddFacultyCommand());
        commands.put("view_all_faculties", new ViewAllFacultiesCommand());
        commands.put("apply", new ApplyCommand());
        commands.put("apply_to_faculty", new ApplyToFacultyCommand());
        commands.put("view_all_candidates", new ViewAllCandidatesCommand());
        commands.put("view_candidate", new ViewCandidateCommand());
        commands.put("change_candidate_state_to_enroll", new ChangeCandidateStateToEnroll());
        commands.put("view_statement_of_faculty", new ViewStatementOfFaculty());
        commands.put("close_faculty", new CloseFacultyCommand());
        commands.put("delete_faculty", new DeleteFacultyCommand());
        commands.put("choose_language", new ChooseLanguage());
        commands.put("error_page", new ErrorCommand());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }
}

