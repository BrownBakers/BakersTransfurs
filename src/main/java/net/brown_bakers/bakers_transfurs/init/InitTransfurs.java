package net.brown_bakers.bakers_transfurs.init;

import net.brown_bakers.bakers_transfurs.BakersTransfurs;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.init.ChangedRegistry;
import net.minecraftforge.registries.DeferredRegister;

public class InitTransfurs
{
	public static DeferredRegister<TransfurVariant<?>> TF_REGISTRY = ChangedRegistry.TRANSFUR_VARIANT.createDeferred(BakersTransfurs.MODID);
	public static void init() {
	
	}
}
