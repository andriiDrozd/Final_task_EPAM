package com.example.final_task_epam.command;

import com.example.final_task_epam.model.dao.implement.CandidateDaoImplement;
import com.example.final_task_epam.model.entity.Candidate;
import com.example.final_task_epam.model.state.CandidateState;
import com.example.final_task_epam.util.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ChangeCandidateStateToEnroll extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        String page = null;

        int candidateId=Integer.parseInt(request.getParameter(Parameter.CANDIDATE_ID));
        int candidateState = Integer.parseInt(request.getParameter(Parameter.CANDIDATE_STATE));

        CandidateDaoImplement.changeCandidateState(candidateState,candidateId);
        page="/ControllerServlet?command=view_candidate&id="+candidateId;


        return page;
    }
}
