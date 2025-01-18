package com.training.resource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.training.model.PassengerDetails;
import com.training.model.PnrDetails;
import com.training.model.PassengerDetails.Gender;
import com.training.model.PassengerDetails.Status;

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


	//http://localhost:8080/my-rest-app/api/example/06/abc/xyz;param4=123?param3=456
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/06/{param1}/{param2}")
	public String example06(@BeanParam MyBean myBean) {
			
		System.out.println(myBean);
		return "Response from example06";
	}
	
	//http://localhost:8080/my-rest-app/api/example/07/abc/xyz;param4=123?param3=456
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/07/{param1}/{param2}")
	public String example07(@Context UriInfo uriInfo) {
		
		System.out.println(uriInfo.getPath());
		System.out.println(uriInfo.getAbsolutePath());
		System.out.println(uriInfo.getBaseUri());
		System.out.println(uriInfo.getRequestUri());
		
		return "Response from example07";
	}

	//http://localhost:8080/my-rest-app/api/example/08?pnrNo=12345
	@GET
	@Produces(MediaType.APPLICATION_JSON) //If we try XML it's not working, what to do?
	@Path("/08")
	public PnrDetails example08(@QueryParam("pnrNo") int pnrNo) {
		PnrDetails pnrDetails = new PnrDetails();
		pnrDetails.setPnrNo(pnrNo);
		pnrDetails.setTrainNo(12121);
		pnrDetails.setTravelDate(LocalDate.of(2025, 1, 31));
		return pnrDetails;
	}
	
	//http://localhost:8080/my-rest-app/api/example/09?pnrNo=12345
	@GET
	@Produces("text/csv")
	@Path("/09")
	public PnrDetails example09(@QueryParam("pnrNo") int pnrNo) {
		PnrDetails pnrDetails = new PnrDetails();
		pnrDetails.setPnrNo(pnrNo);
		pnrDetails.setTrainNo(12121);
		pnrDetails.setTravelDate(LocalDate.of(2025, 1, 31));
		return pnrDetails;
	}

	//http://localhost:8080/my-rest-app/api/example/10?pnrNo=12345
	@GET
	@Path("/10")
	public Response example10(@QueryParam("pnrNo") int pnrNo) {
		PnrDetails pnrDetails = new PnrDetails();
		pnrDetails.setPnrNo(pnrNo);
		pnrDetails.setTrainNo(12121);
		pnrDetails.setTravelDate(LocalDate.of(2025, 1, 31));
	
		return Response
				.ok()
				.entity(pnrDetails)
				.type(MediaType.APPLICATION_JSON)
				.header("cache-control", "no-cache")
				.build();
	}
	
	//content negotiation
	//http://localhost:8080/my-rest-app/api/example/11/1234567890.json
	@GET
	@Path("/11/{pnrNo}.{format}")
	public Response example11(@PathParam("pnrNo") int pnrNo, @PathParam("format") String format) {
		
		PnrDetails pnrDetails = new PnrDetails();
		pnrDetails.setPnrNo(pnrNo);
		pnrDetails.setTrainNo(12121);
		pnrDetails.setTravelDate(LocalDate.of(2025, 1, 31));
	
		if(format.equals("xml"))
			return Response
					.ok()
					.entity(pnrDetails)
					.type(MediaType.APPLICATION_JSON)
					.build();
		else if(format.equals("csv"))
			return Response
					.ok()
					.entity(pnrDetails)
					.type("text/csv")
					.build();
		else
			return Response
					.ok()
					.entity(pnrDetails)
					.type(MediaType.APPLICATION_JSON)
					.build();
	}

}


class MyBean {
	
	@PathParam("param1")
	private String param1;
	
	@PathParam("param2")
	private String param2;
	
	@QueryParam("param3")
	private String param3;
	
	@MatrixParam("param4")
	private String param4;
	
	@HeaderParam("app-key")
	private String appKey;

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public String getParam3() {
		return param3;
	}

	public void setParam3(String param3) {
		this.param3 = param3;
	}

	public String getParam4() {
		return param4;
	}

	public void setParam4(String param4) {
		this.param4 = param4;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	@Override
	public String toString() {
		return "MyBean [param1=" + param1 + ", param2=" + param2 + ", param3=" + param3 + ", param4=" + param4
				+ ", appKey=" + appKey + "]";
	}

	
	
}