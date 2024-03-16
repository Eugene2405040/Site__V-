package app.pictures.util;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class WebpImageConverter {

    private WebpImageConverter() {
    }

    //  TODO: create methods to resize images

    public static byte[] convertImageToWebp(MultipartFile file) throws IOException {
        if (isContentTypeNotSupported(file.getContentType())) return null;
        BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "webp", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    public static byte[] resizePictureToWidth360(byte[] bytes) {
        var start = new Date().getTime();
        BufferedImage targetBufferedImage;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            BufferedImage originalBufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
            double height = originalBufferedImage.getHeight();
            double width = originalBufferedImage.getWidth();
            double ratio = height / width;
            targetBufferedImage = resizeImage(originalBufferedImage
                    , 360, (int) (360 * ratio));

            ImageIO.write(targetBufferedImage, "webp", byteArrayOutputStream);
//        TODO: clear code !
            System.out.println(" =================  WebpImageConverter");
            System.out.println("height = " + height);
            System.out.println("width = " + width);

            System.out.println("height / width (ratio) = " + ratio);

            System.out.println(360D * ratio);

            System.out.println(new Date().getTime() - start);
            System.out.println(" =================  WebpImageConverter End  =================");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static boolean isContentTypeNotSupported(String contentType) {
        return !Arrays.asList(ImageIO.getReaderMIMETypes()).contains(contentType);
    }


    @SuppressWarnings("unused")
    public static void printSupportedParams() {
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
