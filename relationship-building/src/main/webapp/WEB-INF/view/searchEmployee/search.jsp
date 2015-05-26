
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
					<td></td>
					<td>詳細</td>
					<td>更新</td>
					<td>削除</td>
				</tr>

				<c:forEach var="employees" items="${employeesList}">
					<s:form>
						<tr>
							<td><label>${employees.empName}</label></td>
							<td><label>${employees.empFrigana}</label></td>
							<td><label>${employees.birthday}</label></td>
							<td><label>${employees.telephoneNumber}</label></td>
							<td><label>${employees.status}</label></td>
							<td><input type="hidden" name="selectId"
								value="${employees.empId}"></td>
							<td><input type="submit" name="goDescribeForm"
								value="詳細"></td>
							<td><input type="submit" name="goUpdateForm"
								value="更新"></td>
							<td><input type="submit" name="deleteConfirm"
								value="削除"></td>
						</tr>
					</s:form>
				</c:forEach>

			</table>
			<c:if test = "${hasPrev}">
				<a href ="?page = ${page -1}">&lt;前へ</a>
			</c:if>
			<c:if test = "${hasNext}">
				<a href = "?page = ${page + 1 }">次へ&gt;</a>
			</c:if>
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