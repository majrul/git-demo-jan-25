package com.training.config;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.training.model.PnrDetails;

@Provider
@Produces("text/csv")
public class MyCsvMessageBodyWriter implements MessageBodyWriter<PnrDetails> {

	@Override
	public boolean isWriteable(Class<?> arg0, Type type, Annotation[] arg2, MediaType arg3) {
		return type == PnrDetails.class;
	}

	@Override
	public void writeTo(PnrDetails pnrDetails, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4,
			MultivaluedMap<String, Object> arg5, OutputStream out) throws IOException, WebApplicationException {
		
		//we might use third party libraries like OpenCSV, POI, ...
		
		PrintWriter pw = new PrintWriter(out);
		pw.write("PNR No,Train No,Travel Date\n");
		pw.write(pnrDetails.getPnrNo() + "," + pnrDetails.getTrainNo() + "," + pnrDetails.getTravelDate());
		pw.close();
	}

	
	
}
