package dao;

import org.example.connection.DBConnection;
import org.example.dao.ProjectDAOImp;
import org.example.dao.ProjectManagerDAOImpl;
import org.example.model.Clients;
import org.example.model.Tasks;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectManagerDAOImpTest {
    static ProjectManagerDAOImpl projectManagerDAO;
    static Connection con;


    @BeforeAll
    public static void setUpBeforeClass() throws SQLException {
        projectManagerDAO = new ProjectManagerDAOImpl();
        con = DBConnection.getConnection();

    }

    @AfterAll
    public static void tearDownAfterClass() {
        DBConnection.closeConnection();
    }


//    @Test
//    public void testUpdateClient() {
//
//        Clients clients = new Clients();
//        clients.setClient_name("DummyName");
//        clients.setEmail("dummy22@gmail.com");
//        clients.setPhoneNumber("+918745231567");
//
//        boolean res = projectManagerDAO.updateClient(2,clients);
//
//        assertTrue(res);
//    }
//
//    @Test
//    public void testDeleteClient() {
//        boolean res = projectManagerDAO.deleteClient(2);
//        assertTrue(res);
//    }

//    @Test
//    public void testViewClientDetails() {
//        Clients clients = projectManagerDAO.viewClientDetails(1);
//        assertNotNull(clients);
//    }
//
//    @Test
//
//    public void testAddTeamMemberToProject(){
//        boolean res = projectManagerDAO.addTeamMemberToProject(3,8);
//        assertTrue(res);
//    }


//    @Test
//
//    public void testRemoveTeamMemberToProject() {
//        boolean res = projectManagerDAO.removeTeamMemberFromProject(3,8);
//        assertTrue(res);
//    }

//    @Test
//    public void TestAssignTaskToTeamMember(){
//        String query = "INSERT INTO tasks (project_id, start_date, end_date, task_name, user_id, description) VALUES (?, ?, ?, ?, ?, ?)";
//        Tasks task = new Tasks();
//        task.setProject_id(3);
//        task.setStart_date(new Date(2024-6-12));
//        task.setEnd_date(new Date(2024-7-7));
//        task.setTask_name("Dummy Task");
//        task.setUser_id(5);
//        task.setDescription("It is dummy task creation");
//        boolean res = projectManagerDAO.assignTaskToTeamMemberDummy(task);
//
//        assertTrue(res);
//
//    }

//    @Test
//    public void testUpdateTask() {
//        String query = "UPDATE tasks SET percentage = ?, milestone_id = ? WHERE project_id= ?";
//
//        Tasks task = new Tasks();
//        task.setPercentage(34.00);
//        task.setMilestone_id(2);
//        boolean res = projectManagerDAO.updateTask(3,task);
//        assertTrue(res);
//    }


//    @Test
//    public void testGetTaskBasedProjectId(){
//        List<Tasks>   res = projectManagerDAO.getTaskBasedProjectId(3);
//        assertNotNull(res);
//    }

//    @Test
//    public void testDeleteTask() {
//        boolean res = projectManagerDAO.deleteTask(3);
//        assertTrue(res);
//    }


//    @Test
//    public void testViewTaskDetails(){
//        List<Tasks> task = projectManagerDAO.viewTaskDetails(1);
//        assertNotNull(task);
//
//    }
}
