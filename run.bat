@echo off

cd %CD%/src/

echo Compiling Program...
javac ./*.java

echo Running program...
echo --------------------------------
java Mtrx

echo Flushing class files...
del *.class /q /f /s
cd ..
