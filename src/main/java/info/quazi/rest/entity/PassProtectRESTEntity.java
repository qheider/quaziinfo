package info.quazi.rest.entity;
import org.jboss.seam.annotations.Name;

import lombok.Data;

@Name("passProtectRESTEntity")
@Data
public class PassProtectRESTEntity {
	protected String companyName;
	protected String companyUserName;
	protected String companyPassword;
	
}
