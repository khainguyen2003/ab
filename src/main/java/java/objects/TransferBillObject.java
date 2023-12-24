package objects;

public class TransferBillObject extends BillObject{
	private int transfer_bill_id;
	private int transfer_bill_current_workplace_id;
	private int transfer_bill_target_workplace_id;
	public int getTransfer_bill_id() {
		return transfer_bill_id;
	}
	public void setTransfer_bill_id(int transfer_bill_id) {
		this.transfer_bill_id = transfer_bill_id;
	}
	public int getTransfer_bill_current_workplace_id() {
		return transfer_bill_current_workplace_id;
	}
	public void setTransfer_bill_current_workplace_id(int transfer_bill_current_workplace_id) {
		this.transfer_bill_current_workplace_id = transfer_bill_current_workplace_id;
	}
	public int getTransfer_bill_target_workplace_id() {
		return transfer_bill_target_workplace_id;
	}
	public void setTransfer_bill_target_workplace_id(int transfer_bill_target_workplace_id) {
		this.transfer_bill_target_workplace_id = transfer_bill_target_workplace_id;
	}
}
