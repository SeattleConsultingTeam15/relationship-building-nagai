package relationshipBuilding.interceptor;

public class Snippet {
	public void check(String str) {
	        if (str != null) {
	            System.out.println(str);
	        } else {
	            throw new NullPointerException("null");
	        }
	    }
}

