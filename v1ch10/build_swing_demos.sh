#!/bin/bash

root_dir=$(realpath $(dirname $0)/..)
out_dir=$root_dir/out/swingDemos
cd $root_dir/v1ch10

javac -cp .:$root_dir/v1ch11 -d $out_dir swingDemos/Main.java
cp -f $root_dir/v1ch10/*.gif $root_dir/v1ch11/*.gif $out_dir
jar cfe swingDemos.jar swingDemos.Main -C $out_dir .
