package model;

import static org.junit.jupiter.api.Assertions.*;
import exceptions.InvalidCredentialsException;
import exceptions.UserNameAlreadyInUseException;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class AcademicScheduleUsersManagerTest {

    private AcademicScheduleUsersManager asum;

    private void setUpScenary1() {
        asum = new AcademicScheduleUsersManager();
    }//End setUpScenary1

    private void setUpScenary2() throws IOException, UserNameAlreadyInUseException {
        asum = new AcademicScheduleUsersManager();
        asum.addUser("Diego","Hidalgo","DiegoH","diegofer","...");
        asum.addUser("Brian","Romero","BrianR","brianromero","...");
        asum.addUser("Juan","Hernandez","JuanR","juanhernandez","...");
    }//End setUpScenary2

    private void setUpScenary3() {
        asum = new AcademicScheduleUsersManager();
        try {
            asum.addUser("Diego", "Hidalgo", "DiegoH", "diegofer", "...");
            asum.logIn("DiegoH", "diegofer");
        } catch (UserNameAlreadyInUseException | InvalidCredentialsException | IOException ignored) {}
    }//End setUpScenary3

    @Test
    public void testAddUser1() throws IOException {
        setUpScenary1();
        String name = "Diego";
        String lastName = "Hidalgo";
        String userName = "DiegoH";
        String password = "diegofer";
        String photoPath = "...";
        try {
            asum.addUser(name, lastName, userName, password, photoPath);
        } catch (UserNameAlreadyInUseException e) {
            fail(e.getMessage());
        } finally {
            assertEquals(1, asum.getUsers().size());
            assertEquals("DiegoH", asum.getUsers().get(0).getUserName());
        }//End finally
    }//End testAddUser1

    @Test
    public void testAddUser2() throws IOException, UserNameAlreadyInUseException {
        setUpScenary2();
        String name = "Diego";
        String lastName = "Hidalgo";
        String userName = "DiegoH";
        String password = "diegofer";
        String photoPath = "...";
        try {
            asum.addUser(name, lastName, userName, password, photoPath);
        } catch (UserNameAlreadyInUseException e) {
            assertEquals(3, asum.getUsers().size());
            assertEquals(userName, e.getUserName());
        }//End try/catch
    }//End testAddUser3

    @Test
    public void testLogIn1() throws IOException, UserNameAlreadyInUseException {
        setUpScenary2();
        try {
            asum.logIn("DiegoH", "diegofer");
            assertEquals("DiegoH", asum.getCurrentUser().getUserName());
        } catch (InvalidCredentialsException e) {
            fail();
        }//End try/catch
    }//End testLogIn1

    @Test
    public void testLogIn2() throws IOException, UserNameAlreadyInUseException {
        setUpScenary2();
        try{
            asum.logIn("DiegoH","1234567");
        } catch (InvalidCredentialsException e) {
            assertNull(asum.getCurrentUser());
        }//End try/catch
    }//End testLogIn2

    @Test
    public void testVerifyBlankChars1() {
        setUpScenary1();
        assertTrue(asum.verifyBlankChars(new String[]{"a","b","c","d","e","f"}));
    }//End testVerifyBlankChars1

    @Test
    public void testVerifyBlankChars2() {
        setUpScenary1();
        assertFalse(asum.verifyBlankChars(new String[]{" ", "  ", "   ", "ab  "}));
    }//End testVerifyBlankChars2

    @Test
    public void testChangeUser1() throws IOException, UserNameAlreadyInUseException {
        setUpScenary3();
        asum.changeUser("Juan","Perez","JuanPe","1234567","...");
        assertEquals("Juan",asum.getCurrentUser().getName());
        assertEquals("Perez", asum.getCurrentUser().getLastName());
        assertEquals("JuanPe",asum.getCurrentUser().getUserName());
        assertEquals("1234567",asum.getCurrentUser().getPassword());
    }//End testChangeUser1

    @Test
    public void testDeleteUser1() throws IOException {
        setUpScenary3();
        asum.deleteUser();
        assertNull(asum.getCurrentUser());
    }//End testDeleteUser1

    @Test
    public void testChangeUserPassword1() throws IOException, UserNameAlreadyInUseException {
        setUpScenary2();
        asum.changeUserPassword("DiegoH","diegofer12345");
        assertEquals("diegofer12345",asum.getUsers().get(1).getPassword());
    }//End testChangeUserPassword1

}//End AcademicScheduleUsersManagerTest
