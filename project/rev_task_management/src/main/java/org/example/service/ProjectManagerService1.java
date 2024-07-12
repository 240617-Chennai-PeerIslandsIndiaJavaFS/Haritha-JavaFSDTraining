package org.example.service;

import org.example.dao.*;
import org.example.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProjectManagerService1 {
    private static final Logger logger = LoggerFactory.getLogger(ProjectManagerService.class);
    private ProjectDAOImp projectDAOImp;
    private Connection con;
    private TeamsDAO teamsDAO;
    private UserDAOImplementation userDAOImplementation;
    private TeamMemberDAOImpl teamMemberDAO;
    public ProjectManagerDAOImpl projectManagerDAO = new ProjectManagerDAOImpl();
    private MilestoneDAOImp milestoneDAOImp = new MilestoneDAOImp();
    private TimeStampDAOImp timeStampDAOImp = new TimeStampDAOImp();
    private ProjectUserDAOImp projectUserDAOImp;

    public ProjectManagerService1(ProjectDAOImp projectDAOImp, TeamsDAO teamsDAO, UserDAOImplementation userDAOImplementation, TeamMemberDAOImpl teamMemberDAO, ProjectUserDAOImp projectUserDAOImp) {
        this.projectDAOImp = projectDAOImp;
        this.teamsDAO = teamsDAO;
        this.userDAOImplementation = userDAOImplementation;
        this.teamMemberDAO = teamMemberDAO;
        this.projectUserDAOImp = projectUserDAOImp;
    }

    public ProjectManagerService1(ProjectDAOImp projectDAOImp, Connection con) {
        this.projectDAOImp = projectDAOImp;
        this.con = con;
    }

    public ProjectManagerService1() {
    }

    public ProjectManagerService1(ProjectUserDAOImp projectUserDAOImp, UserDAOImplementation userDAOImplementation) {
        this.projectUserDAOImp = projectUserDAOImp;
        this.userDAOImplementation = userDAOImplementation;
    }

    public ProjectManagerService1(ProjectDAOImp projectDAOImp, TeamsDAO teamsDAO, UserDAOImplementation userDAOImplementation, TeamMemberDAOImpl teamMemberDAO) {
    }

    public Clients viewProjectClientDetails(int projectId) {
        Projects project = projectDAOImp.getId(projectId);
        if (project != null) {
            logger.info("Retrieving client details for project ID {}", projectId);
            return viewClientDetails(project.getClient_id());
        } else {
            logger.warn("Project with ID {} not found", projectId);
            return null;
        }
    }

    public Clients viewClientDetails(int clientId) {
        String query = "SELECT * FROM clients WHERE client_id = ?";
        try (PreparedStatement ptmt = con.prepareStatement(query)) {
            ptmt.setInt(1, clientId);
            ResultSet rs = ptmt.executeQuery();
            if (rs.next()) {
                Clients client = new Clients();
                client.setClient_id(rs.getInt(1));
                client.setClient_name(rs.getString(2));
                client.setClient_company_name(rs.getString(3));
                client.setEmail(rs.getString(4));
                client.setPhoneNumber(rs.getString(5));
                logger.info("Client details retrieved for ID: {}", clientId);
                return client;
            }
        } catch (SQLException ex) {
            logger.error("SQLException occurred: {}", ex.getMessage());
            System.out.println("SQLException occurred: " + ex.getMessage());
        }
        return null;
    }

    public boolean deleteTask(int projectId) {
        String query = "DELETE FROM tasks WHERE project_id = ?";
        try (PreparedStatement ptmt = con.prepareStatement(query)) {
            ptmt.setInt(1, projectId);
            int rowsAffected = ptmt.executeUpdate();
            logger.info("{} row(s) affected by task deletion for project ID {}", rowsAffected, projectId);
            return rowsAffected > 0;
        } catch (SQLException ex) {
            logger.error("SQLException occurred while deleting task: {}", ex.getMessage());
            return false;
        }
    }

    public List<TimeStamps> viewTaskDetails(int projectId, int taskId) {
        try {
            logger.info("Viewing task details for project ID {}", projectId);
            List<Tasks> tasks = projectManagerDAO.viewTaskDetails(projectId);
            return timeStampDAOImp.getAllTimeStampBasedTaskId(taskId);
        } catch (Exception e) {
            logger.error("An error occurred while viewing task details: {}", e.getMessage(), e);
        }
        return null;
    }

    public boolean addTeamMemberToProject(int project_id, int manager_id) throws SQLException {
        try {
            Projects project = projectDAOImp.getId(project_id);
            if (project == null) {
                System.out.println("Project with ID " + project_id + " does not exist.");
                return false;
            }

            logger.info("Adding team member to project with ID {}", project_id);
            Teams team = teamsDAO.getId(project_id);
            if (team == null) {
                System.out.println("Team for project ID " + project_id + " does not exist.");
                return false;
            }

            List<Users> usersList = userDAOImplementation.getAll();
            int userId = 9;

            Users user = userDAOImplementation.getUserById(userId);
            if (user == null || user.getManager_id() != 0 || !user.getUser_role().equals(Role.TEAM_MEMBER)) {
                System.out.println("User with ID " + userId + " does not exist or is not a team member.");
                return false;
            }

            boolean isCreated = teamMemberDAO.create(team.getTeam_id(), userId);
            boolean isUpdated = userDAOImplementation.updateManagerId(userId, manager_id);

            if (isCreated && isUpdated) {
                boolean success = projectManagerDAO.addTeamMemberToProject(project_id, userId);
                if (success) {
                    System.out.println("Team member added to project successfully.");
                    return true;
                } else {
                    System.out.println("Failed to add team member to project.");
                }
            } else {
                System.out.println("Failed to add team member to the team member table or update manager ID.");
            }

            return false;
        } catch (Exception e) {
            logger.error("An error occurred while adding team member: {}", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeTeamMemberFromProject(int projectId) {
        System.out.println("List of users id based on the project id");
        List<Integer> listOfUser = projectUserDAOImp.getProjectId(projectId);
        if (listOfUser != null && !listOfUser.isEmpty()) {
            for (Integer id : listOfUser) {
                System.out.println("User id's: " + id);
            }
        } else {
            System.out.println("No users found for project ID: " + projectId);
            return false;
        }

        System.out.print("Enter which team member you want to remove from project: ");
        int user_id = 1;

        Users user = userDAOImplementation.getUserById(user_id);
        if (user == null) {
            System.out.println("User with ID " + user_id + " does not exist.");
            return false;
        }

        boolean removeUser = projectUserDAOImp.deleteUser(user_id);
        if (removeUser) {
            System.out.println("Removed successfully");
            boolean updateManagerIdToNull = userDAOImplementation.updateManagerId(user_id, 0);
            if (updateManagerIdToNull) {
                System.out.println("Updated manager ID to null for the user");
                return true;
            } else {
                System.out.println("Manager ID not updated to null");
                return false;
            }
        } else {
            System.out.println("Failed to remove user from project");
            return false;
        }
    }

}
