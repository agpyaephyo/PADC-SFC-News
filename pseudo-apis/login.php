<?php
	if(isset($_POST['phoneNo']) && isset($_POST['password'])){
		if($_POST['phoneNo'] == '+95911223344') {
			$return_json = array('code'=> 401 , 'message' => 'The phone number is not being registered.');
	    	header('Content-type: application/json');
	    	echo json_encode($return_json);
		} else {
			$json_data = file_get_contents('http://padcmyanmar.com/padc-3/mm-news/apis/v1/user_info.json');  

			$return_json = array('code'=> 200 , 
				'message' => 'Login succcess',
				'login_user' => json_decode($json_data)
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