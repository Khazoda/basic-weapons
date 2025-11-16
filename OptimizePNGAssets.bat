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
set "savings=0"
set "original_total=0"
set "new_total=0"
for /r "%SEARCH_ROOT%" %%F in (*.png) do (
    set /a done+=1
    set "file=%%~nxF"

    :: remember original size
    for %%Z in ("%%F") do set "old=%%~zZ"
    set /a original_total+=old

    "%OXIPNG%" -q -o max --alpha --strip safe -Z "%%F"
    :: compare size
    for %%Z in ("%%F") do (
        set /a new_total+=%%~zZ
        if "%%~zZ"=="!old!" (
            set /a unchanged+=1
            echo   [%_B%OK 🞑%_X%]  already optimal : !file!
        ) else (
            set /a changed+=1
            set /a savings+=old - %%~zZ
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
set /a original_kb=original_total/1024
set /a new_kb=new_total/1024
set /a savings_kb=savings/1024
echo    ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ── ─ ─ ─ ─ ─ ─ ─ ─ ─ ─
echo    Original Total Size: !original_kb! KB
echo    New Total Size     : !new_kb! KB
echo    Total space saved: !savings_kb! KB
echo:
pause