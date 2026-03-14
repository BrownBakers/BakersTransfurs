#!/usr/bin/perl

use warnings;
use strict;

my $IMPORTS='package net.brown_bakers.bakers_transfurs.client.renderer.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.brown_bakers.bakers_transfurs.BakersTransfurs;
import net.brown_bakers.bakers_transfurs.entity./*PERL_CAPITALIZED_NAME*/;
import net.ltxprogrammer.changed.client.renderer.animate.AnimatorPresets;
import net.ltxprogrammer.changed.client.renderer.animate.HumanoidAnimator;
import net.ltxprogrammer.changed.client.renderer.model.AdvancedHumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.HumanoidArm;

import java.util.List;
';

my $EXTEND = "Model extends AdvancedHumanoidModel</*PERL_CAPITALIZED_NAME*/>";
my $GENERIC_OVERRIDES = '	@Override
	public HumanoidAnimator</*PERL_CAPITALIZED_NAME*/, ?> getAnimator(/*PERL_CAPITALIZED_NAME*/ entity) { return animator; }
	
	/*PERL_LEG_OVERRIDES*/
	
	@Override
	public ModelPart getTorso() { return this.Torso; }
	
	@Override
	public ModelPart getHead() { return this.Head; }
	
';

my $LEGLESS_LEG_OVERRIDES

my $MASKED = '	public boolean isPartNotMask(ModelPart part) { return Mask.getAllParts().noneMatch(part::equals); }
	';

my $BIPED_LEG_OVERRIDES = '
	@Override
	public ModelPart getArm(HumanoidArm humanoidArm) { return humanoidArm == HumanoidArm.LEFT ? this.LeftArm : this.RightArm; }
	
	@Override
	public ModelPart getLeg(HumanoidArm humanoidArm) { return humanoidArm == HumanoidArm.LEFT ? this.LeftLeg : this.RightLeg; }
'

my $LEGLESS_OVERRIDES = '
	@Override
	public ModelPart getArm(HumanoidArm humanoidArm) { return null; }
	
	@Override
	public ModelPart getLeg(HumanoidArm humanoidArm) { return null; }

	@Override
	public boolean shouldModelSit(LatexMantaRayFemale entity) {
		return super.shouldModelSit(entity) || LeglessModel.shouldLeglessSit(entity);
	}'

my $DECLARATIONS = "	private final HumanoidAnimator</*PERL_CAPITALIZED_NAME*/, /*PERL_CAPITALIZED_NAME*/Model> animator;

//	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(BakersTransfurs.modResource(\"entity//*PERL_NAME_LOWERCASE*/\"), \"main\");

";

my $ANIMATOR_INIT = "		animator = HumanoidAnimator.of(this).hipOffset(-1.5f)
			   .addPreset(AnimatorPresets./*PERL_ANIMATOR_PRESET*/Like(
					 Head,
					 /*PERL_ANIMATED_EARS*/
					 Torso, LeftArm, RightArm,
					 Tail, List.of(/*PERL_TAIL_PARTS_ARRAY*/),
					 /*PERL_LEG_STUFF*/
					 /*PERL_WINGED_STUFF*/
					 ));

";

my $input_file_name = '';
my $name_capitalized ;
my $name_lowercase;
my $output_file_name = '';
my $preset = '';
my @tail_parts = ();
my $is_masked = 0;
my $leg_type = "";

getlopt(@ARGV);

$name_capitalized = $input_file_name;
$name_capitalized =~ /([a-zA-z]+)\.java$/;
$name_capitalized = $1;

$name_lowercase = $name_capitalized;
$name_lowercase =~ s/([A-Z])/_$1/g;
$name_lowercase =~ tr/[A-Z]/[a-z]/;
$name_lowercase =~ s/^_//;

$output_file_name = $name_capitalized . 'Model.java';

#get file name from cmd parameter
#open file and do shit
#export it to a desired file

