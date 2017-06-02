package instateam.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by mark on 5/31/2017.
 */
@Entity
public class Project {
  @Id
  private Long id;

  private String name;

  private boolean complete;

}
