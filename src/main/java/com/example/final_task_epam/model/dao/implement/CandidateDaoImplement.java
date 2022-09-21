package com.example.final_task_epam.model.dao.implement;

import com.example.final_task_epam.configuration.DbManager;
import com.example.final_task_epam.model.entity.Faculty;
import com.example.final_task_epam.model.entity.Mark;
import com.example.final_task_epam.model.entity.Subject;
import com.example.final_task_epam.model.state.CandidateState;
import com.example.final_task_epam.model.dao.AbstractDao;
import com.example.final_task_epam.model.dao.CandidateDao;
import com.example.final_task_epam.model.entity.Candidate;
import com.example.final_task_epam.util.Fields;
import com.example.final_task_epam.util.Parameter;

import java.sql.*;
import java.util.*;

public class CandidateDaoImplement extends AbstractDao implements CandidateDao {

    private static final String INSERT_CANDIDATE = "INSERT INTO candidate (user_id, faculty_id, candidate_state_id) VALUES (?, ?, ?)";
    private static final String FIND_CANDIDATES_BY_USER_ID = "SELECT candidate.id as candidate_id, faculty.name," +
            "candidate_state.state_type FROM candidate JOIN candidate_state ON candidate.candidate_state_id=candidate_state.id " +
            " JOIN faculty ON candidate.faculty_id=faculty.id WHERE user_id=?";
    private static final String FIND_LATEST_CANDIDATES = "SELECT candidate.id as applicant_id, faculty.id as faculty_id, faculty.name, faculty.capacity, (SELECT sum(mark) \n" +
            "from subject_marks \n" +
            "WHERE candidate_id=candidate.id) as total_rating FROM candidate \n" +
            "JOIN user_table ON candidate.user_id=user_table.id JOIN faculty ON candidate.faculty_id=faculty.id;";
    private static final String UPDATE_ENROLLED_CANDIDATE_STATE = "UPDATE candidate SET candidate_state_id=2 WHERE id=?";
    private static final String UPDATE_NOT_ENROLLED_CANDIDATES_STATE = "UPDATE candidate SET candidate_state_id=3 WHERE candidate_state_id=1";
    private static final String FIND_ALL_CANDIDATES = "SELECT candidate.id as candidate_id,user_table.name,faculty.state as faculty_state, user_table.surname, faculty.name  as faculty_name,(SELECT sum(mark) \n" +
            "from subject_marks\n" +
            "WHERE candidate_id=candidate.id) as total_rating, candidate_state.state_type \n" +
            "FROM candidate\n" +
            "JOIN user_table ON candidate.user_id=user_table.id \n" +
            "JOIN faculty ON candidate.faculty_id=faculty.id\n" +
            "JOIN candidate_state ON candidate_state.id=candidate.candidate_state_id";


    private static final String DELETE_CANDIDATE_BY_ID = "DELETE FROM candidate WHERE id=?";
    private static final String FIND_CANDIDATE_BY_ID = "SELECT  user_table.id, user_table.name, user_table.surname, faculty.name as faculty_name,candidate.faculty_id, candidate_state.state_type\n" +
            "FROM candidate JOIN user_table ON candidate.user_id = user_table.id \n" +
            "JOIN faculty ON candidate.faculty_id=faculty.id\n" +
            "JOIN candidate_state ON candidate.candidate_state_id=candidate_state.id \n" +
            "WHERE candidate.id=?";

    private static final String CHANGE_CANDIDATE_STATE = "UPDATE  candidate SET candidate_state_id = ? WHERE candidate.id = ?";

    private static final String ARE_USER_APPLIED_TO_THE_FACULTY = "SELECT user_id, faculty_id FROM candidate where user_id=? and faculty_id=?";

    private static final String SELECT_CANDIDATES_BY_FACULTY_ID = "SELECT ROW_NUMBER() OVER(ORDER BY (SELECT sum(mark) \n" +
            "from subject_marks \n" +
            "WHERE candidate_id=candidate.id) desc) rating_position, user_table.name, user_table.surname,faculty.state, candidate.id as candidate_id, \n" +
            "faculty.name as faculty_name ,  faculty.id as faculty_id,  (SELECT sum(mark) \n" +
            "from subject_marks \n" +
            "WHERE candidate_id=candidate.id) as total_rating, candidate_state.state_type \n" +
            "FROM candidate\n" +
            "JOIN user_table ON candidate.user_id=user_table.id\n" +
            "JOIN faculty ON candidate.faculty_id=faculty.id\n" +
            "JOIN candidate_state ON candidate_state.id=candidate.candidate_state_id\n" +
            "WHERE faculty.id=?";

