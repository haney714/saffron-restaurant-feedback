<?php

$con=mysqli_connect("localhost","root","","haney");
// Check connection
if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

$customer_name = $_POST['name'];
$email = $_POST['email'];
$food_quality = $_POST['quality'];
$food_varity = $_POST['varity'];
$service = $_POST['service'];
$money_for_value = $_POST['moneyForValue'];
$feedback= $_POST['feedback'];

$filename = "chk.txt";

$file=fopen($filename,"w");
$string_o=$customer_name.$email.$food_quality.$food_varity.$service.$money_for_value.$feedback;
fwrite($file,$string_o);
fclose($file);


if((!$food_quality) || (!$food_varity) || (!$service) || (!$money_for_value)){
	if(!$food_quality){
		$food_quality = 0;
	}
	if(!$food_varity){
		$food_varity =0;
	}
	if(!$service){
		$service = 0;
	}
	if(!$money_for_value){
		$money_for_value = 0;
	}
}
$sql = mysqli_query($con, "INSERT INTO saffron (user_name, email_address, rat_quality, rat_varity, rat_service, rat_money_for_value , feedback) VALUES('$customer_name', '$email', '$food_quality', '$food_varity', '$service', '$money_for_value' , '$feedback')") or die (mysql_error());
 if(!$sql){
	echo "Feedback Sending Unsuccessfull";
} else {
	echo "Feedback Sending Successfull";
}
/*$email_address = "haney714@gmail.com";
$subject = "Feedback from $customer_name";
   $message = "
	Name = $customer_name
	email address = $email
	Food Quality = $food_quality
	Food Varity = $food_varity
	Services = $service
	Money for Value = $money_for_value
	Feedback = $feedback

	Thanks!
	
	This is an automated response, please do not reply!";

	if(mail($email_address, $subject, $message)){
	    echo "Feedback Sending Successfull";
	} else {
	echo "Feedback Sending Error";
	}
$email_address1= "haneypatel1992@gmail.com";
mail($email_address1, $subject, $message)
  mail('haney714@student.fdu.edu','feedback','noting');
*/
  ?>	