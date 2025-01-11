@ECHO OFF

SET root_dir=%~dp0..
SET out_dir=%root_dir%\out\swingDemos
CD %root_dir%\v1ch10

javac -cp .;%root_dir%\v1ch11 -d %out_dir% swingDemos\Main.java
COPY /Y %root_dir%\v1ch10\*.gif %out_dir%
COPY /Y %root_dir%\v1ch11\*.gif %out_dir%
jar cfe swingDemos.jar swingDemos.Main -C %out_dir% .
