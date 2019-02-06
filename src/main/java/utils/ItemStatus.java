package utils;

public enum ItemStatus {
	AGREE(1),DISAGREE(2),UNCHECK(0);
	private int status;
	
	private ItemStatus(int status){
		this.status=status;
	}
}
