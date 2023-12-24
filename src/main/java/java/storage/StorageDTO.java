package storage;


public class StorageDTO {
	private String fileName;
    private long size;
    
	public StorageDTO() {
		super();
	}
	public StorageDTO(String fileName, long size) {
		super();
		this.fileName = fileName;
		this.size = size;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
    
    
}
