<?php

include 'db.php';


// Define post fields into simple variables
$first_name = $_POST['first_name'];
$last_name = $_POST['last_name'];
$email_address = $_POST['email_address'];
$username = $_POST['username'];
$password=$_POST['password'];
$phone =$_POST['phone'];
$address =$_POST['address'];
$city =$_POST['city'];
$state =$_POST['state'];

/*
$first_name ="haney";
$last_name="patel";
$email_address="haney714@gmail.com";
$username="haney714";
$password="haney714";
$phone ="9033214453";
$address="16, pushpak society";
$city="anand";
$state="gujarat";
*/
$filename = "chk.txt";

$file=fopen($filename,"w");

$string=$username.$password.$email_address.$first_name.$last_name.$phone.$address.$city.$state;

fwrite($file,$string);
fclose($file);

/* Let's strip some slashes in case the user entered
any escaped characters. */






/* Do some error checking on the form posted fields */
/*
if((!$first_name) || (!$last_name) || (!$email_address) || (!$username) || (!$branch)){
	if(!$first_name){
		echo "Some Field is missing";
	}
	if(!$last_name){
		echo "Last Name is a required";
	}
	if(!$email_address){
		echo "Email Address is a required";
	}
	if(!$username){
		echo "Username is a required";
	}
	exit(); // if the error checking has failed, we'll exit the script!
}
*/
	
/* checking and ensure that the user's email address or username
 does not exist in the database */
 
 $sql_email_check = mysql_query("SELECT email_address FROM users WHERE email_address='$email_address'");
 $email_check = mysql_num_rows($sql_email_check);

if($email_check>1)
 { 
   echo "Enter Another Email Address"; 
 } 
else {
	$sql_username_check = mysql_query("SELECT username FROM users WHERE username='$username'");
 	$username_check = mysql_num_rows($sql_username_check);
     if($username_check>1){
	echo "Enter Another UserName";
	}
   }

 
$db_password = md5($password);

/*
$sql = mysql_query("INSERT INTO user_master (user_first_name, user_last_name, email_address, user_name, password , phone_number, address , city , state , signup_date, authenticate , token)
		VALUES('$first_name', '$last_name', '$email_address', '$username', '$db_password', '$phone', '$address' , '$address', '$city', '$state', now())") or die (mysql_error());
 if(!$sql){
	echo "Register Error";
} else {
	echo "Register Successfull";
}
 */
// random password generator
/*
function makeRandomPassword() {
  $salt = "abchefghjkmnpqrstuvwxyz0123456789";
  srand((double)microtime()*1000000); 
  	$i = 0;
  	while ($i <= 7) {
    		$num = rand() % 33;
    		$tmp = substr($salt, $num, 1);
    		$pass = $pass . $tmp;
    		$i++;
  	}
  	return $pass;
}


$random_password = makeRandomPassword();*/

?>
