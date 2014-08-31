springmvc
=========

Source of Spring MVC Tutorial project

BUGFIX 8/30/2014
================

Fixed bug in contact manager where contact information
on contacts would only display for the first DB record given a first name.
Now selecting rows where first and AND last name matches.

Files Affected:
-ContactController.java
-NameSplitter.java
-DatabaseService.java