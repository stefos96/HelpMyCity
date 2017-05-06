<?php
//Setting for effective connection to the database
define('DB_USER', "root");
define('DB_PASSWORD', "loztp314159");
define('DB_DATABASE', "test");
define('DB_SERVER', "localhost");

//Creating the class that allows us to connect to the database
class DB_CONNECT {
    function __construct() {
        $this->connect();
    }
    function __destruct() {
        // closing db connection
        $this->close();
    }
    function connect() {
        // import database connection variables
        require_once __DIR__ . '/db_config.php';
        // Connecting to mysql database
        $con = mysql_connect(DB_SERVER, DB_USER, DB_PASSWORD) or die(mysql_error());
 
        // Selecing database
        $db = mysql_select_db(DB_DATABASE) or die(mysql_error()) or die(mysql_error());
 
        // returing connection cursor
        return $con;
    }
    function close() {
        // closing db connection
        mysql_close();
    }
}
?>