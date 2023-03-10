package demo.storage;

public class StorageException extends RuntimeException {
	
	private static final long serialVersionUID = 2910579715910324457L;

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
