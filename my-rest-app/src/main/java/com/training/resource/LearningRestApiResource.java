package com.training.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/example")
public class LearningRestApiResource {

	//http://localhost:8080/my-rest-app/api/example/01?empno=1001&name=John&salary=20000
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/01")
	public String example01(
			@QueryParam("empno") int empno,
			@QueryParam("name") String name,
			@QueryParam("salary") double salary) {
		
		System.out.println(empno + " , " + name + " , " + salary);
		
		return "Response from example01";
	}
}
