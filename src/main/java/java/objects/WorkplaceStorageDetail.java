package objects;

public class WorkplaceStorageDetail {
	private int workplace_storage_detail_id;
	private int workplace_id;
	private int product_id;
	private int product_quantity;
	private String workplace_storage_created_date;
	private boolean workplace_storage_deleted;
	
	public int getWorkplace_storage_detail_id() {
		return workplace_storage_detail_id;
	}
	public void setWorkplace_storage_detail_id(int workplace_storage_detail_id) {
		this.workplace_storage_detail_id = workplace_storage_detail_id;
	}
	public int getWorkplace_id() {
		return workplace_id;
	}
	public void setWorkplace_id(int workplace_id) {
		this.workplace_id = workplace_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getProduct_quantity() {
		return product_quantity;
	}
	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}
	public String getWorkplace_storage_created_date() {
		return workplace_storage_created_date;
	}
	public void setWorkplace_storage_created_date(String workplace_storage__created_date) {
		this.workplace_storage_created_date = workplace_storage__created_date;
	}
	public boolean isWorkplace_storage_deleted() {
		return workplace_storage_deleted;
	}
	public void setWorkplace_storage_deleted(boolean workplace_storage_deleted) {
		this.workplace_storage_deleted = workplace_storage_deleted;
	}

}
