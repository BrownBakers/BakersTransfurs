#!/usr/bin/perl

use warnings;
use strict;
use File::Path qw(make_path);

#GReG - Global Registry Generator by KJEntytek303.

my @variants = ();
my $output_dir = "generated/java/registries";
make_path($output_dir);



while ( !eof STDIN ) {
	push( @variants, <STDIN> );
}

foreach (@variants) {
	chomp( $_ );
	if ( ! ( $_ =~ /^[A-Z][a-zA-Z]*/ ) ) {
		die "Invalid variant name: $_";
	}
}

generateEntities(@variants);
generateLayers(@variants);
generateRenderers(@variants);
generateTransfurs(@variants);

sub generateEntities {# {{{
	my $IMPORT = 'import net.kjentytek303.additional_transfurs.entity.PERL_ENTITY_NAME;';
	my $ROBJECT = '	public static final RegistryObject<EntityType<PERL_ENTITY_NAME>> PERL_CAPITALIZED_ENTITY_NAME = PERL_ENTITY_NAME.getEntityInitRObject();';

	open(my $RFILE, '<', 'data/java/registry/InitEntities.java') or die "Couldn't open entity registry file: $!. Aborted";
	my @mapped_file = <$RFILE>;
	close($RFILE);

	my @imports = (); 
	my @robjects = ();
	foreach (@_) {
		my $variant_name = $_;

		my $variant_capitalized_name = $variant_name;
		$variant_capitalized_name =~ s/([A-Z])/_$1/g;
		$variant_capitalized_name =~ s/^_//;
		$variant_capitalized_name =~ tr/[a-z]/[A-Z]/;
		my $import = $IMPORT;
		$import =~ s/PERL_ENTITY_NAME/$variant_name/;
		my $robject = $ROBJECT;

		$robject =~ s/PERL_ENTITY_NAME/$variant_name/g;
		$robject =~ s/PERL_CAPITALIZED_ENTITY_NAME/$variant_capitalized_name/;

		push( @imports, $import );
		push( @robjects, $robject );
	}

	foreach (@robjects) {
		$_ = $_ . "\n";
	}

	foreach (@imports) {
		$_ = $_ . "\n";
	}

	foreach (@mapped_file) {
		$_ =~ s/\/\*PERL_ENTITY_IMPORTS\*\//@imports/;
		$_ =~ s/\/\*PERL_ENTITIES\*\//@robjects/;
	}

	open( WFILE, '>', $output_dir . '/InitEntities.java' );
	print WFILE @mapped_file;
	close( WFILE );
}# }}}

sub generateLayers {# {{{
	my $IMPORT = 'import net.kjentytek303.additional_transfurs.client.renderer.model.PERL_MODEL_NAME;';
	my $ROBJECT = '		event.registerLayerDefinition(PERL_MODEL_NAME.LAYER_LOCATION, PERL_MODEL_NAME::createBodyLayer);';

	open(my $RFILE, '<', 'data/java/registry/InitLayerDefinitions.java') or die "Couldn't open entity registry file: $!. Aborted";
	my @mapped_file = <$RFILE>;
	close($RFILE);

	my @imports = (); 
	my @robjects = ();
	foreach (@_) {
		my $model_name = $_ . "Model";

		my $import = $IMPORT;
		$import =~ s/PERL_MODEL_NAME/$model_name/;

		my $robject = $ROBJECT;
		$robject =~ s/PERL_MODEL_NAME/$model_name/g;

		push( @imports, $import );
		push( @robjects, $robject );
	}

	foreach (@robjects) {
		$_ = $_ . "\n";
	}

	foreach (@imports) {
		$_ = $_ . "\n";
	}

	foreach (@mapped_file) {
		$_ =~ s/\/\*PERL_MODEL_IMPORTS\*\//@imports/;
		$_ =~ s/\/\*PERL_LAYER_DEFINITIONS\*\//@robjects/;
	}

	open( WFILE, '>', $output_dir . '/InitLayerDefinitions.java' );
	print WFILE @mapped_file;
	close( WFILE );
}# }}}

