#!/bin/bash
./discoverer.pl
./gtmpg.pl
cp -R ./generated/data/* ../../src/main/resources/data/
