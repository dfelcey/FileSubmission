package com.mulesoft.example;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.Base64;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

public class JPEGUtils {
	public static String CompressJpegImage(byte [] imageData, float com_factor) {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
			BufferedImage image = ImageIO.read(bais);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageWriter writer = (ImageWriter) ImageIO.getImageWritersByFormatName("jpeg").next();

			ImageWriteParam param = writer.getDefaultWriteParam();
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(com_factor); // Change this, float between 0.0 and 1.0

			writer.setOutput(ImageIO.createImageOutputStream(os));
			writer.write(null, new IIOImage(image, null, null), param);
			writer.dispose();

			return Base64.getEncoder().encodeToString(os.toByteArray());
		} catch (Exception e) {
			return null;
		}
	}
	
	public static byte[] FileToBytes(File file)
	{
	    byte[] fileBytes = null;
	    try
	    {
	        fileBytes = Files.readAllBytes(file.toPath());
	    }
	    catch (Exception ex) 
	    {
	        ex.printStackTrace();
	    }
	    return fileBytes;
	}
	
	public static void main(String [] args) {
		System.out.println(JPEGUtils.CompressJpegImage(FileToBytes(new File(args[0])), 0.5f));
	}
}
