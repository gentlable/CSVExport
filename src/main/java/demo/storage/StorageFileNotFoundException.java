package demo.storage;

public class StorageFileNotFoundException extends StorageException {

	private static final long serialVersionUID = -169248831269615765L;

	public StorageFileNotFoundException(String message) {
		super(message);
	}

	public StorageFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
