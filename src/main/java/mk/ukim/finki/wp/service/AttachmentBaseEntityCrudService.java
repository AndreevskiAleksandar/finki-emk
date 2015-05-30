package mk.ukim.finki.wp.service;

import mk.ukim.finki.wp.model.AAttachment;
import java.util.List;

public interface AttachmentBaseEntityCrudService<T extends AAttachment> extends
		BaseEntityCrudService<T> {

	public List<T> findByObjectId(Long id);

	public T createNew();

}
