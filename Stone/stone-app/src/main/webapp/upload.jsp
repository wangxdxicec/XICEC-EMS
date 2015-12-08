<!DOCTYPE html>
<html>
  <head>
    <title>upload.html</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->

  </head>
  
  <body>
	<form action="http://localhost:7006/HuizhanApp/client/user/uploadUserHead" method="post" enctype="multipart/form-data">
		<input name ="userId"/>
		<input type="file" name="head">
		<button type="submit">提交</button>
	</form>
  </body>
</html>
