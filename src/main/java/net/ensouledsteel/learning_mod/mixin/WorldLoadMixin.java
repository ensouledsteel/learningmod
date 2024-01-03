package net.ensouledsteel.learning_mod.mixin;

import net.ensouledsteel.learning_mod.LearningMod;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class WorldLoadMixin {
	@Inject(method = "loadWorld", at = @At("TAIL"))
	public void onInit(CallbackInfo ci) {
		LearningMod.LOGGER.info("Amazing, the world loaded...");
	}
}
