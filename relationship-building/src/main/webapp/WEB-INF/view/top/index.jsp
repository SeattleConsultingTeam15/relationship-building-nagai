
<!--
session.isNew():クライアントがセッションを管理できないときにtrueを返す(タイムアウト、初めてアクセスした時等)
 -->
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Human Relationship building</title>
<script type="text/javascript"src="./makeJson"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script src="${f:url('/js/jquery.js')}"></script>
<script type="text/javascript">
   function getJson() {
     //var xmlhttp = createXMLHttpRequest(); //旧バージョンのIEなどに対応する場合
     var xmlhttp = new XMLHttpRequest();

     xmlhttp.onreadystatechange = function () {
       if (xmlhttp.readyState == 4) {
         if (xmlhttp.status == 200) {
           var data = JSON.parse(xmlhttp.responseText);

           var elem = document.getElementById("output_talkedRate");
           elem.innerText = java_message.tlakedEmployeesRate;
           var elem = document.getElementById("output_nontalkedRate");
           elem.innerText = java_message.notTlakedEmployeesRate;
           var elem = document.getElementById("output_totalemp");
           elem.innerText = java_message.totalEmployeesNumber;
           var elem = document.getElementById("output_talkedNumber");
           elem.innerText = java_message.talkedEmployeesNumber;
           var elem = document.getElementById("output_notTalkedNumber");
           elem.innerText = java_message.notTalkedEmployeesNumber;
         } else {
         }
       }
     }
     xmlhttp.open("GET", "data.json");
     xmlhttp.send();
   }

   function createXMLHttpRequest() {
     if (window.XMLHttpRequest) { return new XMLHttpRequest() }
     if (window.ActiveXObject) {
       try { return new ActiveXObject("Msxml2.XMLHTTP.6.0") } catch (e) { }
       try { return new ActiveXObject("Msxml2.XMLHTTP.3.0") } catch (e) { }
       try { return new ActiveXObject("Microsoft.XMLHTTP") } catch (e) { }
     }
     return false;
   }

  </script>
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



	</div><!-- container -->
<hr>
	 <input id="Button_Get" type="button" value="JSON読み込み" onclick="getJson();" />
	 <input type="button" value="makeJson"onclick="$('#output_talkedRate').load('makeJson');"/>

  <div>受信情報</div>
  あなたが話したことがあるのは社員全体の:<span id="output_talkedRate"></span><br />
  あなたが話したことがないのは社員全体の:<span id="output_nontalledRate"></span><br />
  全体の人数:<span id="output_totalemp"></span><br />
  話したことがある人の人数:<span id="output_talkedNumber"></span><br />
  話したことがない人の人数:<span id="output_notTalkedNumber"></span><br />

<br/>
		<div id="footer">
			<address>Copyright(C) team15</address>
		</div><!-- footer -->

</body>
</html>