    private static final String SELECT_CANDIDATES_BY_USER_ID = "SELECT candidate.id as candidate_id,user_table.name,faculty.state, user_table.surname, faculty.name  as faculty_name,(SELECT sum(mark) \n" +
            "from subject_marks \n" +
            "WHERE candidate_id=candidate.id) as total_rating, candidate_state.state_type \n" +
            "FROM candidate\n" +
            "JOIN user_table ON candidate.user_id=user_table.id \n" +
            "JOIN faculty ON candidate.faculty_id=faculty.id\n" +
            "JOIN candidate_state ON candidate_state.id=candidate.candidate_state_id\n" +
            "WHERE user_table.id=?";

    private static final String SELECT_ENROLLED_CANDIDATES_DY_FACULTY_ID = "SELECT ROW_NUMBER() OVER(ORDER BY (SELECT sum(mark) \n" +
            "from subject_marks \n" +
            "WHERE candidate_id=candidate.id) desc) rating_position, user_table.name, user_table.surname,faculty.state, candidate.id as candidate_id, \n" +
            "faculty.name as faculty_name ,  faculty.id as faculty_id,  (SELECT sum(mark) \n" +
            "from subject_marks \n" +
            "WHERE candidate_id=candidate.id) as total_rating, candidate_state.state_type\n" +
            "\n" +
            "FROM candidate\n" +
            "JOIN user_table ON candidate.user_id=user_table.id\n" +
            "JOIN faculty ON candidate.faculty_id=faculty.id\n" +
            "JOIN candidate_state ON candidate_state.id=candidate.candidate_state_id\n" +
            "where state_type='enrolled' AND faculty.id=?";

    private static final String GET_CANDIDATE_FACULTY_NAME_AND_USER_ID = "SELECT candidate.id as candidate_id,user_table.name,faculty.state, user_table.surname, faculty.name  as faculty_name,(SELECT sum(mark) \n" +
            "from subject_marks \n" +
            "WHERE candidate_id=candidate.id) as total_rating, candidate_state.state_type \n" +
            "FROM candidate\n" +
            "JOIN user_table ON candidate.user_id=user_table.id \n" +
            "JOIN faculty ON candidate.faculty_id=faculty.id\n" +
            "JOIN candidate_state ON candidate_state.id=candidate.candidate_state_id\n" +
            "WHERE user_table.id=? AND faculty.name=?";

    private static final String FIND_ALL_CANDIDATES_PAGINATION = "SELECT candidate.id as candidate_id,user_table.name,faculty.state as faculty_state, user_table.surname, faculty.name  as faculty_name,(SELECT sum(mark)\n" +
            "from subject_marks\n" +
            "WHERE candidate_id=candidate.id) as total_rating, candidate_state.state_type\n" +
            "FROM candidate\n" +
            "JOIN user_table ON candidate.user_id=user_table.id \n" +
            "JOIN faculty ON candidate.faculty_id=faculty.id\n" +
            "JOIN candidate_state ON candidate_state.id=candidate.candidate_state_id\n" +
            "LIMIT ? OFFSET ?";

    private static final String COUNT_CANDIDATES = "SELECT COUNT (*) FROM candidate";

