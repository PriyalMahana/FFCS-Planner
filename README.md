# FFCS Slot Clash Validator & Timetable Planner

## Project Overview
This is a Java console application built to help VIT students plan their semester registrations smoothly. During FFCS (Fully Flexible Credit System) registration, it can be difficult to track overlapping slots. This application validates course time slots to prevent clashes, enforces university credit limits (16 to 27 credits), and prints a visual timetable grid.

## Prerequisites
- **Java Development Kit (JDK)** version 8 or higher must be installed on your system.
- A basic terminal or command prompt.

## How to Compile and Run 

This project is fully executable via the command line and requires no external libraries or GUI setup.

### Step 1: Navigate to the project directory
Open your terminal and `cd` into the root folder of this repository:
```bash
cd VIT-FFCS-Planner
```

### Step 2: Compile the Source Code
We need to compile the `.java` files from the `src` folder and output the compiled `.class` files into a new `out` directory.

Run this command (Mac/Linux/PowerShell):
```bash
javac -d out src/ffcs/exception/*.java src/ffcs/model/*.java src/ffcs/data/*.java src/ffcs/service/*.java src/ffcs/Main.java
```
*(Note: If you are using standard Windows Command Prompt (CMD), use backslashes `\` instead of forward slashes `/`)*

### Step 3: Run the Application
Once compiled successfully, run the application using:
```bash
java -cp out ffcs.Main
```

## Usage Instructions
When the app launches, you will be greeted with an interactive menu:
1. **Add a Course:** Enter details like course name, credits, and slot codes (e.g., `A1,TA1` or `L1,L2`). The app will check for clashes.
2. **View Timetable Grid:** Prints a visual Monday-Saturday schedule.
3. **Save Timetable:** Exports your generated timetable to a text file in the `output` directory.