open(my $IFILE, '<', $input_file_name) or die "Couldn't open file '$input_file_name': $!";
my @mapped_file = <$IFILE>;
close($IFILE);

splice(@mapped_file, 0, 0, ($IMPORTS) );

my $doneExtend = 0;
my $inModelParts = 0;
my $inConstruct = 0;


for( my $i = 1; $i<scalar(@mapped_file); $i++) {# {{{

	if ( $doneExtend == 0 && $mapped_file[$i] =~ /public class/ ) {
		$mapped_file[$i] =~ s/<T extends Entity> extends EntityModel<T>/$EXTEND/;
		$i++;
		splice(@mapped_file, $i, 0, $GENERIC_OVERRIDES);
		$doneExtend = 1;
		next;
	}

	$mapped_file[$i] =~ s/new ResourceLocation\("modid", "[a-z]+"\),/BakersTransfurs.modResource\("entity\/$name_lowercase"\),/;
	if ( $inModelParts == 0 && $mapped_file[$i] =~ /private final ModelPart;/ ) {
		$inModelParts = 1;
		next;
	}

	if ( $inModelParts == 1 ) {
		splice(@mapped_file, $i, 0, $DECLARATIONS);
		$inModelParts = -1;
		next;
	}

	if ( $mapped_file[$i] =~ /private final ModelPart (Tail[a-zA-Z]+);/ ) {
		push(@tail_parts, $1);
		next;
	}

	if ($inConstruct == 0 && $mapped_file[$i] =~ /public $name_capitalized/ ) {

		$mapped_file[$i] = "		public $name_capitalized" . "Model" . " (ModelPart root) {\n";
		$i++;
		splice(@mapped_file, $i, 0, "		super(root);\n");
		$i++;
		while ($mapped_file[$i] =~ /getChild/) {
			$i++;
		}
		splice(@mapped_file, $i, 0, $ANIMATOR_INIT);
		$inConstruct = -1;
		next;
	}

	if ( $mapped_file[$i] =~ /public void setupAnim/ ) {
		$i--;
		splice(@mapped_file, $i, 4, () );
		next;
	}
}	# }}}

my $tail_parts_serialized = serializeArray(@tail_parts);

foreach (@mapped_file) {
	$_ =~ s/\/\*PERL_CAPITALIZED_NAME\*\//$name_capitalized/g;
	$_ =~ s/\/\*PERL_NAME_LOWERCASE\*\//$name_lowercase/g;
	$_ =~ s/\/\*PERL_ANIMATOR_PRESET\*\//$preset/;
	$_ =~ s/\/\*PERL_TAIL_PARTS_ARRAY\*\//$tail_parts_serialized/;
}

print @mapped_file;


sub getlopt {# {{{
	my $eval = 0;
	foreach(@_) {
		if ( $eval == 0 ) {
			if ( $_ eq "-f" ) { $eval = 1; next; }
			if ( $_ eq "-p" ) { $eval = 2; next; }
			if ( $_ eq "-M" ) { $is_masked = 1; next; }
			if ( $_ eq "-L" ) { $leg_type = "LEGLESS"; next; }
			if ( $_ eq "-T" ) { $leg_type = "TAUR"; next; }

			next;
		}

		if ( $eval == 1 ) {
			$input_file_name = $_;
			$output_file_name = $_;
			$output_file_name =~ s/\.java/Model\.java/;
			$eval = 0;
			next;
		}

		if ( $eval == 2 ) {
			$preset = $_;
			$eval = 0;
			next;
		}
	}
	if( $eval != 0 || $input_file_name eq '' || $preset eq '' ) { die 'Insufficient arguments. Aborted.';}
}# }}}

sub serializeArray {
	return "" if scalar(@_) == 0;

	my $ret = $_[0];
	for (my $i = 1; $i < scalar(@_); $i++ ) {
		$ret = $ret . ", " . $_[$i];
	}
	return($ret);
}
