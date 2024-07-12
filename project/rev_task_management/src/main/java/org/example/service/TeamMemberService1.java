package org.example.service;

import com.sun.jdi.connect.spi.Connection;
import org.example.dao.ClientDAOImp;
import org.example.dao.ProjectDAOImp;
import org.example.dao.TaskDAOImp;
import org.example.dao.TeamMemberDAOImpl;
import org.example.model.Clients;
import org.example.model.Projects;
import org.example.model.Tasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class TeamMemberService1 {
    private static final Logger logger = LoggerFactory.getLogger(TeamMemberService1.class);
    private TaskDAOImp taskDAOImp;
    private TeamMemberDAOImpl teamMemberDAO;
    private static ProjectDAOImp projectDAOImp = new ProjectDAOImp();
    private static ClientDAOImp clientDAOImp = new ClientDAOImp();

    public TeamMemberService1(TaskDAOImp taskDAOImp, TeamMemberDAOImpl teamMemberDAO) {
        this.taskDAOImp = taskDAOImp;
        this.teamMemberDAO = teamMemberDAO;

    }

    public TeamMemberService1(TaskDAOImp taskDAOImp, ProjectDAOImp projectDAOImp) {
        this.taskDAOImp = taskDAOImp;
    }

    public TeamMemberService1(Connection mockConnection) {
    }

    public List<Tasks> viewAssignedTasks(int userId) {
        try {
            logger.info("Viewing assigned tasks for user ID: {}", userId);

            List<Tasks> tasksList = teamMemberDAO.viewAssignedTasks(userId);
            if (tasksList != null && !tasksList.isEmpty()) {
                for (Tasks task : tasksList) {
                    System.out.println("Task ID: " + task.getTask_id() +
                            ", Project ID: " + task.getProject_id() +
                            ", Task Name: " + task.getTask_name() +
                            ", Description: " + task.getDescription() +
                            ", Milestone: " + task.getMilestone_id() +
                            ", Percentage: " + task.getPercentage());
                }
            } else {
                System.out.println("No assigned tasks found.");
            }

            return tasksList;
        } catch (Exception e) {
            logger.error("An error occurred while viewing assigned tasks: {}", e.getMessage());
            e.printStackTrace();
            return null; // or throw a custom exception as needed
        }
    }

    public Tasks viewTaskDetails(int userId, int taskId) {
        try {
            logger.info("Viewing details for task ID {} for user ID: {}", taskId, userId);

            Tasks task = teamMemberDAO.viewTaskDetails(taskId, userId);
            if (task != null) {
                logger.debug("Fetched task details for task with ID {} assigned to user with ID {}", taskId, userId);
                return task;
            } else {
                logger.warn("No task found with ID {} assigned to user with ID {}", taskId, userId);
                return null;
            }
        } catch (Exception e) {
            logger.error("An error occurred while viewing task details: {}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public Projects viewProjectDetails(int taskId) {
        try {
            logger.info("Viewing project details for task ID: {}", taskId);

            Tasks task = taskDAOImp.getId(taskId);
            if (task != null) {
                Projects projectDetails = projectDAOImp.getId(task.getProject_id());
                if (projectDetails != null) {
                    logger.debug("Fetched project details for task ID {}", taskId);
                    return projectDetails;
                } else {
                    logger.warn("No project details found for task ID {}", taskId);
                }
            } else {
                logger.warn("No task found for task ID {}", taskId);
            }
        } catch (Exception e) {
            logger.error("An error occurred while viewing project details: {}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public Clients viewClientDetails(int taskId) {
        try {
            logger.info("Viewing client details for task ID: {}", taskId);

            Tasks task = taskDAOImp.getId(taskId);
            if (task != null) {
                Projects projectDetails = projectDAOImp.getId(task.getProject_id());
                if (projectDetails != null) {
                    Clients clientDetails = clientDAOImp.getId(projectDetails.getClient_id());
                    if (clientDetails != null) {
                        return clientDetails;
                    } else {
                        logger.warn("No client details found for project ID: {}", projectDetails.getProject_id());
                        return null;
                    }
                } else {
                    logger.warn("No project details found for task ID: {}", taskId);
                    return null;
                }
            } else {
                logger.warn("No task found for task ID: {}", taskId);
                return null;
            }
        } catch (Exception e) {
            logger.error("An error occurred while viewing client details: {}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }



}
