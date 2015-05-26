
package relationshipBuilding.action;

import org.seasar.struts.annotation.Execute;

/**
 * Exception例外をキャッチした時に呼び出されるクラス.
 * @author nagaimidori
 *
 */
public class ErrorAction {

	/**
	 * 例外が発生した時に呼び出されるメソッド..
	 * @return　エラー発生画面のjsp
	 */
	@Execute(validator = false)
	public String index() {	
		
		return "error.jsp";
	}
}
