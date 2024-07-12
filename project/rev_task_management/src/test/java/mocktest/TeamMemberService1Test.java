package mocktest;

import org.example.dao.ClientDAOImp;
import org.example.dao.ProjectDAOImp;
import org.example.dao.TaskDAOImp;
import org.example.dao.TeamMemberDAOImpl;
import org.example.model.Clients;
import org.example.model.Projects;
import org.example.model.Tasks;
import org.example.service.TeamMemberService1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class TeamMemberService1Test {

    @Mock
    private TeamMemberDAOImpl teamMemberDAO;

    @Mock
    private TaskDAOImp taskDAOImp;

    @Mock
    private ProjectDAOImp projectDAOImp;

    private TeamMemberService1 teamMemberService;
    private ClientDAOImp clientDAOImp = new ClientDAOImp();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        teamMemberService = new TeamMemberService1(taskDAOImp, teamMemberDAO);
    }

    @Test
    public void testViewAssignedTasks() {
        Tasks task1 = new Tasks();
        task1.setTask_id(1);
        task1.setProject_id(1);
        task1.setTask_name("Task 1");
        task1.setDescription("Description 1");
        task1.setMilestone_id(1);
        task1.setPercentage(50.0);

        Tasks task2 = new Tasks();
        task2.setTask_id(2);
        task2.setProject_id(1);
        task2.setTask_name("Task 2");
        task2.setDescription("Description 2");
        task2.setMilestone_id(2);
        task2.setPercentage(75.0);

        List<Tasks> mockTasks = Arrays.asList(task1, task2);

        int userId = 1;
        when(teamMemberDAO.viewAssignedTasks(userId)).thenReturn(mockTasks);

        List<Tasks> returnedTasks = teamMemberService.viewAssignedTasks(userId);

        assertEquals(2, returnedTasks.size());
        assertEquals("Task 1", returnedTasks.get(0).getTask_name());
        assertEquals("Task 2", returnedTasks.get(1).getTask_name());
    }

    @Test
    public void testViewTaskDetails_TaskFound() {
        int taskId = 1;
        int userId = 1;
        Tasks expectedTask = new Tasks();
        expectedTask.setTask_id(taskId);
        expectedTask.setProject_id(1);

        when(teamMemberDAO.viewTaskDetails(taskId, userId)).thenReturn(expectedTask);

        Tasks returnedTask = teamMemberService.viewTaskDetails(userId, taskId);

        assertNotNull(returnedTask, "Returned task should not be null");
        assertEquals(expectedTask.getTask_id(), returnedTask.getTask_id());
        assertEquals(expectedTask.getProject_id(), returnedTask.getProject_id());
    }

    @Test
    public void testViewProjectDetails_TaskAndProjectFound() {
        int taskId = 1;
        int projectId = 1;
        Tasks task = new Tasks();
        task.setTask_id(taskId);
        task.setProject_id(projectId);

        Projects projectDetails = new Projects();
        projectDetails.setProject_id(projectId);
        projectDetails.setProject_name("Project 1");
        projectDetails.setManager_id(10);
        projectDetails.setDescription("Description for Project 1");

        when(taskDAOImp.getId(taskId)).thenReturn(task);
        when(projectDAOImp.getId(projectId)).thenReturn(projectDetails);

        Projects returnedProject = teamMemberService.viewProjectDetails(taskId);

        assertEquals(projectDetails.getProject_id(), returnedProject.getProject_id());

    }

    @Test
    public void testViewClientDetails_ClientNotFound() {
        int taskId = 1;
        int projectId = 1;
        int clientId = 1;

        Tasks task = new Tasks();
        task.setTask_id(taskId);
        task.setProject_id(projectId);

        Projects projectDetails = new Projects();
        projectDetails.setProject_id(projectId);
        projectDetails.setClient_id(clientId);

        when(taskDAOImp.getId(taskId)).thenReturn(task);
        when(projectDAOImp.getId(projectId)).thenReturn(projectDetails);

        Clients returnedClient = teamMemberService.viewClientDetails(taskId);

        assertNotNull(returnedClient, "Client details should be null when client not found.");
    }


}
