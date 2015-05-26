/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package relationshipBuilding.action;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import relationshipBuilding.dto.LoginDto;
import relationshipBuilding.form.LoginForm;
import relationshipBuilding.service.LoginService;

/**
 * ログイン画面を制御するアクセスクラス.
 * @author nagaimidori
 *
 */
public class LoginAction {
	
	//ログインエラー時(ユーザー名、パスワードが一致しない時のエラーメッセージ)
	public String loginErrorMessage = "";
	
	//ログインに関するフォームクラス
	@ActionForm
	@Resource
	protected LoginForm loginForm;
	
	//ログイン処理機能に関するサービスクラス
	@Resource
	protected LoginService loginService;
	
	/**
	 * セッションに保持されているデータを格納するDto
	 */
	@Resource
	protected LoginDto loginDto;	
	
	/**
	 * ログアウト画面にアクセスした時に呼び出されるメソッド.
	 * @return ログアウト画面を返します
	 */
	@Execute(validator = false)
	public String index() {	
		
		//ログイン時にエラー(ユーザー名とIDが一致していない)が発生した時にエラーメッセージを出力する
		loginErrorMessage =
				loginService.setLoginErrorMessage(loginDto.loginStatus);
		
		return "login.jsp";
	}
	
	
	/**
	 * ログイン処理を行ったあとのページ遷移先を指定するメソッド.
	 * @return ログイン成功時、失敗時に次に呼び出されるjsp
	 */
	//リダイレクトする、バリデーションに失敗した時はlogin.jspに戻る
	@Execute(redirect=true, validator = true, input = "login.jsp") 
	public String doLogin() {
		
		//jsp名を格納する文字列
		String nextJsp;
		
		//ログイン情報を格納するDtoを作成する
		loginService.createLoginDto(loginDto,loginForm);
		
		//ログインに成功しているか失敗したかを判断するメソッドを呼び出す
		loginDto.loginStatus = loginService.checkLoginStatus(loginDto);
		
		//ログイン状態によって次のページ遷移先を取得する
		nextJsp = loginService.getLoginNextPass(loginDto.loginStatus);
		
		return nextJsp;
	}
	
}
