package dao;

import org.example.connection.DBConnection;
import org.example.dao.TeamsDAOImpl;
import org.example.dao.TimeStampDAOImp;
import org.example.model.TimeStamps;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TimeStampsDAOImpTest {
    static TimeStampDAOImp timeStampDAOImp;
    static Connection con;


    @BeforeAll
    public static void setUpBeforeClass() throws SQLException {
        timeStampDAOImp = new TimeStampDAOImp();
        con = DBConnection.getConnection();

    }

    @AfterAll
    public static void tearDownAfterClass() {
        DBConnection.closeConnection();
    }

//    @Test
//    public void testCreate() {
//        boolean res = timeStampDAOImp.create(4);
//        assertTrue(res);
//    }


//    @Test
//    public void testDelete(){
//        boolean res = timeStampDAOImp.delete(6);
//        assertTrue(res);
//    }

//    @Test
//    public void testUpdate() {
//        String query = "UPDATE time_stamps SET milestone_id = ? WHERE task_id = ?";
//        boolean res = timeStampDAOImp.update(4,2);
//        assertTrue(res);
//    }

//    @Test
//    public void testGetId() {
//        TimeStamps timeStamps = timeStampDAOImp.getId(5);
//        assertNotNull(timeStamps);
//    }

//    @Test
//    public void testGetAll() {
//        List<TimeStamps> list = timeStampDAOImp.getAll();
//        assertEquals(4,list.size());
//    }


//    @Test
//    public void testGetAllTimeStampBasedTaskId() {
//        List<TimeStamps> list = timeStampDAOImp.getAllTimeStampBasedTaskId(4);
//        assertEquals(1,list.size());
//    }


}
