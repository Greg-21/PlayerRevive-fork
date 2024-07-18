package team.creative.playerrevive.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.server.level.ServerPlayer;
import team.creative.playerrevive.PlayerRevive;
import team.creative.playerrevive.api.PlayerExtender;
import team.creative.playerrevive.server.PlayerReviveServer;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin implements PlayerExtender {
    
    @Unique
    private float overkill;
    
    @Inject(method = "getPermissionLevel()I", at = @At("HEAD"), require = 1, cancellable = true)
    public void getPermissionLevel(CallbackInfoReturnable<Integer> callback) {
        if (PlayerRevive.CONFIG.bleeding.changePermissionLevel && PlayerReviveServer.isBleeding((ServerPlayer) (Object) this))
            callback.setReturnValue(PlayerRevive.CONFIG.bleeding.permissionLevel);
    }
    
    @Override
    public float getOverkill() {
        return overkill;
    }
    
    @Override
    public void setOverkill(float overkill) {
        this.overkill = overkill;
    }
    
}
