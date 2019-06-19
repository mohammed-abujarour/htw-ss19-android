package de.htwberlin.masterdetails.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MensaModel {
    /**
     * An array of sample items.
     */
    public static final List<Mensa> MENSEN = new ArrayList<Mensa>();

    /**
     * A map of sample items, by ID.
     */
    public static final Map<Integer, Mensa> ITEM_MAP = new HashMap<Integer, Mensa>();

    private static final int COUNT = 10;
    static {
        // Load Mensen from DB .
        for (int i = 1; i <= COUNT; i++) {
            Mensa mensa = new Mensa("Mensa "+ i, i);
            mensa.addDetails("Address", "Hauptstr. " + i + ", 1055"+(i-1) + " Berlin");
            mensa.addDetails("Capacity", new Random().nextInt(200) +" seats");
            addMensa(mensa);
        }
    }

    private static void addMensa(Mensa mensa) {
        MENSEN.add(mensa);
        ITEM_MAP.put(mensa.getId(), mensa);
    }

}
