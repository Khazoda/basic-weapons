@echo off
echo Processing mod resources...
echo.

:: Process PNG files in resources folder and all subfolders
:: Link to oxipng root in environment variables of your system
for /r ".\common\src\main\resources" %%f in (*.png) do (
    echo Processing: "%%f"
    oxipng -o max --alpha --strip all -Z "%%f"
)

PAUSE