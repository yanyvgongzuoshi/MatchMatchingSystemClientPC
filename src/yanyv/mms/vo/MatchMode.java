package yanyv.mms.vo;

public class MatchMode {
	private int id;
	private String name;
	private String uri;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public String toString() {
		return name;
	}
}
