/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.mavenproject2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    public void setUp() {
        Task.resetTaskCounter();
        taskManager = new TaskManager(4);
        // Test Data Task 1
        Task task1 = new Task("Create Login", "Login feature", "Mike Smith", 5, "To Do");
        // Test Data Task 2
        Task task2 = new Task("Create Add Features", "Add features", "Edward Harrison", 8, "Doing");
        // Test Data Task 3
        Task task3 = new Task("Create Reports", "Create reports", "Samantha Paulson", 2, "Done");
        // Test Data Task 4
        Task task4 = new Task("Add Arrays", "Add arrays feature", "Glenda Oberholzer", 11, "To Do");

        taskManager.addTask(0, task1);
        taskManager.addTask(1, task2);
        taskManager.addTask(2, task3);
        taskManager.addTask(3, task4);
    }

    @Test
    public void testDeveloperArrayCorrectlyPopulated() {
        String[] expectedDevelopers = {"Mike Smith", "Edward Harrison", "Samantha Paulson", "Glenda Oberholzer"};
        String[] actualDevelopers = taskManager.getDevelopers();
        assertArrayEquals(expectedDevelopers, actualDevelopers);
    }

    @Test
    public void testDisplayDeveloperAndDurationForTaskWithLongestDuration() {
        String expected = "Glenda Oberholzer, 11";
        String actual;
        actual = taskManager.displayDeveloperAndDurationForLongestTask();
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchTaskByName() {
        String expected = "Mike Smith, Create Login";
        String actual;
        actual = taskManager.searchTaskByName("Create Login");
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchAllTasksAssignedToDeveloper() {
        String expected = "Create Reports";
        String actual = taskManager.searchTasksByDeveloper("Samantha Paulson");
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteTaskFromArray() {
        taskManager.deleteTaskByName("Create Reports", null);
        assertFalse(taskManager.containsTask("Create Reports"));
    }

    @Test
    public void testDisplayReport() {
        String expected = "Full details of all captured tasks:\n" +
                          "Task Status: To Do\n" +
                          "Developer Details: Mike Smith\n" +
                          "Task Number: 0\n" +
                          "Task Name: Create Login\n" +
                          "Task Description: Login feature\n" +
                          "Task ID: CR:0:HSM\n" +
                          "Task Duration: 5 hours\n\n" +
                          "Task Status: Doing\n" +
                          "Developer Details: Edward Harrison\n" +
                          "Task Number: 1\n" +
                          "Task Name: Create Add Features\n" +
                          "Task Description: Add features\n" +
                          "Task ID: CR:1:SON\n" +
                          "Task Duration: 8 hours\n\n" +
                          "Task Status: Done\n" +
                          "Developer Details: Samantha Paulson\n" +
                          "Task Number: 2\n" +
                          "Task Name: Create Reports\n" +
                          "Task Description: Create reports\n" +
                          "Task ID: CR:2:LSO\n" +
                          "Task Duration: 2 hours\n\n" +
                          "Task Status: To Do\n" +
                          "Developer Details: Glenda Oberholzer\n" +
                          "Task Number: 3\n" +
                          "Task Name: Add Arrays\n" +
                          "Task Description: Add arrays feature\n" +
                          "Task ID: CR:3:RZER\n" +
                          "Task Duration: 11 hours\n\n";
        String actual;
        actual = taskManager.displayReport();
        assertEquals(expected, actual);
    }
}
  