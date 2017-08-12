package instateam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Collaborator {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  @Size(min=2, max=30)
  private String name;
  @NotNull
  @ManyToOne
  private Role role;

  public Collaborator(){};

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Collaborator)) return false;

    Collaborator that = (Collaborator) o;

    if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
    if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
    return getRole() != null ? getRole().equals(that.getRole()) : that.getRole() == null;
  }

  @Override
  public int hashCode() {
    int result = getId() != null ? getId().hashCode() : 0;
    result = 31 * result + (getName() != null ? getName().hashCode() : 0);
    result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
    return result;
  }
}
