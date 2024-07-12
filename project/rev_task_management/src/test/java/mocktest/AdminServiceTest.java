package mocktest;

import org.example.dao.AdminDAOImp;
import org.example.dao.UserDAOImplementation;
import org.example.model.*;
import org.example.service.AdminService1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AdminServiceTest {

    private AdminService1 adminService;
    private AdminDAOImp adminDAOImp;
    private UserDAOImplementation userDAOImplementation;
    private Scanner sc;

    @BeforeEach
    public void setUp() {
        adminDAOImp = Mockito.mock(AdminDAOImp.class);
        userDAOImplementation = Mockito.mock(UserDAOImplementation.class);
        sc = Mockito.mock(Scanner.class);
        adminService = new AdminService1(sc, adminDAOImp, userDAOImplementation);
    }

    @Test
    public void testCreateUser() {
        Users user = new Users();
        user.setUser_name("Haritha");
        user.setSpecilization("Frontend");
        user.setUser_role(Role.TEAM_MEMBER);
        user.setEmail("haritha23@gmail.com");
        user.setPhone("+918978054336");
        user.setPassword("Haritha@23");

        when(adminDAOImp.createUser(any(Users.class))).thenReturn(user);

        Users createdUser = adminService.createUser(user);

        Assertions.assertEquals("Haritha", createdUser.getUser_name());
        Assertions.assertEquals("Frontend", createdUser.getSpecilization());
        Assertions.assertEquals(Role.TEAM_MEMBER, createdUser.getUser_role());
        Assertions.assertEquals("haritha23@gmail.com", createdUser.getEmail());
        Assertions.assertEquals("+918978054336", createdUser.getPhone());
        Assertions.assertEquals("Haritha@23", createdUser.getPassword());

        verify(adminDAOImp).createUser(any(Users.class));
    }

    @Test
    public void testUpdateUser() {
        Users user = new Users();
        user.setUser_name("Haritha Updated");
        user.setSpecilization("Frontend");
        user.setUser_role(Role.TEAM_MEMBER);
        user.setEmail("haritha23_updated@gmail.com");
        user.setPhone("+918978054336");
        user.setPassword("HarithaUpdated@23");
        user.setUser_id(1);

        when(adminDAOImp.updateUser(any(Users.class))).thenReturn(true);

        boolean success = adminService.updateUser(user);

        assertTrue(success);
        verify(adminDAOImp).updateUser(any(Users.class));
    }

    @Test
    public void testDeactivateUser() {
        Users user = new Users();
        user.setUser_id(9);
        user.setStatus(Status.INACTIVE);

        when(adminDAOImp.deactivateUser(user.getUser_id())).thenReturn(true);
        when(userDAOImplementation.getUserById(user.getUser_id())).thenReturn(user);

        boolean res = adminService.deactivateUser(9);
        assertTrue(res);
        Assertions.assertEquals(Status.INACTIVE, user.getStatus());

        verify(adminDAOImp).deactivateUser(9);
        verify(userDAOImplementation).getUserById(9);
    }

    @Test
    public void testAddClient() {
        Mockito.when(sc.nextLine())
                .thenReturn("John")
                .thenReturn("John's Company")
                .thenReturn("john.doe@example.com")
                .thenReturn("+1234567890");

        Clients expectedClient = new Clients();
        expectedClient.setClient_name("John");
        expectedClient.setClient_company_name("John's Company");
        expectedClient.setEmail("john.doe@example.com");
        expectedClient.setPhoneNumber("+1234567890");
        expectedClient.setCreatedAt(new Date(System.currentTimeMillis()));
        expectedClient.setUpdatedAt(new Date(System.currentTimeMillis()));

        when(adminDAOImp.createClient(any(Clients.class))).thenReturn(expectedClient);

        Clients createdClient = adminService.addClient();

        Assertions.assertEquals(expectedClient.getClient_name(), createdClient.getClient_name());
        Assertions.assertEquals(expectedClient.getClient_company_name(), createdClient.getClient_company_name());
        Assertions.assertEquals(expectedClient.getEmail(), createdClient.getEmail());
        Assertions.assertEquals(expectedClient.getPhoneNumber(), createdClient.getPhoneNumber());
        Assertions.assertNotNull(createdClient.getCreatedAt());
        Assertions.assertNotNull(createdClient.getUpdatedAt());

        verify(adminDAOImp).createClient(any(Clients.class));
    }

    @Test
    public void testTrackUserDetails_Success() {
        int userId = 1;

        Users user1 = new Users();
        user1.setUser_id(1);
        user1.setUser_name("John Doe");
        user1.setUser_role(Role.TEAM_MEMBER);
        user1.setStatus(Status.ACTIVE);
        user1.setSpecilization("Developer");

        Users user2 = new Users();
        user2.setUser_id(2);
        user2.setUser_name("Jane Smith");
        user2.setUser_role(Role.PROJECT_MANAGER);
        user2.setStatus(Status.INACTIVE);
        user2.setSpecilization("Manager");

        List<Users> usersList = Arrays.asList(user1, user2);
        when(userDAOImplementation.getAll()).thenReturn(usersList);
        when(userDAOImplementation.getUserById(userId)).thenReturn(user1);

        Users result = adminService.trackUserDetails(userId);

        Assertions.assertNotNull(result, "User should be found");
        Assertions.assertEquals(user1.getUser_id(), result.getUser_id());
        Assertions.assertEquals(user1.getUser_name(), result.getUser_name());
        Assertions.assertEquals(user1.getEmail(), result.getEmail());
        Assertions.assertEquals(user1.getSpecilization(), result.getSpecilization());
        Assertions.assertEquals(user1.getStatus(), result.getStatus());

        verify(userDAOImplementation).getAll();
        verify(userDAOImplementation).getUserById(userId);
    }

    @Test
    public void testTrackUserDetails_UserNotFound() {
        int userId = 1;

        Users user1 = new Users();
        user1.setUser_id(1);
        user1.setUser_name("John Doe");
        user1.setUser_role(Role.TEAM_MEMBER);
        user1.setStatus(Status.ACTIVE);
        user1.setSpecilization("Developer");

        Users user2 = new Users();
        user2.setUser_id(2);
        user2.setUser_name("Jane Smith");
        user2.setUser_role(Role.PROJECT_MANAGER);
        user2.setStatus(Status.INACTIVE);
        user2.setSpecilization("Manager");

        List<Users> usersList = Arrays.asList(user1, user2);
        when(userDAOImplementation.getAll()).thenReturn(usersList);
        when(userDAOImplementation.getUserById(userId)).thenReturn(null);

        Users result = adminService.trackUserDetails(userId);

        Assertions.assertNull(result, "User should not be found");

        verify(userDAOImplementation).getAll();
        verify(userDAOImplementation).getUserById(userId);
    }



}
