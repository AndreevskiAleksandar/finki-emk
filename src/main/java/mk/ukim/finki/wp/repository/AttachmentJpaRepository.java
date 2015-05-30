package mk.ukim.finki.wp.repository;

import java.util.List;

public interface AttachmentJpaRepository<T> extends JpaSpecificationRepository<T> {
	public List<T> findByObjectId(Long id);
}
