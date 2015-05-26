
package relationshipBuilding.dto;

import java.io.Serializable;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

/**
 * 検索ワードを格納するDtoクラス.
 * @author nagaimidori
 *
 */
@Component(instance = InstanceType.SESSION)
public class SearchWordDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//フォームから取得した検索ワードスペースで区切られて格納されている
	public String searchWords;

}
