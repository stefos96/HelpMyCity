<?php
require_once __DIR__ . '/db_settings.php'; // Importing the settings file
$response = array(); // creating a new array the will contain the result of the SQL query

$prid1 = $_GET["prid"];
$db = new DB_CONNECT(); // Creating a new object that connects to the database
if($prid == "") //Checking if the GET request was sent in empty, in which case return all objects from the database
{
	while($row = mysql_fetch_array($result))
	{
		$result = mysql_query("SELECT * FROM problems") or die(mysql_error()); // SQL Query to return all objects
		$response["problems"] = array();             
		$problem = array();                                 //returning all the data from the database
		$problem["prid"] = $row["prid"];                    //into array to run in the function json_encode()
		$problem["name"] = $row["name"];					   //that will print the database as JSONObject
		$problem["lastname"] = $row["lastname"];
		$problem["description"] = $row["description"];
		array_push($response["problems"], $problem);
	}
	echo json_encode($response);						//echo ojects as JSONObject: {"problems"[{"prid":"12", "name":"Kostas", ...}]}
}
else  //if the prid was not empty we must return the problem with the current prid
{
	result = mysql_query("SELECT * FROM problems WHERE prid = $prid1") or die(mysql_error()); // SQL Query to return the object with the prid
	$response["problems"] = array();             
	$problem = array();                                 //returning all the data from the database
	$problem["prid"] = $row["prid"];                    //into array to run in the function json_encode()
	$problem["name"] = $row["name"];					   //that will print the database as JSONObject
	$problem["lastname"] = $row["lastname"];
	$problem["description"] = $row["description"];
	array_push($response["problems"], $problem);
	echo json_encode($response);						//echo oject as JSONObject: {"prid":"12", "name":"Kostas", ...}	
}
?>