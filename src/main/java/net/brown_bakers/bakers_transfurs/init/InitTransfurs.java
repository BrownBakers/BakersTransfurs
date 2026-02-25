package net.brown_bakers.bakers_transfurs.init;

import net.brown_bakers.bakers_transfurs.BakersTransfurs;
import net.brown_bakers.bakers_transfurs.entity.LatexFox;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.init.ChangedRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;

public class InitTransfurs
{
	public static DeferredRegister<TransfurVariant<?>> TF_REGISTRY = ChangedRegistry.TRANSFUR_VARIANT.createDeferred(BakersTransfurs.MODID);
	//public static Map<String, RegistryObject<TransfurVariant<?>>> TF_REGISTRY_MAP;
	
	public static void init() {
	
	}
}
