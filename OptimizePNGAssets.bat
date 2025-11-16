@echo off
::===  LOSSLESS MINECRAFT PNG OPTIMIZER ======================================
::  This script runs oxipng in 100 % lossless mode.
::  To adapt this script to your resources/ location, change the path below:
::============================================================================

set "SEARCH_ROOT=.\common\src\main\resources"

chcp 65001 >nul
setlocal enabledelayedexpansion
for /f "tokens=1,2 delims=#" %%a in ('"prompt #$H#$E# & echo on & for %%b in (1) do rem"') do set "ESC=%%b"
set "_G=%ESC%[92m"
set "_B=%ESC%[94m"
set "_X=%ESC%[0m"

title Optimize Pixel Art
::---------------  locate oxipng on %PATH%  -----------------------------
for /f "delims=" %%E in ('where oxipng 2^>nul') do set "OXIPNG=%%E" & goto :found
echo ERROR: oxipng not found on PATH. Aborting.
pause & exit /b 1

:found
echo Using oxipng:  %OXIPNG%
echo:

::------------------------  Header  ------------------------------------------
cls
echo:
echo    ╔═══════════════════════════════════════════════════════════════╗
echo    ║  MINECRAFT PNG OPTIMISER  –  100 %% LOSSLESS, PIXEL-SAFE      ║
echo    ║  No colour values are changed – only file size is reduced.    ║
echo    ╚═══════════════════════════════════════════════════════════════╝
echo:

::------------------------  main loop  ---------------------------------------
set "done=0"
set "changed=0"
set "unchanged=0"
for /r "%SEARCH_ROOT%" %%F in (*.png) do (
    set /a done+=1
    set "file=%%~nxF"

    :: remember original size
    for %%Z in ("%%F") do set "old=%%~zZ"

    "%OXIPNG%" -q -o max --alpha --strip safe -Z "%%F"
    :: compare size
    for %%Z in ("%%F") do (
        if "%%~zZ"=="!old!" (
            set /a unchanged+=1
            echo   [%_B%OK 🞑%_X%]  already optimal : !file!
        ) else (
            set /a changed+=1
            echo   [%_G%OK ✔%_X%]  optimised       : !file!
        )
    )
)

::------------------------  footer  ------------------------------------------
echo:
echo    ──────────────────────────────────────────
echo    Finished.  Processed %done% PNG files.
echo    %_G%✔ %changed% files optimized %_X%
echo    %_B%🞑 %unchanged% files were already optimized %_X%
echo:
pause