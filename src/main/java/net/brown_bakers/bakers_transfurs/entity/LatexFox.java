package net.brown_bakers.bakers_transfurs.entity;

import net.brown_bakers.bakers_transfurs.init.IEntityInit;
import net.brown_bakers.bakers_transfurs.init.InitUtils;
import net.ltxprogrammer.changed.ability.AbstractAbility;
import net.ltxprogrammer.changed.entity.*;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.init.ChangedAttributes;
import net.ltxprogrammer.changed.init.ChangedEntities;

import net.ltxprogrammer.changed.init.ChangedSounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static net.brown_bakers.bakers_transfurs.init.InitTransfurs.TF_REGISTRY;

public class LatexFox extends ChangedEntity implements IEntityInit
{
	
	/***********************************************************
	 * READ THE FUCKING MANUAL BEFORE COMMENTING IT'S UNREADABLE.
	 *
	 * Static Methods
	 *
	 * static Builder<LatexFox> getInitBuilder() - returns builder for the entity.
	 * TransfurMode getTransfurMode() - returns transfur mode.
	 * static void init(FMLCommonSetupEvent) - registers both the entity and a variant
	 * static RegistryObject<TransfurVariant<LatexFox>> getLatexFoxVariant() - accesses a variant RObject
	 * static RegistryObject<EntityType<LatexFox>> getLatexFox() - accesses the entity RObject
	 * static Builder<LatexFox> getInitBuilder() - returns entity builder
	 * static TransfurVariant<LatexFox> getTFInitBuilder() - returns TransfurVariantBuilder
	 *
	 *
	 * Instance methods:
	 * (constructor) (EntityType < ? extends ChangedEntity > type, Level level) - standard constructor.
	 * TransfurMode getTransfurMode() - returns transfur mode.
	 * void setAttributes( AttributeMap attributes ) - sets attributes
	 *
	 *.
	 * Fields:
	 * Hold constants for init methods
	 *
	 ***********************************************************/
	

	
	
	//TODO: I should definitely read java and FML docs before doing registration in the exact opposite way to what's recommended.
	//The idea is to force-reference a method call inside a class.
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		if (inited) {return;}
		inited = true;
		LATEX_FOX = InitUtils.getInitRObject(
			   name,
			   color1,
			   color2,
			   getInitBuilder(),
			   ChangedEntities::overworldOnly,
			   SpawnPlacements.Type.ON_GROUND,
			   LatexFox::checkEntitySpawnRules,
			   ChangedEntity::createLatexAttributes
		);
		//TODO: Init abilities
		LATEX_FOX_VARIANT = TF_REGISTRY.register(name, LatexFox::getTFInitBuilder);
	}
	
	public static RegistryObject<TransfurVariant<LatexFox>> getLatexFoxVariant() {
		return LATEX_FOX_VARIANT;
	}
	
	public static RegistryObject<EntityType<LatexFox>> getLatexFox() {
		return LATEX_FOX;
	}
	
	public static EntityType.Builder<LatexFox> getInitBuilder() {
		
		return EntityType.Builder
			   .of(LatexFox::new, MobCategory.MONSTER)
			   .clientTrackingRange(10)
			   .sized(0.7F, 1.93F);
	}
	
	public static TransfurVariant<LatexFox> getTFInitBuilder()
	{
		return new TransfurVariant<>(
			   getLatexFox(),
			   breatheMode,
			   canGlide,
			   extraJumpCharges,
			   canClimb,
			   visionType,
			   miningStrength,
			   itemUseMode,
			   scares,
			   transfurMode,
			   abilities,
			   cameraZOffset,
			   sound
		);
	}
	
	public LatexFox(EntityType<? extends ChangedEntity> type, Level level) { super(type, level); }
	
	@Override
	public TransfurMode getTransfurMode() {
		return TransfurMode.REPLICATION;
	}
	
	@Override
	protected void setAttributes (AttributeMap attributes) {
		super.setAttributes(attributes);
		attributes.getInstance(Attributes.MOVEMENT_SPEED).setBaseValue(1.05);
		attributes.getInstance(ChangedAttributes.SNEAK_SPEED.get()).setBaseValue(1.5D);
		attributes.getInstance(ChangedAttributes.JUMP_STRENGTH.get()).setBaseValue(1.5);
		attributes.getInstance(ChangedAttributes.FALL_RESISTANCE.get()).setBaseValue(1.25);
	}
	
	public static final String name = "latex_fox";
	public static final int color1 = 0x624f13;
	public static final int color2 = 0xb4a165;
	private static RegistryObject<EntityType<LatexFox>> LATEX_FOX;
	public static final TransfurVariant.BreatheMode breatheMode= TransfurVariant.BreatheMode.NORMAL;
	public static final boolean canGlide = false;
	public static final int extraJumpCharges = 0;
	public static final boolean canClimb = false;
	public static final VisionType visionType= VisionType.NORMAL;
	public static final MiningStrength miningStrength = MiningStrength.NORMAL;
	//TODO: Extend functionality someday
	public static final UseItemMode itemUseMode = UseItemMode.NORMAL;
	public static final BiPredicate<LatexFox, PathfinderMob> scares = null;
	public static final TransfurMode transfurMode = TransfurMode.REPLICATION;
	//TODO: Break encapsulation.
	public static final List<Function<EntityType<?>, ? extends AbstractAbility<?>>> abilities = new ArrayList<>();
	public static final float cameraZOffset = 0.0f;
	public static final ResourceLocation sound = ChangedSounds.TRANSFUR_BY_LATEX.getId();
	private static boolean inited = false;
	private static RegistryObject<TransfurVariant<LatexFox>> LATEX_FOX_VARIANT;
	
}