
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
					<li><a href="">ログアウト</a></li>
				</ul>
			</div>
			<!-- navbar -->

		</div>
		<!-- header -->

		<div id="">
			<h2>確認画面</h2>
			<p>以下の内容で登録いたします。</p>
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
						<tr>

						</tr>
					</table>
				
				<input type="submit" name="registerEmployee" value="登録">
				<input type="button" value="前のページに戻る" onClick="history.go(-1);">
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