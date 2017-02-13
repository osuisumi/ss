<?php
include('config.php');
$mid="147323";
//$mid=1;
try
{
	$connect = @mysql_connect($dbSever,$dbUser,$dbPassword);
	@mysql_query("SET NAMES 'utf8'");
	@mysql_select_db($dbName,$connect);
	$sql = "select a.userid,b.respect as recommend,a.uploaddatetime from account a inner join recommend b on a.id=b.userid where a.id='$mid' order by userid,uploaddatetime";
	$result = mysql_query($sql);
	while($row = mysql_fetch_array($result))
	{
		$arr[] = $row;
	}
	$str='';
	for($i=0;$i<count($arr);$i++)
	{
		$str.=$arr[$i][1].',';
	}
	$userid=$arr[0][0];
	$str=substr($str,0,strlen($str)-1);
	$uploaddatetime=$arr[0][2];
	
	$conn = oci_connect('itlms_info','ITLMS_INFO_HAOYI','192.168.33.45/gddec03');
	if($conn){
		$sql_sp = "call proc_test_evaluate(:userid, :recommend,:uploaddatetime)";  
		$stmt = oci_parse($conn, $sql_sp);  
		oci_bind_by_name($stmt, ":userid", $userid); 
		oci_bind_by_name($stmt, ":recommend", $str);
		oci_bind_by_name($stmt, ":uploaddatetime", $uploaddatetime); 	
		oci_execute($stmt);		
		oci_close($conn);
		print "操作成功";
	}else{
		print "oracle 链接失败";
	}
	if(empty($connect))
	{
		throw new Exception("mysql 链接失败");	
	}
}
catch(Exception $e)
{
	$e->getMessage();
}
?>