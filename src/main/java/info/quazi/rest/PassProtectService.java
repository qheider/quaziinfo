package info.quazi.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.witchcraft.base.entity.UserUtilAction;
import org.witchcraft.seam.action.BaseAction;

import com.nas.recovery.web.action.users.UserAction;
import com.oreon.kg.domain.PassProtect;
import com.oreon.kg.domain.users.User;

import info.quazi.rest.entity.PassProtectRESTEntity;
import info.quazi.rest.entity.UserEntity;

@Path("/PassProtect")
@Scope(ScopeType.APPLICATION)
@Name("passProtectService")
public class PassProtectService extends BaseAction<User> {
	
	private static final long serialVersionUID = 4552139741258395692L;
	@In(create = true)
	protected FullTextEntityManager entityManager;
	@In(create = true)
	protected UserAction userAction;

	@GET
	@Path("pass/{param}")
	@Produces("application/json")
	public UserEntity getMsg(@PathParam("param") String msg) {
//		UserUtilAction userUtilAction = (UserUtilAction)Component.getInstance("userUtilAction");
//		if(userUtilAction.getCurrentUser().getEnabled()) {
		
		User usr = new User();
		usr = userAction.findByUnqUserName(msg);
		UserEntity usrE = new UserEntity();
		usrE.setUserName(usr.getDisplayName());
		usrE.setPassword(usr.getPassword());
		usrE.setEmail(usr.getEmail());
		return usrE;
//		}else return null;
	}

	@GET
	@Path("info/{param}")
	@Produces("application/json")
	public PassProtectRESTEntity getPass(@PathParam("param") String msg) {
		List<PassProtect> searchResult = new ArrayList<>();
//		UserUtilAction userUtilAction = (UserUtilAction)Component.getInstance("userUtilAction");
//		if(userUtilAction.getCurrentUser().getEnabled()) {
			
		String query = "select p from PassProtect p where p.companyName=?1";
		searchResult = (this.executeQuery(query, msg));
		PassProtectRESTEntity p=new PassProtectRESTEntity();
		if(!searchResult.isEmpty()) {
			p.setCompanyName(searchResult.get(0).getCompanyName());
			p.setCompanyPassword(searchResult.get(0).getCompanyPassword());
			p.setCompanyUserName(searchResult.get(0).getCompanyUserName());
		}
		return p;
//		}else return null;

	}

}