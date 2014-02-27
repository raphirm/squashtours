/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pw.raphael.squashtours.dao;

/**
 *
 * @author raphi
 */
import java.util.List;
 
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pw.raphael.squashtours.models.LoginModel;
 
 
@Repository
public class EmployeeDAOImpl  {
 
    @Autowired
        private SessionFactory sessionFactory;
 
    public void addEmployee(LoginModel employee) {
        this.sessionFactory.getCurrentSession().save(employee);
    }
 
    @SuppressWarnings("unchecked")
    public List<LoginModel> getAllEmployees() {
        return this.sessionFactory.getCurrentSession().createQuery("from EmployeeEntity").list();
    }
 
    public void deleteEmployee(Integer employeeId) {
        LoginModel employee = (LoginModel) sessionFactory.getCurrentSession().load(
                LoginModel.class, employeeId);
        if (null != employee) {
            this.sessionFactory.getCurrentSession().delete(employee);
        }
    }
}