<?php
/*  Database Information - Required!!  */
/* -- Configure the Variables Below --*/
$dbhost = 'localhost';
$dbusername = 'root';
$dbpasswd = '';
$database_name = 'haney';

/* Database Stuff, do not modify below this line */

$connection = mysqli_connect("$dbhost","$dbusername","$dbpasswd") 
	or die ("Couldn't connect to server.");
	
$db = mysqli_select_db($connection, $database_name) or die("Couldn't select database.");
?>