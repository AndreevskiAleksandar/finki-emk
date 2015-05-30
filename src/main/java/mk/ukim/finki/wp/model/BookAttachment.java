package mk.ukim.finki.wp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ba")
public class BookAttachment extends AAttachment {

}
