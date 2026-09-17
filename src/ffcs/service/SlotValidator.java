package ffcs.service;

import ffcs.exception.CreditLimitException;
import ffcs.exception.SlotClashException;
import ffcs.model.Course;
import ffcs.model.Slot;
import ffcs.model.Timetable;
import java.util.List;

public class SlotValidator {

    private static final int MIN_CREDITS = 16;
    private static final int MAX_CREDITS = 27;

    public void validate(Course newCourse, Timetable timetable) throws SlotClashException, CreditLimitException {
        int creditsAfterAdding = timetable.getTotalCredits() + newCourse.getCredits();
        if (creditsAfterAdding > MAX_CREDITS) {
            throw new CreditLimitException(
                "Cannot add '" + newCourse.getCourseName() + "'. Total credits would become " 
                + creditsAfterAdding + " which exceeds the limit of " + MAX_CREDITS + "."
            );
        }

        List<Slot> newSlots = newCourse.getSlots();
        for (Course existing : timetable.getRegisteredCourses()) {
            for (Slot existingSlot : existing.getSlots()) {
                for (Slot newSlot : newSlots) {
                    if (newSlot.clashesWith(existingSlot)) {
                        throw new SlotClashException(
                            "Slot clash detected! '" + newCourse.getCourseName() + 
                            "' clashes with '" + existing.getCourseName() + "'."
                        );
                    }
                }
            }
        }
    }

    public boolean isBelowMinimum(Timetable timetable) {
        return timetable.getTotalCredits() < MIN_CREDITS;
    }

    public int getMinCredits() { return MIN_CREDITS; }
    public int getMaxCredits() { return MAX_CREDITS; }
}
