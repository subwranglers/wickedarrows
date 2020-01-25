package com.subwranglers.wickedarrows.voidspace;

import com.subwranglers.wickedarrows.WickedArrows;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class VoidSpaceCapability {

    @CapabilityInject(IVoidSpace.class)
    public static Capability<IVoidSpace> VOID_SPACE_HANDLER = null;

    public static void register(){

        CapabilityManager.INSTANCE.register(
                IVoidSpace.class,
                new Capability.IStorage<IVoidSpace>(){

                    @Nullable
                    @Override
                    public INBT writeNBT(Capability<IVoidSpace> capability, IVoidSpace instance, Direction side) {

                        return null;
                    }

                    @Override
                    public void readNBT(Capability<IVoidSpace> capability, IVoidSpace instance, Direction side, INBT nbt) {

                    }
                },
                VoidSpace::new );
    }

    //TODO: Implement serialization code for this capability so captured entities will persist when the game is saved
    private static class VoidSpaceProvider implements ICapabilityProvider { //ICapabilitySerializable<CompoundNBT> {

        LazyOptional<IVoidSpace> handler;
        PlayerEntity owner;

        public VoidSpaceProvider(PlayerEntity owner) {
            this.owner = owner;
        }

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
            if(cap == VoidSpaceCapability.VOID_SPACE_HANDLER){
                if(handler == null)
                    handler = LazyOptional.of(() -> new VoidSpace().setOwner(owner));
                return handler.cast();
            }
            return LazyOptional.empty();
        }

        public void invalidate() {
            if(handler != null)
                handler.invalidate();
        }

//        @Override
//        public CompoundNBT serializeNBT() {
//            return null;
//        }
//
//        @Override
//        public void deserializeNBT(CompoundNBT nbt) {
//
//        }
    }

    @Mod.EventBusSubscriber(modid = WickedArrows.MOD_ID)
    public static class EventHandlers {

        @SubscribeEvent
        public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event) {
            if(!(event.getObject() instanceof ServerPlayerEntity))
                return;

            VoidSpaceProvider voidProvider = new VoidSpaceProvider((PlayerEntity) event.getObject());

            event.addCapability(new ResourceLocation(WickedArrows.MOD_ID, "voidspace_capability"), voidProvider);
            event.addListener(voidProvider::invalidate);
        }
    }
}
