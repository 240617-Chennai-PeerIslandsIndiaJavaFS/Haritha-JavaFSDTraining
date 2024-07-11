package dao;

import org.example.connection.DBConnection;
import org.example.dao.UserDAOImplementation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDAOImplementaionTest {
    static UserDAOImplementation userDAOImplementation;
    static Connection con;


    @BeforeAll
    public static void setUpBeforeClass() throws SQLException {
        userDAOImplementation = new UserDAOImplementation();
        con = DBConnection.getConnection();

    }

    @AfterAll
    public static void tearDownAfterClass() {
        DBConnection.closeConnection();
    }

//    @Test
//    public void testCreate() {
//        String query = "INSERT INTO users(user_name, user_role, email, password, phone, manager_id, status, specilization, date_of_joining) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        Users user = new Users();
//        user.setUser_name("RahulDummy");
//        user.setUser_role(Role.TEAM_MEMBER);
//        user.setEmail("rahul23@gmail.com");
//        user.setPassword("Rahul@234");
//        user.setPhone("+919876542314");
//        user.setManager_id(2);
//        user.setStatus(Status.ACTIVE);
//        user.setSpecilization("Backend");
//        user.setDate_of_joining(new Date(2024-6-11));
//        Users us = userDAOImplementation.create(user);
//        assertNotNull(us);
//
//    }


//    @Test
//    public void testDelete() {
//        boolean res = userDAOImplementation.delete(17);
//        assertTrue(res);
//    }

//    @Test
//    public void testUpdate() {
//        String query = "UPDATE users SET user_name = ?, user_role = ?, email = ?, password = ?, phone = ?, manager_id = ?, status = ?, specilization = ?, date_of_joining = ? WHERE user_id = ?";
//
//        Users users = new Users();
//        users.setUser_name("Radhika");
//        users.setUser_role(Role.TEAM_MEMBER);
//        users.setEmail("radhika11@gmail.com");
//        users.setPassword("Radhika@11");
//        users.setPhone("+919809452367");
//        users.setManager_id(2);
//        users.setStatus(Status.ACTIVE);
//        users.setSpecilization("Frontend");
//        users.setDate_of_joining(new Date(2024-9-11));
//        users.setUser_id(17);
//        boolean res = userDAOImplementation.update(users);
//        assertTrue(res);
//
//    }

//    @Test
//    public void testGetUserById() {
//        Users user = userDAOImplementation.getUserById(5);
//        assertNotNull(user);
//    }

//    @Test
//    public void testGetAll() {
//        List<Users> list = userDAOImplementation.getAll();
//        assertEquals(8,list.size());
//    }


}
