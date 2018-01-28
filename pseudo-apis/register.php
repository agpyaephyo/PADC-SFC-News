<?php
	if(isset($_POST['phoneNo']) && isset($_POST['password']) && isset($_POST['name'])){
		if($_POST['phoneNo'] == '+95911223344') {
			$return_json = array('code'=> 401 , 'message' => 'This phone number is already been registered. You can reset the password of the account if you forget.');
	    	header('Content-type: application/json');
	    	echo json_encode($return_json);
		} else {
			$json_data = file_get_contents('http://padcmyanmar.com/padc-3/mm-news/apis/v1/user_info.json');  

			$return_json = array('code'=> 200 , 
				'message' => 'Registration succcess',
				'register_user' => json_decode($json_data)
				);
	    	header('Content-type: application/json');
	    	echo json_encode($return_json);
		}
	} else {
		$return_json = array('code'=> 401 , 'message' => 'Missing required inputs for the request.');
	    header('Content-type: application/json');
	    echo json_encode($return_json);
	}
?>