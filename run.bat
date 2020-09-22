@echo off

cd %CD%/src/

echo Compiling Program...
javac -d ../bin ./mtrx/*.java

echo Running program...
echo --------------------------------
cd ..
cd %CD%/bin
java mtrx.Mtrx
echo --------------------------------
cd ..
