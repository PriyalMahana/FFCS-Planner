package ffcs.data;

import ffcs.model.Slot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlotRegistry {

    private static final Map<String, List<Slot>> registry = new HashMap<>();

    static {
        put("A1", "MON", 8, 9);
        put("A1", "WED", 8, 9);
        put("A1", "FRI", 8, 9);

        put("A2", "MON", 14, 15);
        put("A2", "WED", 14, 15);
        put("A2", "FRI", 14, 15);

        put("B1", "TUE", 8, 9);
        put("B1", "THU", 8, 9);

        put("B2", "TUE", 14, 15);
        put("B2", "THU", 14, 15);

        put("C1", "MON", 9, 10);
        put("C1", "WED", 9, 10);
        put("C1", "FRI", 9, 10);

        put("C2", "MON", 15, 16);
        put("C2", "WED", 15, 16);
        put("C2", "FRI", 15, 16);

        put("D1", "TUE", 9, 10);
        put("D1", "THU", 9, 10);

        put("D2", "TUE", 15, 16);
        put("D2", "THU", 15, 16);

        put("E1", "MON", 10, 11);
        put("E1", "WED", 10, 11);
        put("E1", "FRI", 10, 11);

        put("E2", "MON", 16, 17);
        put("E2", "WED", 16, 17);
        put("E2", "FRI", 16, 17);

        put("F1", "TUE", 10, 11);
        put("F1", "THU", 10, 11);

        put("F2", "TUE", 16, 17);
        put("F2", "THU", 16, 17);

        put("G1", "MON", 11, 12);
        put("G1", "THU", 11, 12);

        put("G2", "MON", 17, 18);
        put("G2", "THU", 17, 18);

        put("TA1", "TUE", 8, 9);
        put("TB1", "WED", 9, 10);
        put("TC1", "THU", 10, 11);
        put("TD1", "FRI", 11, 12);

        put("L1", "MON", 8, 9);
        put("L2", "MON", 9, 10);
        put("L3", "MON", 10, 11);
        put("L4", "MON", 11, 12);
        put("L5", "MON", 14, 15);
        put("L6", "MON", 15, 16);

        put("L7", "TUE", 8, 9);
        put("L8", "TUE", 9, 10);
        put("L9", "TUE", 10, 11);
        put("L10", "TUE", 11, 12);
        put("L11", "TUE", 14, 15);
        put("L12", "TUE", 15, 16);

        put("L13", "WED", 8, 9);
        put("L14", "WED", 9, 10);
        put("L15", "WED", 14, 15);
        put("L16", "WED", 15, 16);

        put("L17", "THU", 8, 9);
        put("L18", "THU", 9, 10);
        put("L19", "THU", 14, 15);
        put("L20", "THU", 15, 16);

        put("L21", "FRI", 8, 9);
        put("L22", "FRI", 9, 10);
        put("L23", "FRI", 14, 15);
        put("L24", "FRI", 15, 16);
    }

    private static void put(String code, String day, int start, int end) {
        registry.computeIfAbsent(code, k -> new ArrayList<>())
                .add(new Slot(code, day, start, end));
    }

    public static List<Slot> getSlots(String slotCode) {
        return registry.getOrDefault(slotCode.toUpperCase(), null);
    }

    public static boolean isValidSlot(String slotCode) {
        return registry.containsKey(slotCode.toUpperCase());
    }

    public static List<String> getAllSlotCodes() {
        List<String> codes = new ArrayList<>(registry.keySet());
        java.util.Collections.sort(codes);
        return codes;
    }
}
