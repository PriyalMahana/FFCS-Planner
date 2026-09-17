package ffcs;

import ffcs.data.SlotRegistry;
import ffcs.exception.CreditLimitException;
import ffcs.exception.SlotClashException;
import ffcs.model.Course;
import ffcs.model.Slot;
import ffcs.model.Timetable;
import ffcs.service.SlotValidator;
import ffcs.service.TimetableExporter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Timetable timetable = new Timetable();
    static SlotValidator validator = new SlotValidator();
    static TimetableExporter exporter = new TimetableExporter();

    public static void main(String[] args) {
        System.out.println("============================================");
        System.out.println("  FFCS TIMETABLE PLANNER - VIT BHOPAL      ");
        System.out.println("============================================");
        System.out.println();

        boolean running = true;
        while (running) {
            printMenu();
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    addCourse();
                    break;
                case "2":
                    removeCourse();
                    break;
                case "3":
                    exporter.printToConsole(timetable);
                    break;
                case "4":
                    exporter.printCourseList(timetable);
                    break;
                case "5":
                    showCreditStatus();
                    break;
                case "6":
                    showAvailableSlots();
                    break;
                case "7":
                    saveToFile();
                    break;
                case "0":
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        sc.close();
    }

    static void printMenu() {
        System.out.println("--------------------------------------------");
        System.out.println("  1. Add a Course");
        System.out.println("  2. Remove a Course");
        System.out.println("  3. View Timetable Grid");
        System.out.println("  4. View Course List");
        System.out.println("  5. Check Credit Status");
        System.out.println("  6. Show Available Slot Codes");
        System.out.println("  7. Save Timetable to File");
        System.out.println("  0. Exit");
        System.out.println("--------------------------------------------");
        System.out.print("Choose an option: ");
    }

    static void addCourse() {
        System.out.println("\n-- Add New Course --");

        System.out.print("Course Code: ");
        String code = sc.nextLine().trim().toUpperCase();

        System.out.print("Course Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Faculty Name: ");
        String faculty = sc.nextLine().trim();

        int credits = 0;
        while (credits <= 0) {
            System.out.print("Credits: ");
            try {
                credits = Integer.parseInt(sc.nextLine().trim());
                if (credits <= 0) System.out.println("Credits must be > 0.");
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }

        System.out.print("Type (Theory / Lab): ");
        String type = sc.nextLine().trim();

        System.out.print("Slots (comma separated): ");
        String[] slotCodes = sc.nextLine().trim().toUpperCase().split(",");

        List<Slot> resolvedSlots = new ArrayList<>();
        boolean slotError = false;

        for (String rawCode : slotCodes) {
            String slotCode = rawCode.trim();
            if (!SlotRegistry.isValidSlot(slotCode)) {
                System.out.println("Unknown slot: " + slotCode);
                slotError = true;
                break;
            }
            resolvedSlots.addAll(SlotRegistry.getSlots(slotCode));
        }

        if (slotError) {
            return;
        }

        Course newCourse = new Course(code, name, faculty, credits, type, resolvedSlots);

        try {
            validator.validate(newCourse, timetable);
            timetable.addCourse(newCourse);
            System.out.println("Course added! Credits: " + timetable.getTotalCredits());

            if (validator.isBelowMinimum(timetable)) {
                System.out.println("Note: You need at least 16 credits.");
            }
        } catch (SlotClashException | CreditLimitException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    static void removeCourse() {
        System.out.println("\n-- Remove a Course --");
        if (timetable.isEmpty()) {
            System.out.println("No courses registered.");
            return;
        }
        System.out.print("Enter Course Code to remove: ");
        String code = sc.nextLine().trim().toUpperCase();

        if (timetable.removeCourse(code)) {
            System.out.println("Removed. Credits: " + timetable.getTotalCredits());
        } else {
            System.out.println("Course not found.");
        }
    }

    static void showCreditStatus() {
        int total = timetable.getTotalCredits();
        System.out.println("\n-- Credit Status --");
        System.out.println("Registered : " + total);
        System.out.println("Minimum    : " + validator.getMinCredits());
        System.out.println("Maximum    : " + validator.getMaxCredits());
    }

    static void showAvailableSlots() {
        System.out.println("\n-- Available Slot Codes --");
        List<String> codes = SlotRegistry.getAllSlotCodes();
        int count = 0;
        for (String code : codes) {
            System.out.printf("%-8s", code);
            if (++count % 8 == 0) System.out.println();
        }
        System.out.println();
    }

    static void saveToFile() {
        java.io.File outputDir = new java.io.File("output");
        if (!outputDir.exists()) outputDir.mkdir();
        exporter.exportToFile(timetable, "output/my_timetable.txt");
    }
}
