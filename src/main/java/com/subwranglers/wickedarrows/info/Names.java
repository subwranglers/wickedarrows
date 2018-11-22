package com.subwranglers.wickedarrows.info;

import com.subwranglers.wickedarrows.WickedArrows;
import util.S;

public class Names {
    private static final String PRE_BLOCK = "block_";
    private static final String PRE_ITEM = "item_";
    private static final String PRE_ENTITY = "entity_";

    /*

        Blocks

     */

    public static final String INVOKED_ICE = "invoked_ice";

    /*

        Items

     */
    public static final String ICE_ARROW = "ice_arrow";

    /*

        Potions

     */

    public static final String ICE_POTION = "ice_potion";

    /**
     * Pass flag to prepend "block_" to the name.
     */
    public static final int BLK = 0b00000001;

    /**
     * Pass flag to prepend "item_" to the name.
     */
    public static final int ITEM = 0b00000010;

    /**
     * Pass flag to prepend "entity_" to the name.
     */
    public static final int ENT = 0b00000100;

    /**
     * Pass flag to qualify the name with {@link WickedArrows#MOD_ID}.
     */
    public static final int QUAL = 0b00001000;

    /**
     * Using bitwise OR-ed flags, prepends information to the provided <var>name</var>.
     * @param name the name to modify
     * @param flags bitwise flags. If {@link #ITEM} and {@link #ENT} are passed together, ENT will be ignored and ITEM
     *              will be applied.
     * @return returns <var>name</var> with information prepended
     */
    public static String name(String name, int flags) {
        // Prepend either block, item or entity if asked, but only 1
        if ((flags & BLK) == flags) name = PRE_BLOCK + name;
        else if ((flags & ITEM) == flags) name = PRE_ITEM + name;
        else if ((flags & ENT) == flags) name = PRE_ENTITY + name;

        // Add mod ID to name
        if ((flags & QUAL) == flags) name = S.qualify(name);

        return name;
    }
}
