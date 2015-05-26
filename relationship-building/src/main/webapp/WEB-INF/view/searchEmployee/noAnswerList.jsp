
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
					<li><a href="http://localhost:8080/relationship-building/list/">一覧</a></li>
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
						<td><input type="text" name="searchWords"></td>
						<td><input type="submit" name="searchEmployees" value="検索"></td>
					</tr>
					<tr>
						<td>名前</td>
						<td>ふりがな</td>
						<td>生年月日</td>
						<td>電話番号</td>
						<td>話した・話していない</td>
						<td>更新</td>
						<td>削除</td>
					</tr>
					<tr><td>条件に一致する項目はありませんでした</td></tr>
				</s:form>
			</table>
		</div>
		<div id="footer">
			<address>Copyright(C) team15</address>
		</div>
		<!-- footer -->

	</div>
	<!-- container -->

</body>
</html>