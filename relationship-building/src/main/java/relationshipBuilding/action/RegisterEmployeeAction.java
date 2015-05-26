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
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import relationshipBuilding.dto.EmployeeDto;
import relationshipBuilding.dto.EmployeesViewDto;
import relationshipBuilding.exception.EmployeesStatusException;
import relationshipBuilding.form.EmployeesForm;
import relationshipBuilding.service.CommonService;
import relationshipBuilding.service.ResisterEmployeeService;

/**
 * 社員情報の登録を制御するアクションクラス.
 * @author nagaimidori
 *
 */

public class RegisterEmployeeAction {
	
	//社員情報を表示するためのDto(エンティティを格納するリストを作成する)
	public EmployeesViewDto employeesViewDto;
	@Resource
	//社員情報を表示するためのDto(エンティティを格納するリストを作成する)
	public EmployeeDto employeeDto;
	
	//社員情報を格納するフォーム
	@ActionForm
	@Resource
	public EmployeesForm employeesForm;

	//社員情報登録機能に関するサービスクラス
	@Resource
	protected ResisterEmployeeService resisterService;
	
	//社員情報登録機能に関するサービスクラス
	@Resource
	protected CommonService commonService;
	
	/**
	 * 社員情報の新規登録画面にアクセスした時に呼び出されるメソッド.
	 * @return 社員情報の新規登録画面を返します
	 */
    @Execute(validator = false)
	public String index() {
    	
    	
        return "register.jsp";
        
	}
    
    /**
     * 社員情報新の規登録画面の確認画面へボタンを押下した時に呼び出されるメソッド.
     * @return 社員情報の新規登録画面の確認画面を返します
     */
    @Execute(validator = true, input ="register.jsp") //バリデーションをかける input:検証結果ががNGのときの遷移先
	public String confirm() {
    	
    	//フォームに入力された値をDtoに保存する
		commonService.createEmployeeViewDto(employeesForm,employeeDto);
    	
		return "confirm.jsp";
		
	}
    
    /**
     * 社員情報の新規登録画面の登録ボタンを押下した時に呼び出されるメソッド.
     * @return　社員情報の登録完了画面を返します
     */
    @RemoveSession(name = "employeeDto")
    @Execute(validator = false)
    public String registerEmployee() throws EmployeesStatusException {
    	
    	if(employeeDto.empName == null ||employeeDto.empName =="") {
    		
    		return "/pleaseRetry/";
    		
    	} else {
    		
    		//Dtoの中身をデータベースにinsertするメソッドを呼び出します
    		resisterService.insertEmployee(employeeDto);

    		return "done.jsp";
    	}
    }
    
}
