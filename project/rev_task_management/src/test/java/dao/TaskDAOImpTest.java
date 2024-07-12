package dao;

import org.example.connection.DBConnection;
import org.example.dao.ProjectUserDAOImp;
import org.example.dao.TaskDAOImp;
import org.example.model.Tasks;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskDAOImpTest {

    static TaskDAOImp taskDAOImp;
    static Connection con;


    @BeforeAll
    public static void setUpBeforeClass() throws SQLException {
        taskDAOImp = new TaskDAOImp();
        con = DBConnection.getConnection();

    }

    @AfterAll
    public static void tearDownAfterClass() {
        DBConnection.closeConnection();
    }

    @Test
    public void testCreate() {
        String query = "INSERT INTO tasks(project_id, start_date, end_date, task_name, percentage, milestone_id, user_id, description, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Tasks task = new Tasks();
        task.setProject_id(3);
        task.setStart_date(new Date(2024-7-12));
        task.setEnd_date(new Date(2024-8-22));
        task.setTask_name("dummy task");
        task.setPercentage(0);
        task.setMilestone_id(2);
        task.setUser_id(5);
        task.setDescription("dummy creation");
        task.setCreatedAt(Timestamp.from(Instant.now()));
        task.setUpdatedAt(Timestamp.from(Instant.now()));

        Tasks res = taskDAOImp.create(task);

        assertNotNull(res);

    }

    @Test
    public void testDelete() {
        boolean res = taskDAOImp.delete(13);
        assertTrue(res);
    }


    @Test
    public void testUpdate(){
        String query = "UPDATE tasks SET project_id = ?, start_date = ?, end_date = ?, task_name = ?, percentage = ?, milestone_id = ?, user_id = ?, description = ?, updated_at = ? WHERE task_id = ?";

        Tasks task = new Tasks();
        task.setProject_id(3);
        task.setStart_date( new Date(2024-5-22));
        task.setEnd_date(new Date(2024-6-11));
        task.setTask_name("Dummytask");
        task.setPercentage(11);
        task.setMilestone_id(2);
        task.setUser_id(6);
        task.setDescription("change to dummy");
        task.setUpdatedAt(Timestamp.from(Instant.now()));
        task.setTask_id(14);

        boolean res = taskDAOImp.update(task);
        assertTrue(res);

    }


    @Test
    public void testGetId() {
        Tasks res = taskDAOImp.getId(14);
        assertNotNull(res);
    }


    @Test
    public void testGetAll() {
        List<Tasks> list = taskDAOImp.getAll();
        assertEquals(5,list.size());
    }
}
