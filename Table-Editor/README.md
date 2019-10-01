# **Table Editor**

A simple *Swing* based tool which generates SQL queries for the selected table, based on the selected operations - 
* Insert
* Update
* Delete

## **User Interface**
![Table Editor UI image](Table-Editor.jpg "Table Editor")

## **Steps to Use**
| Sr. No. | Steps | Result on Screen |
|---------|-------|------------------|
|1.|Launch the application.|Landing page is displayed.|
|2.|Select a table from list of available table names.|Table name is selected.|
|3.|Click on "Get Columns" button.|Table is populated with column names of selected table.|
|4.|Enter values for columns|The new user provided values are persisted.|
|5.|Choose the operation(s).|User selected operation checkbox is selected.|
|6.|Click on "Get Query" button.|The text area is populated with queries.|

## **Technology Stack**
* Java
* Swing
* PostgreSQL database

## **Swing Components Used**
* JLabel
* JComboBox
* JButton
* JTable
* JCheckBox
* JTextArea

## **Supported Database(s)**
* Only PostgreSQL database is supported at the moment.