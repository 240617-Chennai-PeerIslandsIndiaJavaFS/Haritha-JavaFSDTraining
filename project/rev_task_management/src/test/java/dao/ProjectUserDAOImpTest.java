package dao;

import org.example.connection.DBConnection;
import org.example.dao.ProjectManagerDAOImpl;
import org.example.dao.ProjectUserDAOImp;
import org.example.model.ProjectUser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectUserDAOImpTest {
    static ProjectUserDAOImp projectUserDAOImp;
    static Connection con;


    @BeforeAll
    public static void setUpBeforeClass() throws SQLException {
        projectUserDAOImp = new ProjectUserDAOImp();
        con = DBConnection.getConnection();

    }

    @AfterAll
    public static void tearDownAfterClass() {
        DBConnection.closeConnection();
    }

    @Test
    public void TestCreate() {
        String query = "INSERT INTO project_users(user_id, project_id ) VALUES (?, ?, ?, ?)";

        ProjectUser projectUser = new ProjectUser();
        projectUser.setUser_id(8);
        projectUser.setProject_id(1);
        ProjectUser projectUser1 = projectUserDAOImp.create(projectUser);
        assertNotNull(projectUser1);
    }

    @Test
    public void TestDelete(){
        boolean res = projectUserDAOImp.delete(6);
        assertTrue(res);
    }

    @Test
    public void TestGetId(){
        ProjectUser projectUser = projectUserDAOImp.getId(2);
        assertNotNull(projectUser);
    }


    @Test
    public void TestGetAll(){

        List<ProjectUser> list = projectUserDAOImp.getAll();
        assertEquals(4,list.size());
    }

}
