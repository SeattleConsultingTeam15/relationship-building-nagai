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

import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.struts.annotation.Execute;

import relationshipBuilding.dto.LoginDto;

/**
 * ログアウト画面を制御するアクセスクラス.
 * @author nagaimidori
 *
 */
public class LogoutAction {

	/**
	 * セッションに保持されているデータを格納するDto
	 */
	@Resource
	protected LoginDto loginDto;
	
	/**
	 * ログアウト画面にアクセスした時に呼び出されるメソッド.
	 * @return ログアウト画面を返します
	 */	
	@RemoveSession(name = "loginDto") //セッション管理セッションを破棄してログアウトする
	@Execute(validator = false)
	public String index() {
		return "logout.jsp";
	}


}
