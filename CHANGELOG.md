Google Spreadsheets Connector Release Notes
==========================================

Date: 12-May-2014

Version: 2.0.0

Supported API versions: Google Spreadsheets API v3 - https://developers.google.com/google-apps/spreadsheets/

Supported Mule Runtime Versions: 3.5.0

Closed Issues in this release
------------------------------

 - Upgraded Mule Devkit to 3.5.0.
 - Removed @Optional as it is redundant when used along @Default.
 - Removed deprecated @OAuthInvalidateAccessTokenOn and added @ReconnectOn on the Connector.
 - The list of operations supported by this version of the connector are
    - Get All Spreadsheets
    - Create Spreadsheet
    - Get All Worksheets
    - Create Worksheet
    - Delete Worksheet
    - Update Worksheet Metadata
    - Set Row Values
    - Set CSV Values
    - Get Authors
    - Get Column Headers
    - Get Spreadsheets By Title
    - Get Worksheet By Title
    - Purge Worksheet
    - Get All Cells
    - Get All Cells As CSV
    - Get Cell Range
    - Get Cell Range As CSV
    - Search

Known Issues in this release
------------------------------

 - Functional test cases and Samples need to be migrated to the latest devkit.

1.5.0
=====

 - Implemented on Mule Devkit version 3.4.3.
 - Initial Version.