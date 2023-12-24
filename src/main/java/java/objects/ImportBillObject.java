package objects;

public class ImportBillObject extends BillObject {
	private int import_bill_id; 
	private String import_bill_code;
	private int import_bill_provider_id; 
	private int import_bill_target_workplace_id; 
	private int import_bill_price;
	private int import_bill_product_id;
	private int import_bill_product_quantity;	
	
	public String getImport_bill_code() {
		return import_bill_code;
	}
	public void setImport_bill_code(String import_bill_code) {
		this.import_bill_code = import_bill_code;
	}

	public int getImport_bill_id() {
		return import_bill_id;
	}
	public void setImport_bill_id(int import_bill_id) {
		this.import_bill_id = import_bill_id;
	}
	public int getImport_bill_provider_id() {
		return import_bill_provider_id;
	}
	public void setImport_bill_provider_id(int import_bill_provider_id) {
		this.import_bill_provider_id = import_bill_provider_id;
	}
	public int getImport_bill_target_workplace_id() {
		return import_bill_target_workplace_id;
	}
	public void setImport_bill_target_workplace_id(int import_bill_target_workplace_id) {
		this.import_bill_target_workplace_id = import_bill_target_workplace_id;
	}
	public int getImport_bill_price() {
		return import_bill_price;
	}
	public void setImport_bill_price(int import_bill_price) {
		this.import_bill_price = import_bill_price;
	}
	public int getImport_bill_product_id() {
		return import_bill_product_id;
	}
	public void setImport_bill_product_id(int import_bill_product_id) {
		this.import_bill_product_id = import_bill_product_id;
	}
	public int getImport_bill_product_quantity() {
		return import_bill_product_quantity;
	}
	public void setImport_bill_product_quantity(int import_bill_product_quantity) {
		this.import_bill_product_quantity = import_bill_product_quantity;
	}

}
