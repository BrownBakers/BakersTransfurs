package net.kjentytek303.additional_transfurs.entity;

import net.kjentytek303.additional_transfurs.init.IEntityInit;
import net.kjentytek303.additional_transfurs.init.utils.*;
import net.ltxprogrammer.changed.entity.*;
import net.ltxprogrammer.changed.entity.latex.LatexType;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.init.*;

import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.registries.RegistryObject;


import static net.kjentytek303.additional_transfurs.init.InitEntities.LATEX_FOX;

import org.jetbrains.annotations.Nullable;


public class LatexFox extends ChangedEntity /*PERL_IMPLEMENTS*/
{

	/*PERL_ABSTRACT_DELETE_BEGIN*/
	public static EntityType.Builder<LatexFox> getEntityInitBuilder() {
		return EntityType.Builder.of(LatexFox::new, MobCategory.MONSTER).clientTrackingRange(10).sized(0.7F, 1.93F) ;
	}
	
	public static RegistryObject<EntityType<LatexFox>> getEntityInitRObject() {
		return InitUtils.getEntityInitRObject(
			   "latex_fox",
			   0xE37107,
			   0x9E4F05,
			   LatexFox.getEntityInitBuilder(),
			   ChangedEntity::createLatexAttributes
		);
	}
	
	public static TransfurVariant<LatexFox> getTFInitBuilder()
	{
		return TransfurVariant.Builder
			   .of(LATEX_FOX)
			   
			   
			   
			   
			   .visionType(VisionType.NIGHT_VISION)
			   
			   .itemUseMode( UseItemMode.NORMAL )

			   .transfurMode(TransfurMode.REPLICATION)
			   .addAbility(ChangedAbilities.SWITCH_TRANSFUR_MODE)
 			   .addAbility(ChangedAbilities.GRAB_ENTITY_ABILITY)
 			   .addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)

			   
			   
			   .build();
	}
	/*PERL_ABSTRACT_DELETE_END*/
	
	public static void registerSpawns(SpawnPlacementRegisterEvent event) {
		
		if ( Heightmap.Types.MOTION_BLOCKING_NO_LEAVES != null && SpawnPlacements.Type.ON_GROUND != null) { return; }
		
		event.register( LATEX_FOX.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LatexFox::checkEntitySpawnRules, SpawnPlacementRegisterEvent.Operation.OR );
	}
	
	public LatexFox(EntityType<? extends ChangedEntity> type, Level level) { super(type, level); }
	
	@Override
	public TransfurMode getTransfurMode() {
		return TransfurMode.REPLICATION;
	}
	
	
	
	@Override
	protected void setAttributes (AttributeMap attributes) {
		super.setAttributes(attributes);
		attributes.getInstance(Attributes.MOVEMENT_SPEED).setBaseValue(1.1);
 		attributes.getInstance(ForgeMod.SWIM_SPEED.get()).setBaseValue(0.93);
 		attributes.getInstance(ChangedAttributes.SNEAK_SPEED.get()).setBaseValue(1.5);
 		attributes.getInstance(ChangedAttributes.SPRINT_SPEED.get()).setBaseValue(1.25);
 		attributes.getInstance(ChangedAttributes.JUMP_STRENGTH.get()).setBaseValue(1.5);
 		attributes.getInstance(ChangedAttributes.FALL_RESISTANCE.get()).setBaseValue(2.5);

	}
	
	public Color3 getTransfurColor(TransfurCause cause) { return Color3.fromInt(0xE37107); }
	
	@Override
	public int getTicksRequiredToFreeze() { return 400; }

	/*PERL_FLYING_SPEED*/

	

	

	


}
