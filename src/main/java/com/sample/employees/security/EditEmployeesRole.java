package com.sample.employees.security;

import com.sample.employees.entity.Employee;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

@ResourceRole(name = "EditEmployees", code = EditEmployeesRole.CODE, scope = "UI")
public interface EditEmployeesRole {
	String CODE = "edit-employees";

    @MenuPolicy(menuIds = "Employee.browse")
    @ScreenPolicy(screenIds = {"Employee.browse", "Employee.edit"})
    void screens();


    @EntityAttributePolicy(entityClass = Employee.class, attributes = {"id", "firstName", "lastName", "email", "birthDate", "department", "*"}, action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Employee.class, actions = EntityPolicyAction.ALL)
    void employee();
}