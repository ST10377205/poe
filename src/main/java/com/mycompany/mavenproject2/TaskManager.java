/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2;

import javax.swing.JOptionPane;

public class TaskManager {
    private Task[] tasks;
    private String[] developers;
    private String[] taskNames;
    private String[] taskIDs;
    private int[] taskDurations;
    private String[] taskStatuses;

    public TaskManager() {
        // Initialize with default test data
        addDefaultTestData();
    }

    public void addTasks(int numTasks) {
        tasks = new Task[numTasks];
        developers = new String[numTasks];
        taskNames = new String[numTasks];
        taskIDs = new String[numTasks];
        taskDurations = new int[numTasks];
        taskStatuses = new String[numTasks];

        for (int i = 0; i < tasks.length; i++) {
            String taskName = JOptionPane.showInputDialog("Enter Task Name:");
            String taskDescription = JOptionPane.showInputDialog("Enter Task Description:");
            while (taskDescription.length() > 50) {
                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters", "Invalid Task Description", JOptionPane.ERROR_MESSAGE);
                taskDescription = JOptionPane.showInputDialog("Enter Task Description:");
            }
            String developerDetails = JOptionPane.showInputDialog("Enter Developer Details (First and Last Name):");
            int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter Task Duration (hours):"));
            String[] statusOptions = {"To Do", "Doing", "Done"};
            String taskStatus = (String) JOptionPane.showInputDialog(null, "Select Task Status:", "Task Status", JOptionPane.QUESTION_MESSAGE, null, statusOptions, statusOptions[0]);

            Task task = new Task(taskName, taskDescription, developerDetails, taskDuration, taskStatus);
            tasks[i] = task;
            developers[i] = developerDetails;
            taskNames[i] = taskName;
            taskIDs[i] = task.createTaskID();
            taskDurations[i] = taskDuration;
            taskStatuses[i] = taskStatus;

            JOptionPane.showMessageDialog(null, "Task successfully captured", "Task Captured", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, task.printTaskDetails(), "Task Details", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void showReport() {
        StringBuilder report = new StringBuilder("Report:\n");
        for (int i = 0; i < tasks.length; i++) {
            report.append(String.format("Task %d: %s, %s, %d hours, Status: %s\n",
                    i + 1, taskNames[i], developers[i], taskDurations[i], taskStatuses[i]));
        }
        report.append(String.format("Total Task Duration: %d hours\n", Task.returnTotalHours(tasks)));
        JOptionPane.showMessageDialog(null, report.toString(), "Report", JOptionPane.INFORMATION_MESSAGE);
    }

    private void addDefaultTestData() {
        int numTasks = 3; // Example number of tasks
        tasks = new Task[numTasks];
        developers = new String[numTasks];
        taskNames = new String[numTasks];
        taskIDs = new String[numTasks];
        taskDurations = new int[numTasks];
        taskStatuses = new String[numTasks];

        tasks[0] = new Task("Login", "Create login functionality", "John Doe", 5, "To Do");
        tasks[1] = new Task("Register", "Create registration functionality", "Jane Smith", 8, "Doing");
        tasks[2] = new Task("Logout", "Create logout functionality", "Jim Brown", 3, "Done");

        for (int i = 0; i < tasks.length; i++) {
            developers[i] = tasks[i].getDeveloperDetails();
            taskNames[i] = tasks[i].getTaskName();
            taskIDs[i] = tasks[i].getTaskID();
            taskDurations[i] = tasks[i].getTaskDuration();
            taskStatuses[i] = tasks[i].getTaskStatus();
        }
    }

    public void manageTasks() {
        String menu = "Select an option:\n1) Display Done Tasks\n2) Display Longest Task\n3) Search by Task Name\n4) Search by Developer\n5) Delete Task by Name";
        String choice = JOptionPane.showInputDialog(menu);
        switch (choice) {
            case "1" -> displayDoneTasks();
            case "2" -> displayLongestTask();
            case "3" -> searchByTaskName();
            case "4" -> searchByDeveloper();
            case "5" -> deleteTaskByName();
            default -> JOptionPane.showMessageDialog(null, "Invalid option. Please enter 1, 2, 3, 4, or 5.", "Invalid Option", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayDoneTasks() {
        StringBuilder doneTasks = new StringBuilder("Done Tasks:\n");
        for (Task task : tasks) {
            if ("Done".equalsIgnoreCase(task.getTaskStatus())) {
                doneTasks.append(task.printTaskDetails()).append("\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, doneTasks.toString(), "Done Tasks", JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayLongestTask() {
        Task longestTask = tasks[0];
        for (Task task : tasks) {
            if (task.getTaskDuration() > longestTask.getTaskDuration()) {
                longestTask = task;
            }
        }
        JOptionPane.showMessageDialog(null, "Longest Task:\n" + longestTask.printTaskDetails(), "Longest Task", JOptionPane.INFORMATION_MESSAGE);
    }

    private void searchByTaskName() {
        String taskName = JOptionPane.showInputDialog("Enter Task Name to Search:");
        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(taskName)) {
                JOptionPane.showMessageDialog(null, "Task Found:\n" + task.printTaskDetails(), "Task Found", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task not found.", "Task Not Found", JOptionPane.ERROR_MESSAGE);
    }

    private void searchByDeveloper() {
        String developerName = JOptionPane.showInputDialog("Enter Developer Name to Search:");
        StringBuilder tasksByDeveloper = new StringBuilder("Tasks by Developer:\n");
        for (Task task : tasks) {
            if (task.getDeveloperDetails().equalsIgnoreCase(developerName)) {
                tasksByDeveloper.append(task.printTaskDetails()).append("\n\n");
            }
        }
        if (tasksByDeveloper.toString().equals("Tasks by Developer:\n")) {
            JOptionPane.showMessageDialog(null, "No tasks found for this developer.", "No Tasks Found", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, tasksByDeveloper.toString(), "Tasks by Developer", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void deleteTaskByName() {
        String taskName = JOptionPane.showInputDialog("Enter Task Name to Delete:");
        boolean found = false;
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i].getTaskName().equalsIgnoreCase(taskName)) {
                found = true;
                Task[] newTasks = new Task[tasks.length - 1];
                String[] newDevelopers = new String[developers.length - 1];
                String[] newTaskNames = new String[taskNames.length - 1];
                String[] newTaskIDs = new String[taskIDs.length - 1];
                int[] newTaskDurations = new int[taskDurations.length - 1];
                String[] newTaskStatuses = new String[taskStatuses.length - 1];

                System.arraycopy(tasks, 0, newTasks, 0, i);
                System.arraycopy(tasks, i + 1, newTasks, i, tasks.length - i - 1);
                System.arraycopy(developers, 0, newDevelopers, 0, i);
                System.arraycopy(developers, i + 1, newDevelopers, i, developers.length - i - 1);
                System.arraycopy(taskNames, 0, newTaskNames, 0, i);
                System.arraycopy(taskNames, i + 1, newTaskNames, i, taskNames.length - i - 1);
                System.arraycopy(taskIDs, 0, newTaskIDs, 0, i);
                System.arraycopy(taskIDs, i + 1, newTaskIDs, i, taskIDs.length - i - 1);
                System.arraycopy(taskDurations, 0, newTaskDurations, 0, i);
                System.arraycopy(taskDurations, i + 1, newTaskDurations, i, taskDurations.length - i - 1);
                System.arraycopy(taskStatuses, 0, newTaskStatuses, 0, i);
                System.arraycopy(taskStatuses, i + 1, newTaskStatuses, i, taskStatuses.length - i - 1);

                tasks = newTasks;
                developers = newDevelopers;
                taskNames = newTaskNames;
                taskIDs = newTaskIDs;
                taskDurations = newTaskDurations;
                taskStatuses = newTaskStatuses;
                JOptionPane.showMessageDialog(null, "Task deleted successfully.", "Task Deleted", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "Task not found.", "Task Not Found", JOptionPane.ERROR_MESSAGE);
        }
    }
}
