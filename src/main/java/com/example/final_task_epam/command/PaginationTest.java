package com.example.final_task_epam.command;

import com.example.final_task_epam.model.dao.implement.CandidateDaoImplement;
import com.example.final_task_epam.model.entity.Candidate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

public class PaginationTest extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        String page = null;
        Set<Candidate> candidates = null;
        int count = CandidateDaoImplement.count_candidates();
        request.setAttribute("count", count);

        int limit =2 ;
        request.setAttribute("limit", limit);
        int offset = 0;
        if(request.getParameter("start")==null){
            offset=0;

        }else {
            offset = Integer.parseInt(request.getParameter("start")) * limit;
        }
        candidates=CandidateDaoImplement.find_candidates_pagination(limit,offset);
        request.setAttribute("candidates", candidates);
//        while (offset <= count) {
//            candidates = CandidateDaoImplement.find_candidates_pagination(3, 0);
//            offset = +limit;
//        }
        request.setAttribute("pagination", candidates);
        page = "/jsp/pagination_test.jsp";
        return page;
    }
}
