package instateam.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Size(min=3, max=25)
  private String name;

  @ManyToMany(cascade = {CascadeType.ALL})
  private List<Role> rolesNeeded = new ArrayList<>();

  @ManyToMany
  private List<Collaborator> collaborators = new ArrayList<>();
  
  @NotNull
  private String description;


  private String status;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Project(){}

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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

    public List<Role> getRolesNeeded() {
        return rolesNeeded;
    }

    public void setRolesNeeded(List<Role> rolesNeeded) {
        this.rolesNeeded = rolesNeeded;
    }

  public List<Collaborator> getCollaborators() {
    return collaborators;
  }

  public void setCollaborators(List<Collaborator> collaborators) {
    this.collaborators = collaborators;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Project)) return false;

    Project project = (Project) o;

    if (getId() != null ? !getId().equals(project.getId()) : project.getId() != null) return false;
    if (getName() != null ? !getName().equals(project.getName()) : project.getName() != null) return false;
    if (getRolesNeeded() != null ? !getRolesNeeded().equals(project.getRolesNeeded()) : project.getRolesNeeded() != null)
      return false;
    if (getCollaborators() != null ? !getCollaborators().equals(project.getCollaborators()) : project.getCollaborators() != null)
      return false;
    if (getDescription() != null ? !getDescription().equals(project.getDescription()) : project.getDescription() != null)
      return false;
    return getStatus() != null ? getStatus().equals(project.getStatus()) : project.getStatus() == null;
  }

  @Override
  public int hashCode() {
    int result = getId() != null ? getId().hashCode() : 0;
    result = 31 * result + (getName() != null ? getName().hashCode() : 0);
    result = 31 * result + (getRolesNeeded() != null ? getRolesNeeded().hashCode() : 0);
    result = 31 * result + (getCollaborators() != null ? getCollaborators().hashCode() : 0);
    result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
    result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
    return result;
  }
}
