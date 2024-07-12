package dao;

import org.example.connection.DBConnection;
import org.example.dao.TaskDAOImp;
import org.example.dao.TeamMemberDAOImpl;
import org.example.model.Tasks;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TeamMemberDAOImpTest {


    static TeamMemberDAOImpl teamMemberDAO;
    static Connection con;


    @BeforeAll
    public static void setUpBeforeClass() throws SQLException {
        teamMemberDAO = new TeamMemberDAOImpl();
        con = DBConnection.getConnection();

    }

    @AfterAll
    public static void tearDownAfterClass() {
        DBConnection.closeConnection();
    }

    @Test
    public void testCreate() {
        boolean res = teamMemberDAO.create(2,5);
        assertTrue(res);

    }


    @Test
    public void testViewAssignedTasks(){
        List<Tasks> list = teamMemberDAO.viewAssignedTasks(6);
        assertEquals(2,list.size());

    }

    @Test
    public void testViewTaskDetails() {
        Tasks task = teamMemberDAO.viewTaskDetails(14);
        assertNotNull(task);
    }

    @Test
    public void testViewProjectTasks() {
        List<Tasks> list = teamMemberDAO.viewProjectTasks(3);
        assertEquals(1,list.size());
    }


    @Test
    public void testViewClientDetails() {
        ResultSet rs  = teamMemberDAO.viewClientDetail(1);
        assertNotNull(rs);
    }

    @Test
    public void testUpdateTaskStatus(){
        String query = "UPDATE tasks SET percentage = ?, milestone_id = ? WHERE task_id= ?";
        Tasks task = new Tasks();
        task.setPercentage(12);
        task.setMilestone_id(2);
        boolean res  = teamMemberDAO.updateTaskStatus(14,task);
        assertTrue(res);
    }

}
