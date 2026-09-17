package ffcs.service;

import ffcs.model.Course;
import ffcs.model.Slot;
import ffcs.model.Timetable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class TimetableExporter {

    private static final String[] DAYS = {"MON", "TUE", "WED", "THU", "FRI", "SAT"};
    private static final int HOUR_START = 8;
    private static final int HOUR_END = 19;

    public void printToConsole(Timetable timetable) {
        System.out.println("\n============================================================");
        System.out.println("               YOUR FFCS TIMETABLE                         ");
        System.out.println("============================================================");

        if (timetable.isEmpty()) {
            System.out.println("  No courses registered yet.");
            return;
        }

        String[][] grid = buildGrid(timetable);

        System.out.printf("%-10s", "Time");
        for (String day : DAYS) {
            System.out.printf("%-14s", day);
        }
        System.out.println("\n" + "-".repeat(10 + 14 * DAYS.length));

        for (int hour = HOUR_START; hour < HOUR_END; hour++) {
            int rowIndex = hour - HOUR_START;
            System.out.printf("%-10s", hour + ":00");
            for (int d = 0; d < DAYS.length; d++) {
                String cell = grid[rowIndex][d];
                if (cell == null) cell = "---";
                if (cell.length() > 12) cell = cell.substring(0, 12);
                System.out.printf("%-14s", cell);
            }
            System.out.println();
        }

        System.out.println("-".repeat(10 + 14 * DAYS.length));
        System.out.println("Total Credits Registered: " + timetable.getTotalCredits() + "\n");
    }

    public void printCourseList(Timetable timetable) {
        System.out.println("\n=== REGISTERED COURSES ===");
        if (timetable.isEmpty()) {
            System.out.println("No courses added yet.");
            return;
        }
        int i = 1;
        for (Course c : timetable.getRegisteredCourses()) {
            System.out.println(i + ". " + c.toString());
            i++;
        }
        System.out.println("Total Credits: " + timetable.getTotalCredits() + "\n");
    }

    public void exportToFile(Timetable timetable, String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write("FFCS TIMETABLE PLANNER");
            bw.newLine();
            bw.write("============================================================");
            bw.newLine();
            bw.newLine();

            if (timetable.isEmpty()) {
                bw.write("No courses registered.");
                bw.newLine();
            } else {
                String[][] grid = buildGrid(timetable);

                bw.write(String.format("%-10s", "Time"));
                for (String day : DAYS) {
                    bw.write(String.format("%-14s", day));
                }
                bw.newLine();
                bw.write("-".repeat(10 + 14 * DAYS.length));
                bw.newLine();

                for (int hour = HOUR_START; hour < HOUR_END; hour++) {
                    int rowIndex = hour - HOUR_START;
                    bw.write(String.format("%-10s", hour + ":00"));
                    for (int d = 0; d < DAYS.length; d++) {
                        String cell = grid[rowIndex][d];
                        if (cell == null) cell = "---";
                        if (cell.length() > 12) cell = cell.substring(0, 12);
                        bw.write(String.format("%-14s", cell));
                    }
                    bw.newLine();
                }

                bw.write("-".repeat(10 + 14 * DAYS.length));
                bw.newLine();
                bw.newLine();

                bw.write("COURSE DETAILS:");
                bw.newLine();
                int num = 1;
                for (Course c : timetable.getRegisteredCourses()) {
                    bw.write(num + ". " + c.toString());
                    bw.newLine();
                    num++;
                }
                bw.newLine();
                bw.write("Total Credits: " + timetable.getTotalCredits());
                bw.newLine();
            }
            System.out.println("Timetable saved to: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    private String[][] buildGrid(Timetable timetable) {
        int rows = HOUR_END - HOUR_START;
        int cols = DAYS.length;
        String[][] grid = new String[rows][cols];

        Map<String, Integer> dayIndex = new LinkedHashMap<>();
        for (int i = 0; i < DAYS.length; i++) {
            dayIndex.put(DAYS[i], i);
        }

        for (Course course : timetable.getRegisteredCourses()) {
            String label = course.getCourseName().split(" ")[0];
            for (Slot slot : course.getSlots()) {
                Integer colIndex = dayIndex.get(slot.getDay());
                if (colIndex == null) continue;
                for (int hr = slot.getStartHour(); hr < slot.getEndHour(); hr++) {
                    int rowIndex = hr - HOUR_START;
                    if (rowIndex >= 0 && rowIndex < rows) {
                        grid[rowIndex][colIndex] = label;
                    }
                }
            }
        }
        return grid;
    }
}
