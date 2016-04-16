<?php
require 'facemashInit.php';
if($_SERVER['REQUEST_METHOD']=='POST'){
$image=$_POST['uploadImage'];
$sno=$_POST['srno'];
$gender=$_POST['gender'];
saveImage($image,$sno,$gender);
mysqli_close($con);
}
else
{
	echo "error";
}
function saveImage($image,$sno,$gender){
	require "facemashInit.php";
	if(strcmp($gender,'MALE')==0){
		$path="maleUploads/$sno.jpg";
	}
	else
		{
		$path="femaleUploads/$sno.jpg";
		}
	$sql="update userregistration set image='$sno.jpg' where SrNo='$sno'";
	mysqli_query($con,$sql);
	file_put_contents($path,base64_decode($image));
	echo "save image called";
}
?>
