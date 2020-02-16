package info.quazi.rest.entity;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.jboss.seam.annotations.Name;

import lombok.Data;

@Name("userEntity")
@Data
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEntity {
	protected String userName;
	protected String password;
	protected String email;

}
