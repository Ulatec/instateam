package instateam.dao;

import instateam.model.Role;

import java.util.List;

/**
 * Created by mark on 7/9/2017.
 */
public interface RoleDao {
    List<Role> findAll();
    Role findById(Long id);
    void save(Role role);
    void delete(Role role);
}