    public static int count_candidates() {
        Connection connection = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = DbManager.getInstance().getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(COUNT_CANDIDATES);
            while(resultSet.next()){
                count = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public static Set<Candidate> find_candidates_pagination (int limit, int offset) {
        Connection connection = null;
        ResultSet resultSet = null;
        Set<Candidate> candidates = null;
        try {
            connection = DbManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_CANDIDATES_PAGINATION);
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);

            resultSet = preparedStatement.executeQuery();

            candidates = new TreeSet<>();
            Candidate candidate;
            while (resultSet.next()) {
                candidate = new Candidate();
                candidate.setId(resultSet.getInt(Fields.CANDIDATE_ID));
                candidate.setName(resultSet.getString(Fields.NAME));
                candidate.setSurname(resultSet.getString(Fields.SURNAME));
                candidate.setFacultyName(resultSet.getString(Fields.FACULTY_NAME));
                candidate.setTotalRating(resultSet.getInt(Fields.TOTAL_RATING));
                candidate.setCandidateState(CandidateState.valueOf(resultSet.getString(Fields.STATE_TYPE).toUpperCase()));
                candidate.setFacultyState(resultSet.getString(Fields.FACULTY_STATE));
                candidates.add(candidate);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return candidates;
    }

    public static Candidate getCandidateByFacultyNameAndUserId(String facultyName, int UserID) {
        Connection connection = null;
        try {
            connection = DbManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CANDIDATE_FACULTY_NAME_AND_USER_ID);
            preparedStatement.setInt(1, UserID);
            preparedStatement.setString(2, facultyName);

            ResultSet resultSet = preparedStatement.executeQuery();
            Candidate candidate = null;

            if (resultSet.next()) {
                candidate = new Candidate();
                candidate.setId(resultSet.getInt(Fields.CANDIDATE_ID));
                candidate.setName(resultSet.getString(Fields.NAME));
                candidate.setSurname(resultSet.getString(Fields.SURNAME));
                candidate.setFacultyName(resultSet.getString(Fields.FACULTY_NAME));
            }
            return candidate;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static TreeSet<Candidate> selectCandidatesByFacultyId(int facultyId) {
        Connection connection = null;
        ResultSet resultSet = null;
        TreeSet<Candidate> facultyCandidates = null;
        try {
            connection = DbManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CANDIDATES_BY_FACULTY_ID);
            preparedStatement.setInt(1, facultyId);
            resultSet = preparedStatement.executeQuery();

            TreeSet<Candidate> candidates = new TreeSet<>();
            Candidate candidate;
            while (resultSet.next()) {
                candidate = new Candidate();
                candidate.setId(resultSet.getInt(Fields.CANDIDATE_ID));
                candidate.setName(resultSet.getString(Fields.NAME));
                candidate.setSurname(resultSet.getString(Fields.SURNAME));
                candidate.setFacultyName(resultSet.getString(Fields.FACULTY_NAME));
                candidate.setTotalRating(resultSet.getInt(Fields.TOTAL_RATING));
                candidate.setCandidateState(CandidateState.valueOf(resultSet.getString(Fields.STATE_TYPE).toUpperCase()));
                candidate.setFacultyState(resultSet.getString("state"));
                candidate.setRating_position(resultSet.getInt("rating_position"));
                candidates.add(candidate);
                facultyCandidates = candidates;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return facultyCandidates;
    }

    public static Set<Candidate> getCandidatesByUserID(int userId) {
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = DbManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CANDIDATES_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            Set<Candidate> candidates = new TreeSet<>();
            Candidate candidate;
            while (resultSet.next()) {
                candidate = new Candidate();
                candidate.setUserId(userId);
                candidate.setId(resultSet.getInt(Fields.CANDIDATE_ID));
                candidate.setName(resultSet.getString(Fields.NAME));
                candidate.setSurname(resultSet.getString(Fields.SURNAME));
                candidate.setTotalRating(resultSet.getInt(Fields.TOTAL_RATING));
                candidate.setFacultyName(resultSet.getString(Fields.FACULTY_NAME));
                candidate.setFacultyState(String.valueOf(resultSet.getInt("state")));
                candidate.setCandidateState(CandidateState.valueOf(resultSet.getString(Fields.STATE_TYPE).toUpperCase()));
                candidates.add(candidate);
            }
            return candidates;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static TreeSet<Candidate> selectEnrolledCandidatesByFacultyId(int facultyId) {
        Connection connection = null;
        ResultSet resultSet = null;
        TreeSet<Candidate> facultyCandidates = null;
        try {
            connection = DbManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ENROLLED_CANDIDATES_DY_FACULTY_ID);
            preparedStatement.setInt(1, facultyId);
            resultSet = preparedStatement.executeQuery();

            TreeSet<Candidate> candidates = new TreeSet<>();
            Candidate candidate;
            while (resultSet.next()) {
                candidate = new Candidate();
                candidate.setId(resultSet.getInt(Fields.CANDIDATE_ID));
                candidate.setName(resultSet.getString(Fields.NAME));
                candidate.setSurname(resultSet.getString(Fields.SURNAME));
                candidate.setFacultyName(resultSet.getString(Fields.FACULTY_NAME));
                candidate.setTotalRating(resultSet.getInt(Fields.TOTAL_RATING));
                candidate.setCandidateState(CandidateState.valueOf(resultSet.getString(Fields.STATE_TYPE).toUpperCase()));
                candidate.setFacultyState(resultSet.getString("state"));
                candidate.setRating_position(resultSet.getInt("rating_position"));
                candidates.add(candidate);
                facultyCandidates = candidates;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return facultyCandidates;
    }

    public static void changeCandidateState(int stateId, int candidateId) {
        Connection connection = null;

        try {
            connection = DbManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_CANDIDATE_STATE);
            preparedStatement.setInt(1, stateId);
            preparedStatement.setInt(2, candidateId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean areUserAppliedToTheFaculty(int user_id, int faculty_id) {
        boolean result = false;
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            connection = DbManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ARE_USER_APPLIED_TO_THE_FACULTY);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, faculty_id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public static TreeSet<Candidate> getAllCandidates() {
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = DbManager.getInstance().getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_CANDIDATES);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            TreeSet<Candidate> candidates = new TreeSet<>();
            Candidate candidate;
            while (resultSet.next()) {
                candidate = new Candidate();
                candidate.setId(resultSet.getInt(Fields.CANDIDATE_ID));
                candidate.setName(resultSet.getString(Fields.NAME));
                candidate.setSurname(resultSet.getString(Fields.SURNAME));
                candidate.setFacultyName(resultSet.getString(Fields.FACULTY_NAME));
                candidate.setTotalRating(resultSet.getInt(Fields.TOTAL_RATING));
                candidate.setCandidateState(CandidateState.valueOf(resultSet.getString(Fields.STATE_TYPE).toUpperCase()));
                candidate.setFacultyState(resultSet.getString(Fields.FACULTY_STATE));
                candidates.add(candidate);
            }
            return candidates;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbManager.getInstance().commitAndClose(connection);
        }
    }

    @Override
    public void updateEnrolledCandidatesState(Connection connection, List<Integer> idList) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(UPDATE_ENROLLED_CANDIDATE_STATE);
            for (int id : idList) {
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateNotEnrolledCandidatesState(Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NOT_ENROLLED_CANDIDATES_STATE);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Map<Faculty, TreeSet<Candidate>> getCurrentEnrollmentCandidates(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_LATEST_CANDIDATES);
            Map<Faculty, TreeSet<Candidate>> candidates = new HashMap<>();
            Faculty faculty;
            Candidate candidate;
            while (resultSet.next()) {
                faculty = new Faculty();
                faculty.setId(resultSet.getInt(Fields.FACULTY_ID));
                faculty.setCapacity(resultSet.getInt(Fields.CAPACITY));

                candidate = new Candidate();
                candidate.setId(resultSet.getInt(Fields.CANDIDATE_ID));
                candidate.setTotalRating(resultSet.getInt(Fields.TOTAL_RATING));

                if (candidates.containsKey(faculty)) {
                    candidates.get(faculty).add(candidate);
                } else {
                    candidates.put(faculty, new TreeSet<>());
                    candidates.get(faculty).add(candidate);
                }
            }
            return candidates;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public static List<Candidate> findCandidatesByUserId(int userId) {
        Connection connection = null;
        try {
            connection = DbManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CANDIDATES_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Candidate> candidates = new ArrayList<>();
            Candidate candidate;
            while (resultSet.next()) {
                candidate = new Candidate();
                candidate.setUserId(userId);
                candidate.setId(resultSet.getInt(Fields.CANDIDATE_ID));
                candidate.setFacultyName(resultSet.getString(Fields.NAME));
                candidate.setCandidateState(CandidateState.valueOf(resultSet.getString(Fields.STATE_TYPE).toUpperCase()));
                candidates.add(candidate);
            }
            return candidates;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Candidate> getCandidatesByUserId(int userId) {
//        try {
//           Connection connection= DbManager.getInstance().getConnection();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            return findCandidatesByUserId(connection, userId);
//        } finally {
//            DbManager.getInstance().commitAndClose(conn);
//        }
        return null;
    }

    @Override
    public List<Candidate> getCandidatesByUserId(Connection connection, int userId) {
        return findCandidatesByUserId(userId);
    }


    public static int insert(Connection connection, Candidate candidate) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CANDIDATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, candidate.getUserId());
            System.out.println("Id=" + candidate.getUserId());
            preparedStatement.setInt(2, candidate.getFacultyId());
            preparedStatement.setInt(3, candidate.getCandidateState().getOrdinalNumber());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            int id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Candidate getCandidateById(int candidateId) {
        Connection connection = null;
        try {
            connection = DbManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CANDIDATE_BY_ID);
            preparedStatement.setInt(1, candidateId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Candidate candidate = null;

            if (resultSet.next()) {
                candidate = new Candidate();
                candidate.setUserId(resultSet.getInt(Fields.ID));
                candidate.setId(candidateId);
                candidate.setFacultyId(Integer.parseInt(resultSet.getString(Fields.FACULTY_ID)));
                candidate.setName(resultSet.getString(Fields.NAME));
                candidate.setSurname(resultSet.getString(Fields.SURNAME));
                candidate.setFacultyName(resultSet.getString(Fields.FACULTY_NAME));
                candidate.setCandidateState(CandidateState.valueOf(resultSet.getString(Fields.STATE_TYPE).toUpperCase()));
                List<Subject> subjects = SubjectDaoImplement.getSubjectMarksByCandidateId(candidateId);
                System.out.println(candidate.getFacultyId());
                candidate.setSubjects(subjects);
                System.out.println("subjects-" + subjects);
            }
            return candidate;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public void deleteCandidateById(Connection connection, int candidateId) {

    }

    public static void deleteCandidateById(int candidateId)
//            throws candidateDaoException
    {
        Connection connection = null;
        try {
            connection = DbManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CANDIDATE_BY_ID);
            preparedStatement.setInt(1, candidateId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException();
//            throw new candidateDaoException("could not delete candidate by id:" + e.getMessage());
        }
    }

//    public static  List<Candidate> getCandidatesIdByFacultyId( int facultyId)
////            throws candidateDaoException
//    {
//        Connection connection=null;
//        try {
//            connection=DbManager.getInstance().getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CANDIDATES_ID_BY_FACULTY_ID);
//            preparedStatement.setInt(1, facultyId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            List<Candidate> candidates = new ArrayList<>();
//            Candidate candidate;
//            while (resultSet.next()) {
//                candidate = new Candidate();
//                candidate.setId(resultSet.getInt(Fields.ID));
//                candidate.setFacultyName(resultSet.getString(Fields.NAME));
//                candidates.add(candidate);
//            }
//            return candidates;
//        } catch (SQLException e) {
//            throw new RuntimeException();
////            throw new candidateDaoException("could not get candidates id by faculty id: " + e.getMessage());
//        }
//    }


    public static boolean addApplicant(Candidate newCandidate, String[] marks) {
        Connection connection = null;
        try {
            connection = DbManager.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            connection.setAutoCommit(false);
            try {
//                connection = DbManager.getInstance().getConnection();
//                if (!isAlreadyApplied(connection, newCandidate)) {
                List<Subject> requiredSubjects = SubjectDaoImplement.getRequiredSubjects(newCandidate.getFacultyId());
                newCandidate.setId(CandidateDaoImplement.insert(connection, newCandidate));

                List<Mark> newApplicantMarks = convertStringsToMarks(marks, requiredSubjects, newCandidate);
                SubjectDaoImplement.insertMarks(connection, newApplicantMarks);
//                    subjectDao.insertGrades(connection, newApplicantGrades);
                connection.commit();
                return true;
//                } else {

//                }
//            } catch (ApplicantDaoException | SubjectDaoException e) {
//                connection.rollback();
//                throw new DaoException(e.getMessage());
//            } finally {
//                connection.setAutoCommit(true);
//            }
//        } catch (DaoException | SQLException ex) {
//            logger.error(ex.getMessage());
//            throw new ApplicantServiceException(ex.getMessage());
            } finally {
                DbManager.getInstance().commitAndClose(connection);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Mark> convertStringsToMarks(String[] gradesStrings, List<Subject> requiredSubjects, Candidate newCandidate) {

        List<Mark> candidateMark = new ArrayList<>();
        Mark mark;
        for (int i = 0; i < gradesStrings.length; i++) {
            mark = new Mark();
            mark.setMark(gradesStrings[i]);
            mark.setCandidateId(newCandidate.getId());
            mark.setSubjectId(requiredSubjects.get(i).getSubjectId());
            candidateMark.add(mark);
        }
        return candidateMark;
    }


//        private boolean isAlreadyApplied(Connection connection, Candidate newCandidate) {
//        try {
//            List<Candidate>candidates = CandidateDaoImplement.findCandidatesByUserId(connection, newCandidate.getUserId());
//
//            for (Candidate candidate : candidates) {
//                if (candidate.getCandidateState() == CandidateState.APPLIED) {
//                    return true;
//                }
//            }
//        } catch (ApplicantDaoException e) {
//            logger.error(e.getMessage());
//            throw new ApplicantServiceException(e.getMessage());
//        }
//        return false;
//    }

}
