package mk.ukim.finki.wp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Blob;

@Entity
@Table(name = "attachments")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AAttachment extends BaseEntity {

	@NotNull
	@JsonIgnore
	private Blob data;

	@NotEmpty
	private String fileName;

	@NotEmpty
	private String contentType;

	@NotNull
	private Long size;

	private String description;

	@NotNull
	private Long objectId;

	public Blob getData() {
		return data;
	}

	public void setData(Blob data) {
		this.data = data;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

}
