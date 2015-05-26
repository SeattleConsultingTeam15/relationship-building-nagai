package relationshipBuilding.service;

import java.sql.Timestamp;

import org.seasar.extension.jdbc.service.S2AbstractService;

import relationshipBuilding.entity.Employee;


/**
 * 人間関係構築アプリのロジックを指定するクラス.
 * @author nagaimidori
 *
 */
public class DeleteEmployeeService extends S2AbstractService<Employee> {

	/**
	 * 一覧ページの表における指定された行を削除するメソッド.
	 * @param employeesList 指定された行における社員情報を格納したエンティティが格納されているリスト
	 */

	public void deleteEmployeeById(Employee employee) {

		//現在のタイムスタンプを格納する変数
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		//デリートフラグを立てる
		employee.dleteFlag = 1;
		
		employee.updateDate = now;

		//デリートフラグを立てた社員情報を更新する
		update(employee);
	}

}


