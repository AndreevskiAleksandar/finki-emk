package mk.ukim.finki.wp.service.impl;

import mk.ukim.finki.wp.model.AAttachment;
import mk.ukim.finki.wp.repository.AttachmentRepository;
import mk.ukim.finki.wp.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl extends
		BaseEntityCrudServiceImpl<AAttachment, AttachmentRepository> implements
		AttachmentService {

	@Autowired
	private AttachmentRepository repository;

	@Override
	protected AttachmentRepository getRepository() {
		return repository;
	}

}
