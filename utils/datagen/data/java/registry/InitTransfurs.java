package net.kjentytek303.additional_transfurs.init;

import net.kjentytek303.additional_transfurs.AdditionalTransfurs;

 /*PERL_ENTITY_IMPORTS*/
import net.ltxprogrammer.changed.entity.variant.GenderedPair;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.init.ChangedRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.ltxprogrammer.changed.init.ChangedTransfurVariants.Gendered.registerPair;

public class InitTransfurs
{
	public static DeferredRegister<TransfurVariant<?>> TF_REGISTRY = ChangedRegistry.TRANSFUR_VARIANT.createDeferred(AdditionalTransfurs.MODID);
	
/*PERL_TRANSFURS*/
}
