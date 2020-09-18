package servlet;

import entity.product.Shoes;
import org.apache.log4j.Logger;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Objects;
import java.util.Random;

@MultipartConfig
@WebServlet("/addProduct")
public class ProductAddController extends HttpServlet {
    private final Logger logger = Logger.getLogger(ProductAddController.class);
    private ProductService productService;

    private Random random = new Random();

    public int random() {
        return (random.nextInt(100000));
    }

    @Override
    public void init() {
        productService = (ProductService) this.getServletContext().getAttribute("productService");
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Shoes shoes = new Shoes(
                request.getParameter("name"),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("category")),
                Double.parseDouble(request.getParameter("price")));
        boolean isValid = false;
        logger.debug(shoes);

        shoes.setImage1(saveImage(request, "file1"));
        shoes.setImage2(saveImage(request, "file2"));
        shoes.setBrand(String.valueOf(random() % 3 + 1));
        productService.addProduct(shoes);
        response.sendRedirect("addProduct.jsp");
    }


    protected String saveImage(HttpServletRequest request, String fileName) {
        Part filePart;
        String imgName = null;
        try {
            filePart = request.getPart(fileName);
            if (Objects.isNull(filePart)) {
                return null;
            }
            String path = System.getProperty("user.dir") + File.separator + "src" +
                    File.separator + "main" +
                    File.separator + "webapp" +
                    File.separator + "img";
            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
            }
            imgName = random() + "image" + fileName.charAt(fileName.length() - 1) + ".jpg";
            String pathAvatar = path + File.separator + imgName;
            save(filePart, pathAvatar);
        } catch (IOException | ServletException e) {
            logger.error(e.getMessage());
        }
        String res = "img/" + imgName;
        System.out.println(res);
        return res;
    }

    private void save(Part filePart, String pathAvatar) throws IOException {
        try (OutputStream out = new FileOutputStream(new File(pathAvatar));
             InputStream filecontent = filePart.getInputStream()
        ) {
            int read;
            final byte[] bytes = new byte[1024];
            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            logger.debug("File being uploaded ");
        } catch (FileNotFoundException e) {
            logger.error("Problems during file upload." + e.getMessage());
        }
    }

}
