package model;

import static org.junit.jupiter.api.Assertions.*;

import exceptions.InvalidTimeFormatException;
import exceptions.OutOfTimeRangeException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AcademicScheduleTest {

    private AcademicSchedule as;

    private void setUpScenery4() {
        as = new AcademicSchedule();
    }//End setUpScenery4

    private void setUpScenery5() throws InvalidTimeFormatException, OutOfTimeRangeException, IOException {
        as = new AcademicSchedule();
        String name = "APO II";
        int credits = 3;
        ArrayList<String> days = new ArrayList<>();
        days.add("Lunes");
        days.add("Jueves");
        ArrayList<Time> initHours = new ArrayList<>();
        initHours.add(new Time("14:00"));
        initHours.add(new Time("14:00"));
        ArrayList<Time> finishHours = new ArrayList<>();
        finishHours.add(new Time("15:00"));
        finishHours.add(new Time("15:00"));
        as.addCourse(name, credits, days, initHours, finishHours);
    }//End setUpScenery5

    private void setUpScenery6() throws OutOfTimeRangeException, InvalidTimeFormatException, IOException {
        setUpScenery5();
        Time toSendAtHour = new Time("13:00");
        Date date = new Date();
        String description = "Recordatorio de clase";
        String title = "Recordatorio";
        Time initHour = new Time("14:00");
        Time finHour = new Time("15:00");
        String day = "Lunes";
        as.addNotify(toSendAtHour, date, description, title, initHour, finHour, day, as.getFirstCourse());
    }//End setUpScenery6

    private void setUpScenery7() throws InvalidTimeFormatException, OutOfTimeRangeException, IOException {
        setUpScenery5();
        String title = "Plan de estudio";
        String description = "Prueba de plan de estudio";
        ArrayList<String> goals = new ArrayList<>();
        goals.add("1");
        goals.add("2");
        goals.add("3");
        String day = "Lunes";
        Time init = new Time("08:00");
        Time fin = new Time("10:00");
        as.addStudyPlan(title, description, goals, day, init, fin, as.getFirstCourse());
    }//End setUpScenery7

    @Test
    public void testAddCourse1() throws InvalidTimeFormatException, OutOfTimeRangeException, IOException {
        setUpScenery4();
        String name = "Álgebra lineal";
        int credits = 3;
        ArrayList<String> days = new ArrayList<>();
        days.add("Lunes");
        days.add("Jueves");
        days.add("Viernes");
        ArrayList<Time> initHours = new ArrayList<>();
        initHours.add(new Time("14:00"));
        initHours.add(new Time("14:00"));
        initHours.add(new Time("14:00"));
        ArrayList<Time> finishHours = new ArrayList<>();
        finishHours.add(new Time("15:00"));
        finishHours.add(new Time("15:00"));
        finishHours.add(new Time("15:00"));
        as.addCourse(name, credits, days, initHours, finishHours);
        assertEquals(name, as.getFirstCourse().getName());
    }//End testAddCourse1

    @Test
    public void testAddCourse2() throws InvalidTimeFormatException, OutOfTimeRangeException, IOException {
        setUpScenery5();
        String name = "Álgebra lineal";
        int credits = 3;
        ArrayList<String> days = new ArrayList<>();
        days.add("Lunes");
        days.add("Jueves");
        days.add("Viernes");
        ArrayList<Time> initHours = new ArrayList<>();
        initHours.add(new Time("14:00"));
        initHours.add(new Time("14:00"));
        initHours.add(new Time("14:00"));
        ArrayList<Time> finishHours = new ArrayList<>();
        finishHours.add(new Time("15:00"));
        finishHours.add(new Time("15:00"));
        finishHours.add(new Time("15:00"));
        as.addCourse(name, credits, days, initHours, finishHours);
        assertEquals(name, as.getFirstCourse().getNext().getName());
    }//End testAddCourse2

    @Test
    public void testDeleteCourse1() throws InvalidTimeFormatException, OutOfTimeRangeException, IOException {
        setUpScenery5();
        as.deleteCourse(as.getFirstCourse());
        assertNull(as.getFirstCourse());
    }//End testDeleteCourse1

    @Test
    public void testAddNotify1() throws InvalidTimeFormatException, OutOfTimeRangeException, IOException {
        setUpScenery5();
        Time toSendAtHour = new Time("14:00");
        Date date = new Date();
        String description = "Recordatorio";
        String title = "Recordatorio";
        Time initHour = new Time("16:00");
        Time finHour = new Time("18:00");
        String day = "Martes";
        as.addNotify(toSendAtHour, date, description, title, initHour, finHour, day, as.getFirstCourse());
        assertEquals(1, as.getNotifies().size());
    }//End testAddNotify1

    @Test
    public void testDeleteNotify1() throws InvalidTimeFormatException, OutOfTimeRangeException, IOException {
        setUpScenery6();
        as.deleteNotify(as.getNotifies().get(0));
        assertEquals(0, as.getNotifies().size());
    }//End testDeleteNotify1

    @Test
    public void testAddStudyPlan1() throws InvalidTimeFormatException, OutOfTimeRangeException, IOException {
        setUpScenery5();
        String title = "Prueba";
        String description = "Prueba";
        ArrayList<String> goals = new ArrayList<>();
        goals.add("A");
        goals.add("B");
        goals.add("C");
        goals.add("D");
        String day = "Viernes";
        Time init = new Time("10:00");
        Time fin = new Time("13:00");
        as.addStudyPlan(title, description, goals, day, init, fin, as.getFirstCourse());
        assertEquals(1, as.getStudyPlans().size());
    }//End testAddStudyPlan1

    @Test
    public void testDeleteStudyPlan1() throws InvalidTimeFormatException, OutOfTimeRangeException, IOException {
        setUpScenery7();
        as.deleteStudyPlan(as.getStudyPlans().get(0));
        assertEquals(0, as.getStudyPlans().size());
    }//End testDeleteStudyPlan1

}//End AcademicScheduleTest
