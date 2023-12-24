package objects;

public class ExportBillObject extends BillObject {
	private int export_bill_id; 
	private int export_bill_current_workplace_id;
	private int export_bill_customer_id; 
	private int export_bill_price;
	private String export_bill_target_address;
	private int export_bill_product_id;
	private int export_bill_discount;
	
	public int getExport_bill_id() {
		return export_bill_id;
	}
	public void setExport_bill_id(int export_bill_id) {
		this.export_bill_id = export_bill_id;
	}
	public int getExport_bill_current_workplace_id() {
		return export_bill_current_workplace_id;
	}
	public void setExport_bill_current_workplace_id(int export_bill_current_workplace_id) {
		this.export_bill_current_workplace_id = export_bill_current_workplace_id;
	}
	public int getExport_bill_customer_id() {
		return export_bill_customer_id;
	}
	public void setExport_bill_customer_id(int export_bill_customer_id) {
		this.export_bill_customer_id = export_bill_customer_id;
	}
	public int getExport_bill_price() {
		return export_bill_price;
	}
	public void setExport_bill_price(int export_bill_price) {
		this.export_bill_price = export_bill_price;
	}
	public String getExport_bill_target_address() {
		return export_bill_target_address;
	}
	public void setExport_bill_target_address(String export_bill_target_address) {
		this.export_bill_target_address = export_bill_target_address;
	}
	public int getExport_bill_product_id() {
		return export_bill_product_id;
	}
	public void setExport_bill_product_id(int export_bill_product_id) {
		this.export_bill_product_id = export_bill_product_id;
	}
	public int getExport_bill_discount() {
		return export_bill_discount;
	}
	public void setExport_bill_discount(int export_bill_discount) {
		this.export_bill_discount = export_bill_discount;
	}
	
	
}
