#!/usr/bin/perl
#GregTech Masohist's Perl Generator /j
use warnings;
use strict;
scanDirectory("data");


#look into tags and discover namespaces.
#then, iterate over directories
##discover the contents of each directory, and iterate over files and directories.
##if directory, then recursive iterate.
##else if file
##Compile-check all values. Halt file write on error, but don't crash.
##If not errored, write values to a file inside generated/<namespace>/

sub scanDirectory {
	opendir(my $TAG_DIR, $_[0]) or die "Couldn't open directory $_[0]: $!
Aborted";
	mkdir "generated";

	my @files = readdir($TAG_DIR);
	closedir($TAG_DIR);
	foreach(@files) {

		if ( $_ eq "." || $_ eq ".." ) { #yes, this must be here. {{{
			next;
		} #}}}

		my $newpath = $_[0] . "/" . "$_";
		my $gen_path = $newpath;
		$gen_path =~ s/^data/generated/;

		if ( -f $newpath && $newpath =~ /.mctag$/ ) { #regular file {{{
			$gen_path =~ s/mctag$/json/;
			my $errored = 0;

			my @tag_values;
			open( my $FILE, "<$newpath" ) or die "Couldn't open file '$newpath': $!";
			foreach (<$FILE>) { # scan a file and push the lines into a buffer{{{

				if ( $_ =~ /^\h*\n?$/ || $_ =~ /^;/ ) { #blank line or comment
					next;
				}

				if ( $_ =~ /(^#?[a-z][a-z0-9_]*:[a-z][a-z0-9_\/]*)\h*(false|)?\h*$/ ) { #entry {{{
					my $temp_tmp = $1;

					if ( $2 eq 'false' ) {
						$temp_tmp = '{ "id": "' . $temp_tmp . '", "required": false }';
					}
					else {
						$temp_tmp = '"' . $temp_tmp . '"';
					}

					$temp_tmp = '    ' . $temp_tmp . ',' . "\n";
					push(@tag_values, $temp_tmp);
					next;
				} #}}}

				print STDERR "Broken entry \"$_";
				$errored = 1;

			} #}}}
			close ($FILE);

			if ($errored) {
				print STDERR "in file \"$newpath\"\n";
				next;}

			open( my $TFILE, 'tag.json.template' ) or die "Couldn't open file 'tag.json.template': $!";
			my @TAG_FILE = <$TFILE>;
			close ($TFILE);

			$tag_values[ scalar(@tag_values) - 1 ] =~ s/,$//;
			
			foreach (@TAG_FILE) {
			$_ =~ s/PERL_TAGS/@tag_values/;
			}
			
			open(WFILE, '>', $gen_path ) or die "Couldn't open file '$gen_path': $!";
			foreach ( @TAG_FILE ) {
				print WFILE $_;
			}

			close (WFILE);
			next; 

		} #}}}

		if ( -d $newpath && $newpath =~ /[a-z_0-9\/]+/ ) { #directory {{{
			mkdir $gen_path;
			scanDirectory($newpath);
			next;
		} #}}}

		print STDERR "Invalid file: $newpath\n";
	}
}
