package info.quazi.rest;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.witchcraft.base.entity.UserUtilAction;

import com.oreon.kg.domain.users.User;

import info.quazi.rest.entity.UserEntity;

@Path("/portal")
@Scope(ScopeType.APPLICATION)
@Name("loginService")
public class LoginService {
	@In
	EntityManager entityManager;

	@In
	Credentials credentials;
	@In
	Identity identity;

	@POST
	@Path("/login")
	@Consumes("application/json")
	public Response login(UserEntity user) {

	    credentials.setUsername(user.getUserName());
	    credentials.setPassword(user.getPassword());
	    String s=identity.login();
	    UserUtilAction userUtilAction = (UserUtilAction)Component.getInstance("userUtilAction");
	    if(userUtilAction.getCurrentUser()!=null && userUtilAction.getCurrentUser().getEmail()!=null) {
	    	
	    	String output = userUtilAction.getCurrentUser().getEmail();
	    	return Response.status(200).entity(output).build();
	    }else
	    {
	    	
	    	String output = "user not found";
	    	return Response.status(400).entity(output).build();
	    	
	    }

	}
}
