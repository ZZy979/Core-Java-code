@ECHO OFF

SET root_dir=%~dp0..
SET out_dir=%root_dir%\out\swing-demos
CD %root_dir%\swing-demos

javac -cp .;%root_dir%\v1ch10;%root_dir%\v1ch11 -d %out_dir% Main.java
COPY /Y %root_dir%\v1ch10\*.gif %out_dir%
COPY /Y %root_dir%\v1ch11\*.gif %out_dir%
jar cfe swing-demos.jar Main -C %out_dir% .
