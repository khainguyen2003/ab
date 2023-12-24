package objects;

public class ProductObject extends PCOject {
	private int product_id; 
	private String product_name; 
	private short product_category_id; 
	private byte product_status; 
	private boolean product_enable; 
	private short product_visited; 
	private String product_intro;  
	private String product_bar_code; 
	private boolean product_deleted; 
	private int product_sell_price;
	private int product_import_price;
	private String product_images; 
	private short product_guarantee_id; 
	private String product_desc;
	private String product_notes;
	private String product_last_modified;
	private short minInventory;
	private short maxInventory;
	private short product_pc_id;
	private boolean stoped_sell;
	private int user_modified_id;
	public ProductObject() {
		super();
	}
	
	public ProductObject(int product_id, String product_name, short product_category_id, byte product_status,
			boolean product_enable, short product_visited, String product_intro,
			String product_bar_code, boolean product_deleted, int product_sell_price, int product_import_price,
			String product_images, short product_guarantee_id, String product_desc, String product_notes,
			String product_last_modified, short minInventory, short maxInventory, short product_pc_id,
			boolean stoped_sell, int user_modified_id) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_category_id = product_category_id;
		this.product_status = product_status;
		this.product_enable = product_enable;
		this.product_visited = product_visited;
		this.product_intro = product_intro;
		this.product_bar_code = product_bar_code;
		this.product_deleted = product_deleted;
		this.product_sell_price = product_sell_price;
		this.product_import_price = product_import_price;
		this.product_images = product_images;
		this.product_guarantee_id = product_guarantee_id;
		this.product_desc = product_desc;
		this.product_notes = product_notes;
		this.product_last_modified = product_last_modified;
		this.minInventory = minInventory;
		this.maxInventory = maxInventory;
		this.product_pc_id = product_pc_id;
		this.stoped_sell = stoped_sell;
		this.user_modified_id = user_modified_id;
	}

	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public short getProduct_category_id() {
		return product_category_id;
	}
	public void setProduct_category_id(short product_category_id) {
		this.product_category_id = product_category_id;
	}
	public byte getProduct_status() {
		return product_status;
	}
	public void setProduct_status(byte product_status) {
		this.product_status = product_status;
	}
	public boolean isProduct_enable() {
		return product_enable;
	}
	public void setProduct_enable(boolean product_enable) {
		this.product_enable = product_enable;
	}
	public short getProduct_visited() {
		return product_visited;
	}
	public void setProduct_visited(short product_visited) {
		this.product_visited = product_visited;
	}
	public String getProduct_intro() {
		return product_intro;
	}
	public void setProduct_intro(String product_intro) {
		this.product_intro = product_intro;
	}
	public String getProduct_bar_code() {
		return product_bar_code;
	}
	public void setProduct_bar_code(String product_bar_code) {
		this.product_bar_code = product_bar_code;
	}
	public boolean isProduct_deleted() {
		return product_deleted;
	}
	public void setProduct_deleted(boolean product_deleted) {
		this.product_deleted = product_deleted;
	}
	public int getProduct_sell_price() {
		return product_sell_price;
	}
	public void setProduct_sell_price(int product_sell_price) {
		this.product_sell_price = product_sell_price;
	}
	public int getProduct_import_price() {
		return product_import_price;
	}
	public void setProduct_import_price(int product_import_price) {
		this.product_import_price = product_import_price;
	}
	public String getProduct_images() {
		return product_images;
	}
	public void setProduct_images(String product_images) {
		this.product_images = product_images;
	}
	public short getProduct_guarantee_id() {
		return product_guarantee_id;
	}
	public void setProduct_guarantee_id(short product_guarantee_id) {
		this.product_guarantee_id = product_guarantee_id;
	}
	public String getProduct_desc() {
		return product_desc;
	}
	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}
	public String getProduct_notes() {
		return product_notes;
	}
	public void setProduct_notes(String product_notes) {
		this.product_notes = product_notes;
	}
	public String getProduct_last_modified() {
		return product_last_modified;
	}
	public void setProduct_last_modified(String product_last_modified) {
		this.product_last_modified = product_last_modified;
	}
	public short getMinInventory() {
		return minInventory;
	}
	public void setMinInventory(short minInventory) {
		this.minInventory = minInventory;
	}
	public short getMaxInventory() {
		return maxInventory;
	}
	public void setMaxInventory(short maxInventory) {
		this.maxInventory = maxInventory;
	}
	public short getProduct_pc_id() {
		return product_pc_id;
	}
	public void setProduct_pc_id(short product_pc_id) {
		this.product_pc_id = product_pc_id;
	}
	public boolean isStoped_sell() {
		return stoped_sell;
	}
	public void setStoped_sell(boolean stoped_sell) {
		this.stoped_sell = stoped_sell;
	}
	public int getUser_modified_id() {
		return user_modified_id;
	}
	public void setUser_modified_id(int user_modified_id) {
		this.user_modified_id = user_modified_id;
	}

}
