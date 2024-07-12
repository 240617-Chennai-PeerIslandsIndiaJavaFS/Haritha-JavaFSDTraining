package org.example.service;

import org.example.dao.AdminDAOImp;
import org.example.dao.ProjectDAOImp;
import org.example.dao.TaskDAOImp;
import org.example.dao.UserDAOImplementation;
import org.example.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import static org.example.service.Validation.getValidUserId;

public class AdminService1 {
    private static final Logger logger = LoggerFactory.getLogger(AdminService1.class);
    private static Scanner sc;
    private AdminDAOImp adminDAOImp;
    private UserDAOImplementation userDAOImplementation;
    private ProjectDAOImp projectDAOImp;
    private TaskDAOImp taskDAOImp;

    public AdminService1() {
    }

    public AdminService1(ProjectDAOImp projectDAOImp, TaskDAOImp taskDAOImp, Scanner sc) {
        this.projectDAOImp = projectDAOImp;
        this.taskDAOImp = taskDAOImp;
        this.sc = sc;
    }

    public AdminService1(Scanner sc, UserDAOImplementation userDAOImplementation) {
        this.sc = sc;
        this.userDAOImplementation = userDAOImplementation;
    }

    public AdminService1(Scanner sc, AdminDAOImp adminDAOImp) {
        this.sc = sc;
        this.adminDAOImp = adminDAOImp;
    }

    public AdminService1(Scanner sc, AdminDAOImp adminDAOImp, UserDAOImplementation userDAOImplementation) {
        this.sc = sc;
        this.adminDAOImp = adminDAOImp;
        this.userDAOImplementation = userDAOImplementation;
    }

