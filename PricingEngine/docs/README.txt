										Read Me

This document contains the programming solution for Pricing Engine problem as a part of design/coding exercise.

========================
System Requirements
========================
Operating system:Windows 7+ /Mac OS X (Lion+) /Linux5.5+
Java SE Runtime Environment 7 or above.

========================
Contents
========================
The PricingEngine.zip file contains eclipse project structure with
1. Source code for the solution (src)
2. Configuration file to read the variable parameters (config)
3. Input survey file as input feed (inputOutput)
4. build.xml , ant build file to compile and jar the source code.
5. Compiled binaries as a executable jar file (dist/pricingEngine.jar)
6. Documents containing , readme ,testcases ,usage screenshots

========================
Prerequisite
========================
Java home runtime environment is already set.


========================
How To Use
========================
1. Unzip the contents of the PricingEngine.zip to a directory.
2. Within the dist directory, find the executable jar file (pricingEngine.jar.bkp).
3. Rename the .bkp file to .jar file (eg: ren pricingEngine.jar.bkp pricingEngine.jar)
4. Open command prompt, navigate to dist directory (eg:cd C:\Users\pavan\workspace\PricingEngine\dist)
5. Execute the jar file with 2 input arguments,
	a. Absolute path of initial configuration file (properties file)
	b. Absolute path of input survey file (text file)
	eg(java -jar pricingEngine.jar "C:\Users\pavan\workspace\PricingEngine\config\demand_supply_config_matrix.properties" "C:\Users\pavan\workspace\PricingEngine\inputOutput\surveyInput.txt")
6.Progress and Output messages are displayed on the command window.
7.If in case of any invalid/unnecessary data during runtime, corresponding message is displayed.
8.See executionResult.png in docs folder for execution and mixed data as input and displayed output.
