/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author Albus
 */
@DeclareRoles({"Admin","Student","College"})

@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(loginPage = "/login")
)

@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "hogwarts/jndi",
        callerQuery = "select password from tbluser where userName=?",
        groupsQuery = "select groupName from tbluser u,tblgroup g,tblusergroup ug where u.userID = ug.userID and g.groupID = ug.groupID and userName=?",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        priority = 30)
@ApplicationScoped
@Named
public class config {
    
}
