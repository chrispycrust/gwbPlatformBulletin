package com.fdmgroup.gwbPlatformBulletin.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fdmgroup.gwbPlatformBulletin.model.Member;

public class GetAuthorName extends JsonSerializer<Member> {
	
	@Override
    public void serialize(Member author, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        
		jsonGenerator.writeStartObject();
			
			jsonGenerator.writeStringField("honorific", author.getHonorific());
	        jsonGenerator.writeStringField("firstName", author.getFirstName());
	        jsonGenerator.writeStringField("lastName", author.getLastName());
	        
	    jsonGenerator.writeEndObject();
    }

}
