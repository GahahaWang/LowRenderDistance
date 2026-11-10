package cc.gahaha.lrd.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.server.IntegratedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(IntegratedServer.class)
public abstract class IntegratedServerMixin {

    // 內建伺服器會用 Math.max(2, ...) 把模擬距離夾回 2，Bobby 的 View Distance Overwrite 也會把它一起蓋掉。
    @ModifyExpressionValue(
            method = "tickServer",
            at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(II)I"),
            slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/client/Options;simulationDistance()Lnet/minecraft/client/OptionInstance;"))
    )
    private int lrd$useConfiguredSimulationDistance(int original) {
        return Minecraft.getInstance().options.simulationDistance().get();
    }
}
