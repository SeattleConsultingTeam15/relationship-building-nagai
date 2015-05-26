
package relationshipBuilding.action;

import org.seasar.struts.annotation.Execute;

/**
 * 登録、更新、削除完了を押したのに戻ってしまったときに呼び出されるクラス.
 * @author nagaimidori 
 *
 */
public class PleaseRetryAction {
	
	/**
	 * 登録、更新、削除完了を押したのに戻ってしまったときに呼び出されるメソッド.
	 * @return もう一度登録をやり直すことを促す画面を返します
	 */
	@Execute(validator = false)
	public String index() {
		
		return "pleaseRetry.jsp";
	}
}
