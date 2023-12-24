package objects;

public class PSObject {
	private short ps_id; 
	private String ps_name; 
	private int ps_manager_id; 
	private String ps_notes; 
	private boolean ps_delete; 
	private String ps_created_date; 
	private String ps_deleted_date; 
	private String ps_modified_date; 
	private String ps_deleted_author; 
	private String ps_table; 
	private boolean ps_enable; 
	private String ps_name_en; 
	private int ps_created_author_id; 
	private byte ps_language;
	
	public PSObject() {
		super();
	}
	public PSObject(short ps_id, String ps_name, int ps_manager_id, String ps_notes, boolean ps_delete,
			String ps_created_date, String ps_deleted_date, String ps_modified_date, String ps_deleted_author,
			String ps_table, boolean ps_enable, String ps_name_en, int ps_created_author_id, byte ps_language) {
		super();
		this.ps_id = ps_id;
		this.ps_name = ps_name;
		this.ps_manager_id = ps_manager_id;
		this.ps_notes = ps_notes;
		this.ps_delete = ps_delete;
		this.ps_created_date = ps_created_date;
		this.ps_deleted_date = ps_deleted_date;
		this.ps_modified_date = ps_modified_date;
		this.ps_deleted_author = ps_deleted_author;
		this.ps_table = ps_table;
		this.ps_enable = ps_enable;
		this.ps_name_en = ps_name_en;
		this.ps_created_author_id = ps_created_author_id;
		this.ps_language = ps_language;
	}
	public short getPs_id() {
		return ps_id;
	}
	public String getPs_name() {
		return ps_name;
	}
	public int getPs_manager_id() {
		return ps_manager_id;
	}
	public String getPs_notes() {
		return ps_notes;
	}
	public boolean isPs_delete() {
		return ps_delete;
	}
	public String getPs_created_date() {
		return ps_created_date;
	}
	public String getPs_deleted_date() {
		return ps_deleted_date;
	}
	public String getPs_modified_date() {
		return ps_modified_date;
	}
	public String getPs_deleted_author() {
		return ps_deleted_author;
	}
	public String getPs_table() {
		return ps_table;
	}
	public boolean isPs_enable() {
		return ps_enable;
	}
	public String getPs_name_en() {
		return ps_name_en;
	}
	public int getPs_created_author_id() {
		return ps_created_author_id;
	}
	public byte getPs_language() {
		return ps_language;
	}
	public void setPs_id(short ps_id) {
		this.ps_id = ps_id;
	}
	public void setPs_name(String ps_name) {
		this.ps_name = ps_name;
	}
	public void setPs_manager_id(int ps_manager_id) {
		this.ps_manager_id = ps_manager_id;
	}
	public void setPs_notes(String ps_notes) {
		this.ps_notes = ps_notes;
	}
	public void setPs_delete(boolean ps_delete) {
		this.ps_delete = ps_delete;
	}
	public void setPs_created_date(String ps_created_date) {
		this.ps_created_date = ps_created_date;
	}
	public void setPs_deleted_date(String ps_deleted_date) {
		this.ps_deleted_date = ps_deleted_date;
	}
	public void setPs_modified_date(String ps_modified_date) {
		this.ps_modified_date = ps_modified_date;
	}
	public void setPs_deleted_author(String ps_deleted_author) {
		this.ps_deleted_author = ps_deleted_author;
	}
	public void setPs_table(String ps_table) {
		this.ps_table = ps_table;
	}
	public void setPs_enable(boolean ps_enable) {
		this.ps_enable = ps_enable;
	}
	public void setPs_name_en(String ps_name_en) {
		this.ps_name_en = ps_name_en;
	}
	public void setPs_created_author_id(int ps_created_author_id) {
		this.ps_created_author_id = ps_created_author_id;
	}
	public void setPs_language(byte ps_language) {
		this.ps_language = ps_language;
	}
	@Override
	public String toString() {
		return "PSObject [ps_id=" + ps_id + ", ps_name=" + ps_name + ", ps_manager_id=" + ps_manager_id + ", ps_notes="
				+ ps_notes + ", ps_delete=" + ps_delete + ", ps_created_date=" + ps_created_date + ", ps_deleted_date="
				+ ps_deleted_date + ", ps_modified_date=" + ps_modified_date + ", ps_deleted_author="
				+ ps_deleted_author + ", ps_table=" + ps_table + ", ps_enable=" + ps_enable + ", ps_name_en="
				+ ps_name_en + ", ps_created_author_id=" + ps_created_author_id + ", ps_language=" + ps_language + "]";
	}
	
	
}
