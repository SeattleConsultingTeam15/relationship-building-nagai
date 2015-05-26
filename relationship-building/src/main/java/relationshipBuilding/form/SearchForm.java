
package relationshipBuilding.form;

import org.seasar.struts.annotation.IntegerType;

/**
 * ページパラメータを受け取るためのフォームクラス.
 * @author nagaimidori
 *
 */
public class SearchForm {
	
	//ページ番号を格納する変数
	@IntegerType //Integerに変換できるStringなのか判定するため
	public String page;

	//選択された社員のIDを格納する変数
	public String selectId;

	//フォームから取得した検索ワードスペースで区切られて格納さ
	public String searchWords;

}
