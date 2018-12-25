Java - Berlin Clock Conversion API 

INTRODUCTION
------------

This download provides code to convert Digital clock time to Berlin clock time & vice versa.
Additionally, it contains command line interface to access the API.


CONTENTS
--------

This download contains:

    /README.md - this file
    /POM.xml - Maven build files to compile and run the test 
    /src - Actual code for time conversion

PRE-REQUISITES
--------------

The following are pre-requisites to successfully use the API in the development environment:

1. Eclipse 4.6 or more Installation
2. JDK 1.8 Installation
3. Apache Maven 2.5.x Installation

RUNNING THE CODE
-------------------
1. Clone the git repository locally - https://github.com/gangesmech/2018_DEV_036.git.
2. Through Eclipse, import the 'berlin-clock' folder as 'Existing Maven projects'.
3. For building it, Run project as 'Maven install'
4. For time conversion, Run BerlinClockMain Class as a Java application using below arguments.
	a) For Berlin to Digital Clock conversion : '-i 23:59:59'
	b) For Digital to Berlin Clock conversion : '-r -i ORROOROOOYYRYYRYOOOOYYOO'
	c) For help, execute Java without any argument
5. For running all unit test cases, Run project as 'Maven test'.


