package dao;

import org.example.connection.DBConnection;
import org.example.dao.AdminDAOImp;
import org.example.model.*;
import org.junit.jupiter.api.*;

import java.sql.*;

import static org.example.dao.AdminDAOImp.logger;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminDAOImpTest {

    static AdminDAOImp admin;
    static Connection con;

    @BeforeAll
    public void setUpBeforeClass() throws SQLException {
        admin = new AdminDAOImp();
        con = DBConnection.getConnection();

    }

    @AfterAll
    public void tearDownAfterClass() {
        DBConnection.closeConnection();
    }

    @BeforeEach
    public void setUp() throws SQLException {
        // Clean up before each test

    }

//    @Test
//    public void testCreateUser() {
//        Users user = new Users();
//        user.setUser_name("John Doe");
//        user.setUser_role(Role.ADMIN);
//        user.setEmail("joe.doe@gmail.com");
//        user.setPassword("password");
//        user.setPhone("1234567890");
//        user.setSpecilization("Java Developer");
//
//        Users result = admin.createUser(user);
//
//        assertNotNull(result);
//        assertEquals(user.getUser_name(), result.getUser_name());
//    }

//    @Test
//    public void testUpdateUser() {
//        Users user = new Users();
//        user.setUser_id(8);
//        user.setUser_name("John Doe Updated");
//        user.setUser_role(Role.TEAM_MEMBER);
//        user.setEmail("john.doe@example.com");
//        user.setPassword("newpassword");
//        user.setPhone("0987654321");
//        user.setSpecilization("Java Developer");
//        user.setStatus(Status.ACTIVE);
//        user.setManager_id(6);
//        user.setDate_of_joining(new Date(System.currentTimeMillis()));
//
//        try {
//            boolean result = admin.updateUser(user);
//            assertTrue(result);
//        } catch (RuntimeException ex) {
//            logger.error("Test failed: {}", ex.getMessage(), ex);
//            fail("Failed to update user: " + ex.getMessage());
//        }
//    }


//
//
//    @Test
//    public void testDeactivateUser() {
//        Users user = new Users();
//        user.setUser_id(8);
//        user.setStatus(Status.INACTIVE);
//
//        boolean result = admin.deactivateUser(user.getUser_id());
//
//        assertTrue(result);
//    }

//    @Test
//    public void testCreateClient() {
//        Clients client = new Clients();
//        client.setClient_name("Dummy");
//        client.setClient_company_name("dummy company name");
//        client.setEmail("dummy23@gmail.com");
//        client.setPhoneNumber("+919876560908");
//        client.setCreatedAt(new java.util.Date());
//
//        Clients result = admin.createClient(client);
//
//        assertNotNull(result);
//        assertEquals(client.getClient_name(), result.getClient_name());
//    }

//    @Test
//    public void testCreateProject() {
//        Projects project = new Projects();
//        Clients client = new Clients();
//        client.setClient_id(1);
//        project.setClients(client);
//        project.setProject_name("Project Name");
//        project.setDescription("Project Description");
//        project.setManager_id(1);
//        project.setPercentage_left(100);
//
//        Projects result = admin.createProject(project);
//
//        assertNotNull(result);
//        assertEquals(project.getProject_name(), result.getProject_name());
//    }
//
//    @Test
//    public void testTrackUserActivity() {
//
//        ResultSet rs = admin.trackUserActivity(1);
//
//        assertNotNull(rs);
//    }

    @Test
    public void testMonitorTaskCompletion() {

        ResultSet rs = admin.monitorTaskCompletion(1);

        assertNotNull(rs);
    }
}
