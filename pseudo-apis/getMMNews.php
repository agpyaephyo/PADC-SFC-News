<?php
    if(isset($_POST['access_token'])){
        if(md5($_POST['access_token']) == 'ee16ce00f6696305900a42ac7dc57d35'){  

            $pageNo = $_POST['page'];
            $json_data = "";
            if($pageNo == '1') {
                $json_data = file_get_contents('http://padcmyanmar.com/padc-3/mm-news/apis/v1/mm-news-p1.json');
            } else if($pageNo == '2') {
                $json_data = file_get_contents('http://padcmyanmar.com/padc-3/mm-news/apis/v1/mm-news-p2.json');
            }
            //$json_data = 'data here';
            $return_json = array('code'=> 200 , 'message' => 'success', 'apiVersion'=>'v1', 'page'=>$pageNo, 'mmNews' => json_decode($json_data));
	        header('Content-type: application/json');
	        echo json_encode($return_json);
        }
        else
        {
            $return_json = array('code'=> 401 , 'message' => 'Your token is invalid.');
	        header('Content-type: application/json');
	        echo json_encode($return_json);
        }
    }
    else
    {
        $return_json = array('code'=> 403 , 'message' => 'Access forbidden');
	    header('Content-type: application/json');
	    echo json_encode($return_json);
    }
    
?>
