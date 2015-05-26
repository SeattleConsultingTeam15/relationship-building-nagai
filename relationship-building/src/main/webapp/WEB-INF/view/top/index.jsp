
<!-- 
session.isNew():クライアントがセッションを管理できないときにtrueを返す(タイムアウト、初めてアクセスした時等)
 -->
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Human Relationship building</title>
</head>

<body>

<div id="container">

		<div id="header">
		
			<p id="logo">Human　Relationship building</p>
			
			<div id="navbar">
				<ul>
					<li><a href="http://localhost:8080/relationship-building/">TOP</a></li>
					<li><a href="http://localhost:8080/relationship-building/listEmployee/">一覧</a></li>
					<li><a href="http://localhost:8080/relationship-building/registerEmployee/">新規登録</a></li>
					<li><a href="http://localhost:8080/relationship-building/logout/">ログアウト</a></li>
				</ul>
			</div><!-- navbar -->
			
		</div><!-- header -->
		<div>
			<h2>ダッシュボード</h2>
			<!--新規社員登録フォーム  -->

			<p>こんにちは${loginDto.userName}さん</p>

			<p>あなたが話したことがあるのは社員全体の${analysisEmployeesDto.talkedEmployeesRate}%です
				${analysisEmployeesDto.talkedEmployeesNumber}/${analysisEmployeesDto.totalEmployeesNumber}</p>

			<p>あなたが話したことがないのは社員全体の${analysisEmployeesDto.notTalkedEmployeesRate}%です
				${analysisEmployeesDto.notTalkedEmployeesNumber}/${analysisEmployeesDto.totalEmployeesNumber}</p>

			<p>全体の人数</p>
			<p>${analysisEmployeesDto.totalEmployeesNumber}</p>

			<p>話したことがある人の人数</p>
			<p>${analysisEmployeesDto.talkedEmployeesNumber}</p>

			<p>話したことがない人の人数</p>
			<p>${analysisEmployeesDto.notTalkedEmployeesNumber}</p>
		</div>
		
		<div id="footer">
			<address>Copyright(C) team15</address>
		</div><!-- footer -->
		
	</div><!-- container -->
	
</body>
</html>