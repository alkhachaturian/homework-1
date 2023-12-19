package com.sample.employees.security;

import com.sample.employees.entity.Department;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

@ResourceRole(name = "Access to departments", code = AccessToDepartmentsRole.CODE, scope = "UI")
public interface AccessToDepartmentsRole {
	String CODE = "access-to-departments";

    @MenuPolicy(menuIds = "Department.browse")
    @ScreenPolicy(screenIds = {"Department.browse", "Department.edit"})
    void screens();

    @EntityAttributePolicy(entityClass = Department.class, attributes = "notes", action = EntityAttributePolicyAction.MODIFY)
    @EntityAttributePolicy(entityClass = Department.class, attributes = {"name", "internalPhoneNumber", "id"}, action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Department.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE})
    void department();
}