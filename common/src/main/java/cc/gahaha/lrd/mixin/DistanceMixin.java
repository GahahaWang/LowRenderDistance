package cc.gahaha.lrd.mixin;

import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Options.class)
public abstract class DistanceMixin {

    // 建構子結尾會呼叫 load()，範圍必須在建立時就改，否則 options.txt 裡存的 1 會被判定為無效。
    @ModifyArg(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/OptionInstance$IntRange;<init>(IIZ)V"),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=options.renderDistance")),
            index = 0,
            require = 2,
            allow = 2
    )
    private int lrd$lowerDistanceMinimum(int minInclusive) {
        return 1;
    }
}
