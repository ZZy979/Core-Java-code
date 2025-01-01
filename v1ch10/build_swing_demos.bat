@ECHO OFF

SET root_dir=%~dp0..
SET out_dir=%root_dir%\out\swingDemos
CD %root_dir%\v1ch10

javac -d %out_dir% swingDemos\Main.java
jar cfe swingDemos.jar swingDemos.Main -C %out_dir% . -C %root_dir%\v1ch10 *.gif
