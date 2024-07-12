package dao;

import org.example.connection.DBConnection;
import org.example.dao.TeamMemberDAOImpl;
import org.example.dao.TeamsDAOImpl;
import org.example.model.Teams;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TeamsDAOImpTest {
    static TeamsDAOImpl teamsDAO;
    static Connection con;


    @BeforeAll
    public static void setUpBeforeClass() throws SQLException {
        teamsDAO = new TeamsDAOImpl();
        con = DBConnection.getConnection();

    }

    @AfterAll
    public static void tearDownAfterClass() {
        DBConnection.closeConnection();
    }

    @Test
    public void testCreate() {
        String query = "INSERT INTO teams(team_name, manager_id, project_id) VALUES (?, ?, ?)";
        Teams teams = new Teams();
        teams.setTeam_name("EE");
        teams.setManager_id(2);
        teams.setProject_id(3);
        Teams team = teamsDAO.create(teams);
        assertNotNull(team);

    }

    @Test
    public void testDelete() {
        boolean res = teamsDAO.delete(3);
        assertTrue(res);
    }

    @Test
    public void testUpdate() {
        String query = "UPDATE teams SET team_name = ? WHERE team_id = ?";
        boolean res = teamsDAO.update("ER",4);
        assertTrue(res);
    }

    @Test
    public void testGetId() {
        Teams team = teamsDAO.getId(4);
        assertNotNull(team);
    }

    @Test
    public void testGetAll() {
        List<Teams> list = teamsDAO.getAll();
        assertEquals(3,list.size());
    }

}
