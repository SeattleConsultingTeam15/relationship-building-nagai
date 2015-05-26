
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Human Relationship building</title>
</head>

<body>

	<div id="container">

		<div id="header">

			<p id="logo">Human Relationship building</p>

			<div id="navbar">
				<ul>
					<li><a href="http://localhost:8080/relationship-building/">TOP</a></li>
					<li><a
						href="http://localhost:8080/relationship-building/list/">一覧</a></li>
					<li><a
						href="http://localhost:8080/relationship-building/register/">新規登録</a></li>
					<li><a
						href="http://localhost:8080/relationship-building/logout/">ログアウト</a></li>
				</ul>
			</div>
			<!-- navbar -->

		</div>
		<!-- header -->
		<div>
			<h2>更新確認画面</h2>
			<p>以下の内容を更新します</p>
			<s:form>
				
					<table>
						<tr>
							<td>名前</td>
							<td><label>${employeeDto.empName}</label></td>
						</tr>
						<tr>
							<td>ふりがな</td>
							<td><label>${employeeDto.empFrigana}</label></td>
						</tr>
						<tr>
							<td>生年月日</td>
							<td><label>${employeeDto.birthdayYear}年${employeeDto.birthdayMonth}月${employeeDto.birthdayDay}日</label></td>
						</tr>
						<tr>
							<td>電話番号</td>
							<td><label>${employeeDto.telephoneNumber}</label></td>
						</tr>
						<tr>
							<td>note</td>
							<td><label>${employeeDto.empNote}</label></td>
						</tr>
						<tr>
							<td>この先輩と</td>
							<td><label>${employeeDto.status}</label></td>
						</tr>
					</table>
					<input type="button" value="前のページに戻る" onClick="history.go(-1);">
					<input type="submit" name="update" value="この行を更新する">
			</s:form>
		</div>
		<div id="footer">
			<address>Copyright(C) team15</address>
		</div>
		<!-- footer -->

	</div>
	<!-- container -->

</body>
</html>