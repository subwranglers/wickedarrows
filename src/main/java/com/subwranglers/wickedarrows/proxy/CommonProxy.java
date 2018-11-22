package com.subwranglers.wickedarrows.proxy;

import com.subwranglers.wickedarrows.registry.BlocksCommon;
import com.subwranglers.wickedarrows.registry.EntitiesCommon;
import com.subwranglers.wickedarrows.registry.ItemsCommon;

public class CommonProxy {

    public void preInit() {
        BlocksCommon.preInit();
        ItemsCommon.preInit();
        EntitiesCommon.preInit();
    }

    public void init() {
        BlocksCommon.init();
        ItemsCommon.init();
        EntitiesCommon.init();
    }

    public void postInit() {
        BlocksCommon.postInit();
        ItemsCommon.postInit();
        EntitiesCommon.postInit();
    }
}
