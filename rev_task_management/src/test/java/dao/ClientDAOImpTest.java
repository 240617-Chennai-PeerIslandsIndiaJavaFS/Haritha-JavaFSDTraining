package dao;

import org.example.connection.DBConnection;
import org.example.dao.AdminDAOImp;
import org.example.dao.ClientDAOImp;
import org.example.model.Clients;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClientDAOImpTest {
    static ClientDAOImp clientDAOImp;
    static Connection con;


    @BeforeAll
    public static void setUpBeforeClass() throws SQLException {
        clientDAOImp = new ClientDAOImp();
        con = DBConnection.getConnection();

    }

    @AfterAll
    public static void tearDownAfterClass() {
        DBConnection.closeConnection();
    }
//    @Test
//    public void testCreateClient() {
//        Clients client = new Clients();
//        client.setClient_name("Dummy1");
//        client.setClient_company_name("dummy1 company name");
//        client.setEmail("dummy213@gmail.com");
//        client.setPhoneNumber("+918876560908");
//        client.setCreatedAt(new java.util.Date());
//
//        Clients result = clientDAOImp.create(client);
//
//        assertNotNull(result);
//        assertEquals(client.getClient_name(), result.getClient_name());
//    }

//    @Test
//    public void testDeleteClient() {
//        boolean val = clientDAOImp.delete(7);
//        assertTrue(val);
//
//    }

//    @Test
//    public void testUpdateClient() {
//        boolean val = clientDAOImp.update("Dummy3",2);
//        assertTrue(val);
//    }

//    @Test
//    public void testGetId() {
//        Clients clients = clientDAOImp.getId(1);
//        assertEquals("Akash",clients.getClient_name());
//    }

//
//    @Test
//    public void testGetAll(){
//        List<Clients> clientsList = clientDAOImp.getAll();
//        assertNotNull(clientsList);
//        assertEquals(2, clientsList.size());
//    }


}
