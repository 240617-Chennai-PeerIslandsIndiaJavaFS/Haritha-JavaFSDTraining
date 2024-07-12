package dao;

import org.example.connection.DBConnection;
import org.example.dao.ProjectDAOImp;
import org.example.dao.ProjectManagerDAOImpl;
import org.example.model.Projects;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectDAOImpTest {

    static ProjectDAOImp projectDAOImp;
    static Connection con;


    @BeforeAll
    public static void setUpBeforeClass() throws SQLException {
        projectDAOImp = new ProjectDAOImp();
        con = DBConnection.getConnection();

    }

    @AfterAll
    public static void tearDownAfterClass() {
        DBConnection.closeConnection();
    }

    @Test
    public void testCreate() {
        Projects projects = new Projects();
        projects.setClient_id(1);
        projects.setProject_name("dummy");
        projects.setDescription("It is some dummy project");
        projects.setStart_date(new Date(2024-06-19));
        projects.setEnd_date(new Date(2024-07-11));
        projects.setPercentage_left(12);
        projects.setManager_id(2);
        projects.setCreated_at(Timestamp.from(Instant.now()));
        projects.setUpdated_at(Timestamp.from(Instant.now()));

        Projects result = projectDAOImp.create(projects);
        assertNotNull(result);

    }

    @Test

    public  void testDelete(){
        boolean val = projectDAOImp.delete(4);
        assertTrue(val);

    }

    @Test
    public void testUpdate() {
        String query = "UPDATE projects SET client_id = ?, project_name = ?, description = ?, start_date = ?, end_date = ?, percentage_left = ?, manager_id = ?, updated_at = ? WHERE project_id = ?";

        Projects project = new Projects();
        project.setProject_name("Dummy2");
        project.setClient_id(1);
        project.setProject_id(3);
        project.setDescription("Some dummy project");
        project.setStart_date(new Date(2024-12-22));
        project.setEnd_date(new Date(2024-12-25));
        project.setPercentage_left(0);
        project.setManager_id(2);
        project.setUpdated_at(Timestamp.from(Instant.now()));

        boolean res = projectDAOImp.update(project);
        assertTrue(res);
    }

    @Test
    public void testGetId() {
        Projects res = projectDAOImp.getId(3);
        assertNotNull(res);
    }



    @Test
    public void testGetAll() {
        List<Projects> list = projectDAOImp.getAll();
        assertEquals(3,list.size());
    }
}
