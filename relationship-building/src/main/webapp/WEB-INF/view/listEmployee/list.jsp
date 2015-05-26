
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
			<h2>一覧ページ</h2>
			<!-- ページャー機能により使用するリンク -->
			<p>${link}</p>
			

			<table>
				<s:form>
					<tr>
						<td><input type="text" name="searchWords" value ="${searchWord}"></td>
						<td><input type="submit" name="searchEmployees" value="検索"></td>
					</tr>
				</s:form>
				<tr>
					<td>名前</td>
					<td>ふりがな</td>
					<td>生年月日</td>
					<td>電話番号</td>
					<td>話した・話していない</td>
					<td>詳細</td>
					<td>更新</td>
					<td>削除</td>
				</tr>

				<c:forEach var="employees" items="${employeesList}">
					
						<tr>
							<td><label>${employees.empName}</label></td>
							<td><label>${employees.empFrigana}</label></td>
							<td><label>${employees.birthday}</label></td>
							<td><label>${employees.telephoneNumber}</label></td>
							<td><label>${employees.status}</label></td>
							<td>
								<form name="describeActionForm" method="post"
								action="/relationship-building/describe/">
								<input type="hidden" name="selectId" value="${employees.empId}">
								<input type="submit" name="" value="詳細">
								</form>
							</td>
							<td>
								<form name="updateActionForm" method="post"
								action="/relationship-building/update/">
								<input type="hidden" name="selectId" value="${employees.empId}">
								<input type="submit" name="" value="更新">
								</form>
							</td>
							<td>
								<form name="deleteActionForm" method="post"
								action="/relationship-building/delete/">
								<input type="hidden" name="selectId" value="${employees.empId}">
								<input type="submit" name="" value="削除">
								</form>
							</td>
						</tr>
				</c:forEach>

			</table>
			<p>${noSearchMessage}</p>
		</div>
		<div id="footer">
			<address>Copyright(C) team15</address>
		</div>
		<!-- footer -->

	</div>
	<!-- container -->

</body>
</html>