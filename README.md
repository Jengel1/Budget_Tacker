# Budget_Tracker

GUI application to keep track of a household’s budget. 

User can add expenses, income, and recurring costs to find out how much they are saving or losing over a given period of time. 

Allows the user to specify a date range and see the net flow of money in and out of the house budget for that time period.

user can import expenses and income from online bank statements and credit card sites.
	-contact site, auto log in, navigate to desired data, export data into application
		-temporary short cut: manually log in and export desired date range to a .csv file

  Export - export .csv from online sites
  Transform - Normalize headers to matching custom format
  Load - load .csv values into database

  For each file in FolderOfCSVs perform ETL


Create database to store budget info

Create script to catagorize expenses into database

Optional: Catagorize expenses to generate an expense report. 

Include visual graphs.
