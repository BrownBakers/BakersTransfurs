package net.kjentytek303.additional_transfurs.init;

 /*PERL_RENDERER_IMPORTS*/
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.ltxprogrammer.changed.init.ChangedEntityRenderers.registerHumanoid;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class InitRenderers {
	
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {

/*PERL_RENDERERS*/
	}
}
