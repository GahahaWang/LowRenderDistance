package cc.gahaha.lrd.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Pseudo
@Mixin(targets = "de.johni0702.minecraft.bobby.SodiumConfigEntrypoint", remap = false)
public abstract class BobbyDistanceMixin {

    // Bobby 會用自己的範圍覆蓋 Sodium 的顯示距離滑桿，下限寫死為 2，存檔裡的 1 會因此被重設。
    @ModifyArg(
            method = "lambda$registerConfigLate$0",
            at = @At(value = "INVOKE", target = "Lnet/caffeinemc/mods/sodium/api/config/option/Range;<init>(III)V"),
            index = 0
    )
    private static int lrd$lowerRenderDistanceMinimum(int min) {
        return 1;
    }
}
