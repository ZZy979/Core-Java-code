#!/bin/bash

root_dir=$(realpath $(dirname $0)/..)
out_dir=$root_dir/out/swingDemos
cd $root_dir/v1ch10

javac -d $out_dir swingDemos/Main.java
jar cfe swingDemos.jar swingDemos.Main -C $out_dir . -C $root_dir/v1ch10 *.gif