sub generateRenderers {# {{{
	my $IMPORT = 'import net.kjentytek303.additional_transfurs.client.renderer.PERL_RENDERER_NAME;';
	my $ROBJECT = '		registerHumanoid(event, InitEntities.PERL_CAPITALIZED_NAME.get(), PERL_RENDERER_NAME::new);';

	open(my $RFILE, '<', 'data/java/registry/InitRenderers.java') or die "Couldn't open entity registry file: $!. Aborted";
	my @mapped_file = <$RFILE>;
	close($RFILE);

	my @imports = (); 
	my @robjects = ();
	foreach (@_) {
		my $variant_name = $_;

		my $variant_capitalized_name = $variant_name;
		$variant_capitalized_name =~ s/([A-Z])/_$1/g;
		$variant_capitalized_name =~ s/^_//;
		$variant_capitalized_name =~ tr/[a-z]/[A-Z]/;

		my $renderer_name = $variant_name . 'Renderer';

		my $import = $IMPORT;
		$import =~ s/PERL_RENDERER_NAME/$renderer_name/g;

		my $robject = $ROBJECT;

		$robject =~ s/PERL_CAPITALIZED_NAME/$variant_capitalized_name/g;
		$robject =~ s/PERL_RENDERER_NAME/$renderer_name/g;

		push( @imports, $import );
		push( @robjects, $robject );
	}

	foreach (@robjects) {
		$_ = $_ . "\n";
	}

	foreach (@imports) {
		$_ = $_ . "\n";
	}

	foreach (@mapped_file) {
		$_ =~ s/\/\*PERL_RENDERER_IMPORTS\*\//@imports/;
		$_ =~ s/\/\*PERL_RENDERERS\*\//@robjects/;
	}

	open( WFILE, '>', $output_dir . '/InitRenderers.java' );
	print WFILE @mapped_file;
	close( WFILE );
}# }}}

sub generateTransfurs {# {{{
	my $IMPORT = 'import net.kjentytek303.additional_transfurs.entity.PERL_ENTITY_NAME;';
	my $ROBJECT = '	public static final RegistryObject<TransfurVariant<PERL_ENTITY_NAME>> PERL_CAPITALIZED_ENTITY_NAME_VARIANT= TF_REGISTRY.register("PERL_LOWERCASE_NAME", PERL_ENTITY_NAME::getTFInitBuilder);';

	open(my $RFILE, '<', 'data/java/registry/InitTransfurs.java') or die "Couldn't open entity registry file: $!. Aborted";
	my @mapped_file = <$RFILE>;
	close($RFILE);

	my @imports = (); 
	my @robjects = ();
	foreach (@_) {
		my $variant_name = $_;

		my $variant_capitalized_name = $variant_name;
		$variant_capitalized_name =~ s/([A-Z])/_$1/g;
		$variant_capitalized_name =~ s/^_//;
		$variant_capitalized_name =~ tr/[a-z]/[A-Z]/;

		my $variant_lowercase_name = $variant_capitalized_name;
		$variant_lowercase_name =~ tr/[A-Z]/[a-z]/;

		my $import = $IMPORT;
		$import =~ s/PERL_ENTITY_NAME/$variant_name/;
		my $robject = $ROBJECT;

		$robject =~ s/PERL_ENTITY_NAME/$variant_name/g;
		$robject =~ s/PERL_CAPITALIZED_ENTITY_NAME/$variant_capitalized_name/g;
		$robject =~ s/PERL_LOWERCASE_NAME/$variant_lowercase_name/g;

		push( @imports, $import );
		push( @robjects, $robject );
	}

	foreach (@robjects) {
		$_ = $_ . "\n";
	}

	foreach (@imports) {
		$_ = $_ . "\n";
	}

	foreach (@mapped_file) {
		$_ =~ s/\/\*PERL_ENTITY_IMPORTS\*\//@imports/;
		$_ =~ s/\/\*PERL_TRANSFURS\*\//@robjects/;
	}

	open( WFILE, '>', $output_dir . '/InitTransfurs.java' );
	print WFILE @mapped_file;
	close( WFILE );
}# }}}
