@echo off

if "%ANT_HOME%" NEQ "" goto :okANT_HOME
echo ANT_HOME not defined
goto :OutOfHere
:okANT_HOME

if "%JAVA_HOME%" NEQ "" goto :okJAVA_HOME
echo JAVA_HOME not defined
goto :OutOfHere
:okJAVA_HOME

%ANT_HOME%\ant %1 %2 %3 %4 %5 %6 %7 %8 %9

:OutOfHere
