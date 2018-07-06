package ru.tsystem.javaschool.ordinaalena.DAO.api;

import ru.tsystem.javaschool.ordinaalena.entities.Role;

import java.util.List;

public interface RoleDAO {
    void persist(Role role);
    Role find(int id, Class<Role > className);
    void remove(Role  role);
    void merge(   Role role);
    List<Role > getAll(Class<Role > className);
    public Role getRoleByName(String roleName);
}