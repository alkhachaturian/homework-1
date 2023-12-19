package com.sample.employees.security;

import com.sample.employees.entity.Employee;
import com.sample.employees.entity.User;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.security.model.RowLevelBiPredicate;
import io.jmix.security.model.RowLevelPolicyAction;
import io.jmix.security.role.annotation.PredicateRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;
import org.springframework.context.ApplicationContext;

@RowLevelRole(name = "Editing employees of own department", code = EditingEmployeesOfOwnDepartmentRole.CODE)
public interface EditingEmployeesOfOwnDepartmentRole {
	String CODE = "editing-employees-of-own-department";

    @PredicateRowLevelPolicy(entityClass = Employee.class, actions = {RowLevelPolicyAction.CREATE, RowLevelPolicyAction.UPDATE, RowLevelPolicyAction.DELETE})
    default RowLevelBiPredicate<Employee, ApplicationContext> employeePredicate() {
        return (employee, applicationContext) -> {
            CurrentAuthentication currentAuthentication = applicationContext.getBean(CurrentAuthentication.class);
            return ((User) currentAuthentication.getUser()).getDepartment().equals(employee.getDepartment());
        };
    }
}