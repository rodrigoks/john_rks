package br.com.johndeere.servlet.filters;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GsonProvider<T> implements MessageBodyReader<T>, MessageBodyWriter<T> {

	private final Gson gson;
	private final Gson gsonIncoming;

	@Context
	private UriInfo ui;

	public GsonProvider() {

		GsonBuilder builder = new GsonBuilder().disableHtmlEscaping().serializeNulls()
				.excludeFieldsWithoutExposeAnnotation().enableComplexMapKeySerialization();

		GsonBuilder builderIncoming = new GsonBuilder().disableHtmlEscaping().serializeNulls()
				.enableComplexMapKeySerialization();

		this.gson = builder.create();
		this.gsonIncoming = builderIncoming.create();

	}

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public T readFrom(Class<T> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {

		InputStreamReader reader = new InputStreamReader(entityStream, "UTF-8");
		try {
			return gsonIncoming.fromJson(reader, type);
		} finally {
			reader.close();
		}
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public long getSize(T t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	@Override
	public void writeTo(T t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {

		PrintWriter printWriter = new PrintWriter(entityStream);
		try {
			String json;
			json = gson.toJson(t);
			printWriter.write(json);
			printWriter.flush();
		} finally {
			printWriter.close();
		}
	}
}
