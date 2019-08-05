package Resource;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;

import javax.ws.rs.core.Application;

import Resource.UserResource;

@ApplicationPath("pt")
public class AppConf extends Application {
	
	private Set<Class<?>> resources = new HashSet<>();
	
	public AppConf() {
		System.out.println("creando app");
		resources.add(UserResource.class);
	}
	
	@Override
	public Set<Class<?>> getClasses(){
		return resources;
	}
}