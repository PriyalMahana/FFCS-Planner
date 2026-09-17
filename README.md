# FFCS Planner

A simple Java console app to help VIT students plan their timetable and avoid slot clashes during FFCS registration.

I built this because tracking overlapping slots manually (like A1, L1, TA1, etc.) is really confusing and often leads to registration errors. This program lets you test your course basket locally before the actual FFCS portal opens.

## Features
- Checks for time slot clashes between theory, lab, and tutorial slots.
- Verifies if you are meeting the standard minimum 16 and maximum 27 credit limits.
- Prints a clean visual timetable grid (Monday to Saturday).
- Exports the timetable to a text file for easy reference.

## Requirements
- Java (JDK 8 or above) installed on your computer.

## How to run the project

You don't need any special IDE to run this. Just use your standard terminal/command prompt.

1. Open your terminal and go into the project folder:
```bash
cd FFCS-Planner
```

2. Compile all the java files into an `out` folder by running this command:
```bash
javac -d out src/ffcs/exception/*.java src/ffcs/model/*.java src/ffcs/data/*.java src/ffcs/service/*.java src/ffcs/Main.java
```
*(Note for Windows CMD users: if you get an error with the forward slashes, try replacing them with backslashes `\`)*

3. Run the main program:
```bash
java -cp out ffcs.Main
```

## How to use
Once the app starts, just follow the text menu on the screen. 
- Press `1` to add a course. 
- Type in the slots you want (for example: `A1,TA1`), and the app will instantly tell you if there is a clash with your existing courses. 
- Press `3` to view your timetable grid at any time.
