package objects;

public class BillObject {
	private int bill_id;
	private String bill_code;
	private String bill_created_date;
	private byte bill_status; 
	private String bill_last_modified_date; 
	private int bill_last_modified_id;
	private int bill_creator_id;
	private int bill_transporter_id;
	private byte bill_type;
	
	public String getBill_code() {
		return bill_code;
	}
	public void setBill_code(String bill_code) {
		this.bill_code = bill_code;
	}
	public int getBill_id() {
		return bill_id;
	}
	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
	}

	public String getBill_created_date() {
		return bill_created_date;
	}
	public void setBill_created_date(String bill_created_date) {
		this.bill_created_date = bill_created_date;
	}
	public byte getBill_status() {
		return bill_status;
	}
	public void setBill_status(byte bill_status) {
		this.bill_status = bill_status;
	}
	public String getBill_last_modified_date() {
		return bill_last_modified_date;
	}
	public void setBill_last_modified_date(String bill_last_modified_date) {
		this.bill_last_modified_date = bill_last_modified_date;
	}
	public int getBill_last_modified_id() {
		return bill_last_modified_id;
	}
	public void setBill_last_modified_id(int bill_last_modified_id) {
		this.bill_last_modified_id = bill_last_modified_id;
	}
	public int getBill_creator_id() {
		return bill_creator_id;
	}
	public void setBill_creator_id(int bill_creator_id) {
		this.bill_creator_id = bill_creator_id;
	}
	public int getBill_transporter_id() {
		return bill_transporter_id;
	}
	public void setBill_transporter_id(int bill_transporter_id) {
		this.bill_transporter_id = bill_transporter_id;
	}
	public byte getBill_type() {
		return bill_type;
	}
	public void setBill_type(byte bill_type) {
		this.bill_type = bill_type;
	}
	
}
