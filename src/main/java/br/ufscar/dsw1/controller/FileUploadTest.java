package br.ufscar.dsw1.controller;

import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@MultipartConfig
@WebServlet(name = "FileUploadTest", value = "/amazon-s3")
public class FileUploadTest extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.SA_EAST_1).build();
        final String bucketName = "debatr-media";

        Part filePart = request.getPart("file");
        String filename = "abacate";

        try {
            InputStream inputStream = filePart.getInputStream();
            File uploadedFile = new File("/" + File.separator + filename);
            OutputStream outputStream = new FileOutputStream(uploadedFile);

            int read;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            s3.putObject(bucketName, filename, uploadedFile);
        } catch (IOException | SdkClientException e) {
            e.printStackTrace();
        }
    }
}
