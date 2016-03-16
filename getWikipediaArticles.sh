#!/bin/sh
wget -O - http://dumps.wikimedia.org/enwiki/latest/enwiki-latest-all-titles-in-ns0.gz | gunzip  -c > src/main/resources/autocomplete/enwiki-latest-all-titles-in-ns0


