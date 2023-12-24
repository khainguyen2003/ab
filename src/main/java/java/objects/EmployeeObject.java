package objects;

public class EmployeeObject extends UserObject{
	private int employee_id; 
	private String employee_contract_expired_date; 
	private boolean employee_status; 
	private byte employee_work_time_length; 
	private String employee_workplace_start_time; 
	private int employee_workplace_id; 
	private int employee_work_finished_day; 
	private byte employee_role;
	
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public String getEmployee_contract_expired_date() {
		return employee_contract_expired_date;
	}
	public void setEmployee_contract_expired_date(String employee_contract_expired_date) {
		this.employee_contract_expired_date = employee_contract_expired_date;
	}
	public boolean isEmployee_status() {
		return employee_status;
	}
	public void setEmployee_status(boolean employee_status) {
		this.employee_status = employee_status;
	}
	public byte getEmployee_work_time_length() {
		return employee_work_time_length;
	}
	public void setEmployee_work_time_length(byte employee_work_time_length) {
		this.employee_work_time_length = employee_work_time_length;
	}
	public String getEmployee_workplace_start_time() {
		return employee_workplace_start_time;
	}
	public void setEmployee_workplace_start_time(String employee_workplace_start_time) {
		this.employee_workplace_start_time = employee_workplace_start_time;
	}
	public int getEmployee_workplace_id() {
		return employee_workplace_id;
	}
	public void setEmployee_workplace_id(int employee_workplace_id) {
		this.employee_workplace_id = employee_workplace_id;
	}
	public int getEmployee_work_finished_day() {
		return employee_work_finished_day;
	}
	public void setEmployee_work_finished_day(int employee_work_finished_day) {
		this.employee_work_finished_day = employee_work_finished_day;
	}
	public byte getEmployee_role() {
		return employee_role;
	}
	public void setEmployee_role(byte employee_role) {
		this.employee_role = employee_role;
	} 	
}