    public Users addUser() {
        logger.info("Starting user creation process.");

        String name = promptForValidInput("Enter name: ", this::isValidName, "Invalid name. Please enter a name starting with a capital letter followed by lowercase letters, with a minimum length of 3 and a maximum length of 25 characters.");
        String roleString = promptForValidInput("Select user role (ADMIN, PROJECT_MANAGER, TEAM_MEMBER): ", this::isValidRole, "Invalid role. Please enter one of the following: ADMIN, PROJECT_MANAGER, TEAM_MEMBER.");
        String newEmail = promptForValidInput("Enter email: ", this::isValidEmail, "Invalid email format. Please enter a valid email address.");
        String newPassword = promptForValidInput("Enter password: ", this::isValidPassword, "Invalid password format. Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
        String phone = promptForValidInput("Enter phone: ", this::isValidPhoneNumber, "Invalid phone number format. Please enter a valid international phone number starting with '+'.");

        System.out.print("Enter specialization in technology (Frontend, Backend): ");
        String spec = sc.nextLine();

        Users user = new Users();
        user.setUser_name(name);
        user.setUser_role(Role.valueOf(roleString));
        user.setSpecilization(spec);
        user.setPassword(newPassword);
        user.setEmail(newEmail);
        user.setPhone(phone);

        Users createdUser = createUser(user);
        logger.info("User creation process completed.");
        return createdUser;
    }

    public Users createUser(Users user) {
        return adminDAOImp.createUser(user);
    }

    public boolean updateUser(Users user) {
        return adminDAOImp.updateUser(user);
    }

    public boolean deactivateUser(int userId) {
        Users user = userDAOImplementation.getUserById(userId);
        if (user != null) {
            user.setStatus(Status.INACTIVE);
            return adminDAOImp.deactivateUser(userId);
        }
        return false;
    }

    public Clients addClient() {
        logger.info("Starting client creation process.");

        String clientName = promptForValidInput("Enter client name: ", this::isValidName, "Invalid name. Please enter a name starting with a capital letter followed by lowercase letters, with a minimum length of 3 and a maximum length of 25 characters.");
        System.out.print("Enter client company name: ");
        String clientCompanyName = sc.nextLine();
        String clientEmail = promptForValidInput("Enter client email: ", this::isValidEmail, "Invalid email format. Please enter a valid email address.");
        String clientPhone = promptForValidInput("Enter client phone: ", this::isValidPhoneNumber, "Invalid phone number format. Please enter a valid international phone number starting with '+'.");

        Clients client = new Clients();
        client.setClient_name(clientName);
        client.setClient_company_name(clientCompanyName);
        client.setEmail(clientEmail);
        client.setPhoneNumber(clientPhone);
        client.setCreatedAt(new Date(System.currentTimeMillis()));
        client.setUpdatedAt(new Date(System.currentTimeMillis()));

        adminDAOImp.createClient(client);
        logger.info("Client creation process completed.");
        return client;
    }

    public Users trackUserDetails() {
        logger.info("Starting track user details process.");

        List<Users> listOfUsers = userDAOImplementation.getAll();
        for (Users user : listOfUsers) {
            logger.info("User details: ID={}, Name={}, Role={}, Status={}, Specialization={}",
                    user.getUser_id(), user.getUser_name(), user.getUser_role(), user.getStatus(), user.getSpecilization());
        }

        System.out.print("Enter user ID of the user you want to see user details: ");
        int userId = getValidUserId(sc);
        Users user = userDAOImplementation.getUserById(userId);

        if (user != null) {
            logger.info("User details: ID={}, Name={}, Email={}, Specialization={}, Status={}",
                    user.getUser_id(), user.getUser_name(), user.getEmail(), user.getSpecilization(), user.getStatus());
            System.out.println(user.getUser_id() + " " + user.getUser_name().toUpperCase() + " " + user.getEmail() + " " + user.getSpecilization() + " " + user.getStatus());
        } else {
            logger.warn("User ID not valid.");
            System.out.println("User ID not valid.");
        }
        return user;
    }

    public Users trackUserDetails(int userId) {
        logger.info("Starting track user details process.");

        List<Users> listOfUsers = userDAOImplementation.getAll();
        for (Users user : listOfUsers) {
            logger.info("User details: ID={}, Name={}, Role={}, Status={}, Specialization={}",
                    user.getUser_id(), user.getUser_name(), user.getUser_role(), user.getStatus(), user.getSpecilization());
        }

        Users user = userDAOImplementation.getUserById(userId);

        if (user != null) {
            logger.info("User details: ID={}, Name={}, Email={}, Specialization={}, Status={}",
                    user.getUser_id(), user.getUser_name(), user.getEmail(), user.getSpecilization(), user.getStatus());
            return user;
        } else {
            logger.warn("User ID not valid.");
            return null;
        }
    }

    public String monitorTaskDetails() {
        StringBuilder result = new StringBuilder();
        logger.info("Starting monitor task details process.");
        result.append("List of projects:\n");

        List<Projects> listOfProjects = projectDAOImp.getAll();
        for (Projects project : listOfProjects) {
            result.append("Project ID: ").append(project.getProject_id()).append(" Project Name: ").append(project.getProject_name()).append("\n");
            logger.info("Project details: ID={}, Name={}", project.getProject_id(), project.getProject_name());
        }

        System.out.print("Enter project ID of the project you want to see task details: ");
        int projectId = getValidUserId(sc);
        Projects project = projectDAOImp.getId(projectId);

        if (project != null) {
            List<Tasks> taskDetails = taskDAOImp.getTaskDetailByProjectId(projectId);

            if (taskDetails != null && !taskDetails.isEmpty()) {
                result.append("List of tasks for the project:\n");
                for (Tasks task : taskDetails) {
                    result.append("Task ID: ").append(task.getTask_id()).append(" Task Name: ").append(task.getTask_name()).append("\n");
                }

                System.out.print("Enter task ID you want to see: ");
                int taskId = getValidUserId(sc);
                Tasks task = taskDAOImp.getId(taskId);

                if (task != null) {
                    result.append("Task ID: ").append(task.getTask_id())
                            .append(" Task Name: ").append(task.getTask_name())
                            .append(" Milestone ID: ").append(task.getMilestone_id())
                            .append(" Updated at: ").append(task.getUpdatedAt()).append("\n");
                } else {
                    logger.warn("Task ID not found: {}", taskId);
                    result.append("Task ID not found.\n");
                }
            } else {
                logger.warn("No tasks found for project with ID: {}", projectId);
                result.append("No tasks found for this project.\n");
            }
        } else {
            logger.warn("Project ID not valid: {}", projectId);
            result.append("Project ID not valid.\n");
        }
        return result.toString();
    }

    public static boolean createTeam(Projects project) {
        logger.info("Creating team for project: {}", project.getProject_name());

        System.out.print("Enter team name: ");
        String name ="Some name";
        System.out.println(name + " " + project.getProject_id() + " " + project.getManager_id());
        logger.info("Team name entered: {}", name);

        boolean teamCreated = AdminDAOImp.createTeamForProject(name, project.getProject_id(), project.getManager_id());
        if (teamCreated) {
            logger.info("Team created successfully");
            System.out.println("Team created successfully");
            sc.nextLine();
        } else {
            logger.error("Team creation failed");
            System.out.println("Team not formed");
        }

        return teamCreated;
    }

    private String promptForValidInput(String prompt, Predicate<String> isValid, String errorMessage) {
        String input;
        do {
            System.out.print(prompt);
            input = sc.nextLine();
            if (!isValid.test(input)) {
                System.out.println(errorMessage);
            }
        } while (!isValid.test(input));
        return input;
    }

    private boolean isValidName(String name) {
        return name.matches("[A-Z][a-z]{2,24}");
    }

    private boolean isValidRole(String role) {
        try {
            Role.valueOf(role);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$");
    }

    private boolean isValidPhoneNumber(String phone) {
        return phone.matches("^\\+\\d{1,15}$");
    }
}
