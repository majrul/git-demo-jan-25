package com.training.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

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
	
	//http://localhost:8080/my-rest-app/api/example/02;empno=1001;name=John;salary=20000
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/02")
	public String example02(
			@MatrixParam("empno") int empno,
			@MatrixParam("name") String name,
			@MatrixParam("salary") double salary) {
		
		System.out.println(empno + " , " + name + " , " + salary);
		
		return "Response from example02";
	}
	
	//http://localhost:8080/my-rest-app/api/example/03/1001/John/20000
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/03/{empno}/{name}/{salary}") //Path Segment
	public String example03(
			@PathParam("empno") int empno,
			@PathParam("name") String name,
			@PathParam("salary") double salary) {
		
		System.out.println(empno + " , " + name + " , " + salary);
		
		return "Response from example03";
	}
	

	//http://localhost:8080/my-rest-app/api/example/04/1001/John
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/04/{empno : \\d+}/{name : [a-zA-Z]*}") //Path Segment
	public String example04(
			@PathParam("empno") int empno,
			@PathParam("name") String name) {
		
		System.out.println(empno + " , " + name);
		
		return "Response from example04";
	}

	//http://localhost:8080/my-rest-app/api/example/05/1001/1002/1003/1004/1005/action/sendEmail
	//http://localhost:8080/my-rest-app/api/example/05/1001;sendSMSAsWell=true/1002/1003;sendSMSAsWell=true/1004/1005/action/sendEmail
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/05/{empnos : .+}/action/{someAction}")
	public String example05(
			//@PathParam("empnos") String empnos,
			//@PathParam("empnos") List<String> empnos, //this doesn't works
			@PathParam("empnos") List<PathSegment> empnos,
			@PathParam("someAction") String someAction) {
		
		//System.out.println(empnos + " , " + someAction);
		for(PathSegment empno : empnos) {
			System.out.println(empno.getPath());
			System.out.println(empno.getMatrixParameters());
		}
		System.out.println(someAction);
		
		return "Response from example05";
	}


}
