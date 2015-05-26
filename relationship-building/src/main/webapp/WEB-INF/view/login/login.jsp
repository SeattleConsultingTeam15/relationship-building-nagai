
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Human Relationship building</title>
</head>

<body>

	<div id="container">

		<div id="header">

			<p id="logo">Human Relationship building</p>

			
		</div>
		<!-- header -->
		<div>
			<h2>ログイン画面</h2>
			<p>以下のフォームに記入してください。※は必須項目です。</p>
			<p>${loginErrorMessage}</p>
			<html:errors />
			<s:form >
				<table>
					<tr>
						<td>ユーザー名</td>
						<td>
						<!--input type="text" name="userName" required-->
						<html:text property="userName" errorStyleClass="err"
								errorStyle="background-color:pink" />
						</td>
						
					</tr>
					<tr>
						<td>パスワード</td>
						<td>
						<!--input type="text" name="password" required-->
						<html:password property="password" errorStyleClass="err"
								errorStyle="background-color:pink" />
						</td>
					</tr>

				</table>
				<input class="submit-btn" type="submit" name="doLogin" value="ログイン">
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