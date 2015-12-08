package com.zhenhappy.fastJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhenhappy.security.ClientMessageEncryption;
import org.apache.log4j.Logger;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * 
 * <b>Summary: </b> TODO spring mvc 消息转化器集成FastJson
 */
public class MappingFastJsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	private static Logger log = Logger.getLogger(MappingFastJsonHttpMessageConverter.class);
	// fastjson特性参数
	private SerializerFeature[] serializerFeature;

	public SerializerFeature[] getSerializerFeature() {
		return serializerFeature;
	}

	public void setSerializerFeature(SerializerFeature[] serializerFeature) {
		this.serializerFeature = serializerFeature;
	}

	public MappingFastJsonHttpMessageConverter() {
		super(new MediaType("application", "json", DEFAULT_CHARSET));
	}

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		log.debug("请求Media类型 : " + mediaType.toString());
		return true;
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		// return this.objectMapper.canSerialize(clazz) && canWrite(mediaType);
		return true;
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		// should not be called, since we override canRead/Write instead
		throw new UnsupportedOperationException();
	}

	@Override
	protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i;
		while ((i = inputMessage.getBody().read()) != -1) {
			baos.write(i);
		}
		try {
			String content = baos.toString("UTF-8");
			log.debug("请求Json : " + content);

			Object object = JSON.parseObject(ClientMessageEncryption.decryption(content), clazz);
			return object;
		} catch (Exception e) {
			log.error("参数错误", e);
			throw new HttpMessageNotReadableException("");
		}
	}

	@Override
	protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException,
			HttpMessageNotWritableException {
		String jsonString = JSON.toJSONString(o, serializerFeature);
		log.info("反馈消息: " + jsonString);
		OutputStream out = outputMessage.getBody();
		out.write(jsonString.getBytes(DEFAULT_CHARSET));
		out.flush();
	}

}
