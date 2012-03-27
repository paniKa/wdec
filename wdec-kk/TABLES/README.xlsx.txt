Use of ODBC with Excel 2010 and perhaps other recent versions of Excel
is somewhat clumsier than it used to be with older versions of Excel.
Reading .xls files still works, but writing fails.  It is instead
necessary to write .xlsx files.  The ODBC handler will write a new
.xlsx file without reporting any trouble, but then Excel cannot read
the resulting file.  This appears to be a Microsoft bug.  A solution
is to create an empty .xlsx file; the ODBC driver can than add tables
to it.

Adding new columns to an existing table is also clumsier with Excel
2010 than with sufficiently earlier versions of Excel.  An initial
"write table" that adds the columns goes well, but it is then
necessary to use Excel's Name Manger to manually adjust the range of
columns encompassed by the table before one can read the table back
with a "read table" command in an AMPL session.  Moreover, repeating
an AMPL script that adds columns to a table will fail if the table
range has not been adjusted first.  A solution is to use Excel to
modify the table before running AMPL:  add the desired output column
names (in any order you find convenient -- not necessarily the order
used in your table declarations) and adjust the range of the table to
include the new columns -- and any rows you will want to add.  In
Excel 2010, click on "Formulas" (near the top of the screen), then on
"Name Manager" to bring up a window in which you can add or modify
table names and ranges.

Files *.xlsx.run are variants of *.xls.run that work with Excel 2010
and the *.xlsx files.
