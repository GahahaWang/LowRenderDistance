package cc.gahaha.lrd.mixin;

import net.minecraft.client.server.IntegratedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(IntegratedServer.class)
public abstract class IntegratedServerMixin {

    // 單人模式的內建伺服器會用 Math.max(2, ...) 把模擬距離夾回 2。
    @ModifyArg(
            method = "tickServer",
            at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(II)I"),
            slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/client/Options;simulationDistance()Lnet/minecraft/client/OptionInstance;")),
            index = 0
    )
    private int lrd$lowerSimulationDistanceFloor(int floor) {
        return 1;
    }
}
