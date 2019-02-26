package com.mulesoft.example;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

public class JPEGUtils {
	public static String CompressJpegImage(byte [] imageData) {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
			BufferedImage image = ImageIO.read(bais);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageWriter writer = (ImageWriter) ImageIO.getImageWritersByFormatName("jpeg").next();

			ImageWriteParam param = writer.getDefaultWriteParam();
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(0.2f); // Change this, float between 0.0 and 1.0

			writer.setOutput(ImageIO.createImageOutputStream(os));
			writer.write(null, new IIOImage(image, null, null), param);
			writer.dispose();

			return Base64.getEncoder().encodeToString(os.toByteArray());
		} catch (Exception e) {
			return null;
		}
	}
}