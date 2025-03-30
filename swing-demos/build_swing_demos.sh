#!/bin/bash

root_dir=$(realpath $(dirname $0)/..)
out_dir=$root_dir/out/swing-demos
cd $root_dir/swing-demos

javac -cp .:$root_dir/v1ch10:$root_dir/v1ch11 -d $out_dir Main.java
cp -f $root_dir/v1ch10/*.gif $root_dir/v1ch11/*.gif $out_dir
jar cfe swing-demos.jar Main -C $out_dir .
