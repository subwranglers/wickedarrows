package com.subwranglers.wickedarrows.proxy;

import com.subwranglers.wickedarrows.registry.BlocksClient;
import com.subwranglers.wickedarrows.registry.EntitiesClient;
import com.subwranglers.wickedarrows.registry.ItemsClient;

public class ClientProxy extends CommonProxy {

    public void preInit() {
        super.preInit();
        BlocksClient.preInit();
        ItemsClient.preInit();
        EntitiesClient.preInit();
    }

    public void init() {
        super.init();
        BlocksClient.init();
        ItemsClient.init();
        EntitiesClient.init();
    }

    public void postInit() {
        super.postInit();
        BlocksClient.postInit();
        ItemsClient.postInit();
        EntitiesClient.postInit();
    }
}
