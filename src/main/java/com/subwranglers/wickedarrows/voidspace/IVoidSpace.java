package com.subwranglers.wickedarrows.voidspace;

import net.minecraft.entity.CreatureEntity;

public interface IVoidSpace {

    /**
     * @param entity entity to capture into the void
     * @return true if the entity was captured, false if not (already full)
     */
    boolean captureMob(CreatureEntity entity);

    /**
     * Release the captured entity, allowing a new entity to be stored
     * @return the entity that was held in the Void Space
     */
    CreatureEntity releaseCapturedMob();

    /**
     * Clears the currently held mob (if any), allowing a new entity to be captured
     */
    default void consumeMob() {
        this.releaseCapturedMob();
    }

    /**
     * Check if this void currently has a captured mob
     * @return true if this has a captured mob, false if not
     */
    boolean hasCapturedMob();
}
