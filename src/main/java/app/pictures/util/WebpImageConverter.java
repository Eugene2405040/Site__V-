package app.pictures.util;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

public class WebpImageConverter {

    private WebpImageConverter(){}

    //  TODO: create methods to resize images

    public static byte[] convertImageToWebp(MultipartFile file) throws IOException {
        if (isContentTypeNotSupported(file.getContentType())) return null;
        BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "webp", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static boolean isContentTypeNotSupported(String contentType){
        return !Arrays.asList(ImageIO.getReaderMIMETypes()).contains(contentType);
    }

    @SuppressWarnings("unused")
    public static void printSupportedParams(){
        String[] readerNames = ImageIO.getReaderFormatNames();
        printList(readerNames, "Reader names:");
        String[] readerMimes = ImageIO.getReaderMIMETypes();
        printList(readerMimes, "Reader MIME types:");
        String[] writerNames = ImageIO.getWriterFormatNames();
        printList(writerNames, "Writer names:");
        String[] writerMimes = ImageIO.getWriterMIMETypes();
        printList(writerMimes, "Writer MIME types:");
    }

    private static void printList(String[] names, String title) {
        System.out.println(title);
        for (String name : names) {
            System.out.println("\t" + name);
        }
    }


}
