package cc.gahaha.lrd.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

// NeoForge 版的 Sodium 是 jar-in-jar，編譯時不在 classpath 上，所以目標用字串指定。
@Pseudo
@Mixin(targets = "net.caffeinemc.mods.sodium.client.gui.SodiumConfigBuilder", remap = false)
public abstract class SodiumDistanceMixin {

    // 只有 Sodium 0.9.2 以前需要：0.9.3 起滑桿範圍直接讀原版設定（DistanceMixin 已改成 1），找不到注入點是正常的。
    @ModifyArg(
            method = "buildGeneralPage",
            at = @At(value = "INVOKE", target = "Lnet/caffeinemc/mods/sodium/api/config/structure/IntegerOptionBuilder;setRange(III)Lnet/caffeinemc/mods/sodium/api/config/structure/IntegerOptionBuilder;"),
            slice = @Slice(
                    from = @At(value = "CONSTANT", args = "stringValue=sodium:general.render_distance"),
                    to = @At(value = "CONSTANT", args = "stringValue=sodium:general.gamma")
            ),
            index = 0,
            require = 0,
            allow = 2
    )
    private int lrd$lowerDistanceMinimum(int min) {
        return 1;
    }
}
