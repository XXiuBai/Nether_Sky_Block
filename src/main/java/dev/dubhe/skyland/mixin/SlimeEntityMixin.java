package dev.dubhe.skyland.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(SlimeEntity.class)
public class SlimeEntityMixin {

    private static final Identifier BASALT_DELTAS = new Identifier("basalt_deltas");

    @Inject(
            method = "canSpawn",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void canSpawnMixin(EntityType<SlimeEntity> type, WorldAccess world, SpawnReason spawnReason,
            BlockPos pos, Random random, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            return;
        }
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            if (world.getBiome(pos).matchesId(SlimeEntityMixin.BASALT_DELTAS)) {
                cir.setReturnValue(true);
            }
        }
    }

}
