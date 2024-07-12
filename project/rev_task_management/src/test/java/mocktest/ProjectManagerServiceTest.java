package mocktest;

import org.example.dao.*;
import org.example.model.*;
import org.example.service.ProjectManagerService1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ProjectManagerServiceTest {

    private ProjectManagerService1 projectManagerService1;
    private ProjectUserDAOImp projectUserDAOImp;
    private UserDAOImplementation userDAOImplementation;

    @BeforeEach
    public void setUp() {
        projectUserDAOImp = Mockito.mock(ProjectUserDAOImp.class);
        userDAOImplementation = Mockito.mock(UserDAOImplementation.class);
        projectManagerService1 = new ProjectManagerService1(projectUserDAOImp, userDAOImplementation);
    }

    @Test
    public void testViewProjectClientDetails() throws SQLException {
        ProjectDAOImp projectDAOImp = Mockito.mock(ProjectDAOImp.class);
        Connection con = Mockito.mock(Connection.class);
        ProjectManagerService1 projectManagerService = new ProjectManagerService1(projectDAOImp, con);

        int projectId = 1;
        Projects project = new Projects();
        project.setProject_id(projectId);
        project.setClient_id(101);

        Clients client = new Clients();
        client.setClient_id(101);
        client.setClient_name("Client Name");
        client.setClient_company_name("Client Company");
        client.setEmail("client@example.com");
        client.setPhoneNumber("1234567890");

        when(projectDAOImp.getId(anyInt())).thenReturn(project);

        PreparedStatement ptmt = Mockito.mock(PreparedStatement.class);
        ResultSet rs = Mockito.mock(ResultSet.class);

        when(con.prepareStatement(anyString())).thenReturn(ptmt);
        when(ptmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt(1)).thenReturn(client.getClient_id());
        when(rs.getString(2)).thenReturn(client.getClient_name());
        when(rs.getString(3)).thenReturn(client.getClient_company_name());
        when(rs.getString(4)).thenReturn(client.getEmail());
        when(rs.getString(5)).thenReturn(client.getPhoneNumber());

        Clients result = projectManagerService.viewProjectClientDetails(projectId);

        verify(projectDAOImp).getId(projectId);
        verify(ptmt).setInt(1, project.getClient_id());
        verify(ptmt).executeQuery();
        verify(rs).next();
        verify(rs).getInt(1);
        verify(rs).getString(2);
        verify(rs).getString(3);
        verify(rs).getString(4);
        verify(rs).getString(5);

        assertNotNull(result);
        Assertions.assertEquals(client.getClient_id(), result.getClient_id());
        Assertions.assertEquals(client.getClient_name(), result.getClient_name());
        Assertions.assertEquals(client.getClient_company_name(), result.getClient_company_name());
        Assertions.assertEquals(client.getEmail(), result.getEmail());
        Assertions.assertEquals(client.getPhoneNumber(), result.getPhoneNumber());
    }

    @Test
    public void testDeleteTask() throws SQLException {
        ProjectDAOImp projectDAOImp = Mockito.mock(ProjectDAOImp.class);
        Connection con = Mockito.mock(Connection.class);
        ProjectManagerService1 projectManagerService = new ProjectManagerService1(projectDAOImp, con);

        int projectId = 1;

        PreparedStatement ptmt = Mockito.mock(PreparedStatement.class);

        when(con.prepareStatement(anyString())).thenReturn(ptmt);
        when(ptmt.executeUpdate()).thenReturn(1); // Simulate successful deletion

        boolean result = projectManagerService.deleteTask(projectId);

        verify(con).prepareStatement("DELETE FROM tasks WHERE project_id = ?");
        verify(ptmt).setInt(1, projectId);
        verify(ptmt).executeUpdate();

        Assertions.assertTrue(result, "Task should be deleted successfully");
    }

    @Test
    public void viewTaskDetails() {
        List<TimeStamps> list = projectManagerService1.viewTaskDetails(1, 1);
        assertNotNull(list);
    }

    @Test
    public void testAddTeamMemberToProject() throws SQLException {
        ProjectDAOImp projectDAOImp = Mockito.mock(ProjectDAOImp.class);
        TeamsDAO teamsDAO = Mockito.mock(TeamsDAO.class);
        UserDAOImplementation userDAOImplementation = Mockito.mock(UserDAOImplementation.class);
        TeamMemberDAOImpl teamMemberDAO = Mockito.mock(TeamMemberDAOImpl.class);
        ProjectManagerDAOImpl projectManagerDAO = Mockito.mock(ProjectManagerDAOImpl.class);

        ProjectManagerService1 projectManagerService = new ProjectManagerService1(projectDAOImp, teamsDAO, userDAOImplementation, teamMemberDAO, projectUserDAOImp);
        projectManagerService.projectManagerDAO = projectManagerDAO;

        int projectId = 1;
        int managerId = 1;
        int userId = 9;

        Projects project = new Projects();
        project.setProject_id(projectId);
        when(projectDAOImp.getId(projectId)).thenReturn(project);

        Teams team = new Teams();
        team.setTeam_id(1);
        when(teamsDAO.getId(projectId)).thenReturn(team);

        Users user = new Users();
        user.setUser_id(userId);
        user.setManager_id(0);
        user.setUser_role(Role.TEAM_MEMBER);
        when(userDAOImplementation.getAll()).thenReturn(Collections.singletonList(user));
        when(userDAOImplementation.getUserById(userId)).thenReturn(user);
        when(teamMemberDAO.create(team.getTeam_id(), userId)).thenReturn(true);
        when(userDAOImplementation.updateManagerId(userId, managerId)).thenReturn(true);
        when(projectManagerDAO.addTeamMemberToProject(projectId, userId)).thenReturn(true);

        boolean result = projectManagerService.addTeamMemberToProject(projectId, managerId);

        Assertions.assertTrue(result, "Team member should be added successfully");

        verify(projectDAOImp).getId(projectId);
        verify(teamsDAO).getId(projectId);
        verify(userDAOImplementation).getAll();
        verify(userDAOImplementation).getUserById(userId);
        verify(teamMemberDAO).create(team.getTeam_id(), userId);
        verify(userDAOImplementation).updateManagerId(userId, managerId);
        verify(projectManagerDAO).addTeamMemberToProject(projectId, userId);
    }

    @Test
    public void testRemoveTeamMemberFromProject_Success() {
        int projectId = 1;
        int userId = 1;

        List<Integer> userList = Arrays.asList(userId);
        when(projectUserDAOImp.getProjectId(projectId)).thenReturn(userList);

        Users user = new Users();
        user.setUser_id(userId);
        user.setUser_name("John Doe");
        when(userDAOImplementation.getUserById(userId)).thenReturn(user);
        when(projectUserDAOImp.deleteUser(userId)).thenReturn(true);
        when(userDAOImplementation.updateManagerId(userId, 0)).thenReturn(true);

        boolean result = projectManagerService1.removeTeamMemberFromProject(projectId);

        Assertions.assertTrue(result, "Team member should be removed successfully");

        verify(projectUserDAOImp).getProjectId(projectId);
        verify(userDAOImplementation).getUserById(userId);
        verify(projectUserDAOImp).deleteUser(userId);
        verify(userDAOImplementation).updateManagerId(userId, 0);
    }
}
