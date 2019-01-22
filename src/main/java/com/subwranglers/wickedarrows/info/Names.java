package com.subwranglers.wickedarrows.info;

import com.subwranglers.wickedarrows.WickedArrows;

public class Names {
    private static final String PRE_BLOCK = "block_";
    private static final String PRE_ITEM = "item_";
    private static final String PRE_ENTITY = "entity_";

    // Blocks & Items ---------------------------
    public static final String INVOKED_ICE = "invoked_ice";
    public static final String SHOT_ARR2W = "shot_arr2w";
    public static final String SHOT_ARR3W = "shot_arr3w";
    public static final String SHOT_ARR4W = "shot_arr4w";
    public static final String SHOT_ARR5W = "shot_arr5w";
    public static final String SHOT_ARR6W = "shot_arr6w";
    public static final String SHOT_ARR7W = "shot_arr7w";
    public static final String SHOT_ARR8W = "shot_arr8w";
    public static final String SHOT_ARR9W = "shot_arr9w";
    public static final String ICE_ARROW = "ice_arrow";
    public static final String TORCH_ARROW = "torch_arrow";

    // Sounds -----------------------------------
    public static final String ICE_CRACKLING = "ice_crackling";
    public static final String ICE_EXPLOSION = "ice_explosion";

    // Other ------------------------------------
    public static final String ICE_POTION = "ice_potion";

    /**
     * Pass flag to prepend "block_" to the name.
     */
    public static final int BLOCK = 0b00000001;

    /**
     * Pass flag to prepend "item_" to the name.
     */
    public static final int ITEM = 0b00000010;

    /**
     * Pass flag to prepend "entity_" to the name.
     */
    public static final int ENTITY = 0b00000100;

    /**
     * Pass flag to qualify the name with {@link WickedArrows#MOD_ID}.
     */
    public static final int QUALIFY = 0b00001000;

    /**
     * Using bitwise OR-ed flags, prepends information to the provided <var>name</var>.
     *
     * @param name  the name to modify
     * @param flags bitwise flags. {@link #QUALIFY} can be mixed with any flag, but the others are singularly applied in
     *              priority: <ol>
     *              <li>{@link #BLOCK}</li>
     *              <li>{@link #ITEM}</li>
     *              <li>{@link #ENTITY}</li>
     *              </ol>
     * @return returns <var>name</var> with information prepended
     */
    public static String name(String name, int flags) {
        // Prepend either block, item or entity if asked, but only 1
        if ((flags & BLOCK) == flags) name = PRE_BLOCK + name;
        else if ((flags & ITEM) == flags) name = PRE_ITEM + name;
        else if ((flags & ENTITY) == flags) name = PRE_ENTITY + name;

        // Add mod ID to name
        if ((flags & QUALIFY) == flags) name = WickedArrows.MOD_ID + ":" + name;

        return name;
    }
}
