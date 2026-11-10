package cc.gahaha.lrd.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Options.class)
public abstract class DistanceMixin {

    @Shadow @Final @Mutable
    private OptionInstance<Integer> simulationDistance;

    @Shadow @Final @Mutable
    private OptionInstance<Integer> renderDistance;

}