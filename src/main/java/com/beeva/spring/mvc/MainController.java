/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beeva.spring.mvc;


import com.beeva.mongodb.form.MessageData;
import com.beeva.mongodb.form.MessageForm;
import com.beeva.mongodb.form.RegisterForm;
import com.beeva.mongodb.service.Service;
import org.apache.log4j.Logger;
import org.owasp.esapi.errors.ValidationException;
import org.owasp.esapi.reference.DefaultValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author secure development group
 */
@Controller
public class MainController {

	private static final Logger logger = Logger.getLogger(MainController.class);

    private static final String GIF_IMAGE_EXTENSION = "gif";
    private static final String JPEG_IMAGE_EXTENSION = "jpeg";
    private static final String JPG_IMAGE_EXTENSION = "jpg";
    private static final String PNG_IMAGE_EXTENSION = "png";

    public static final int TAMANIO_MAXIMO_BYTES = 10485760;
    public static final String FILE_UPLOAD_CONTEXT = "upload_image";
    private static final String TMP_FOLDER = "tmp";

    private Service service;
    private List<String> whiteListExtension;

	@Autowired
	public MainController(Service service) {
		this.service = service;

        whiteListExtension =  new ArrayList<>();
        whiteListExtension.add(PNG_IMAGE_EXTENSION);
        whiteListExtension.add(JPG_IMAGE_EXTENSION);
        whiteListExtension.add(JPEG_IMAGE_EXTENSION);
        whiteListExtension.add(GIF_IMAGE_EXTENSION);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultPage(ModelMap map) {
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(ModelMap model) {
		return "register";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "logout";
	}
	
	@RequestMapping(value = "/accessdenied")
	public String denied(ModelMap model) {
		return "denied";
	}
	
	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	public String messages(ModelMap map) {

        List<MessageData> messages = service.getAllMessage();
		map.addAttribute("messages", messages);
		return "messages";
	}

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String message(ModelMap map) {

        return "message";
    }

    @RequestMapping(value = "/publish", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String publishPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                               @ModelAttribute("messageForm") @Valid MessageForm inputData)
            throws MethodArgumentNotValidException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = auth.getName(); //get logged in username

        service.publishMessage (userId, inputData);
        return "redirect:/messages";
    }

	/**
	 * EndPoint para subir imagenes
	 *
	 * @param httpRequest
	 * @param httpResponse
	 * @param file
	 * @return
	 * @throws MethodArgumentNotValidException
	 */

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
	public String registerUser(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                              @ModelAttribute("registerForm") @Valid RegisterForm inputData
                              ,@RequestParam("file") MultipartFile file)
                              throws MethodArgumentNotValidException {

		String name;

		try {

			if (file.isEmpty()) {

			}
            name = file.getOriginalFilename();
			// Se valida la imagen subida a traves de OWAPS
			validateInputFile(file, name);

			try {
				BufferedImage image = ImageIO.read(file.getInputStream());

				// It's an image (only BMP, GIF, JPG and PNG are recognized).
				if (image == null) {

                    logger.error("Invalid file upload type");
				} else {

                    service.registerUser(name, image, inputData);
				}
			} catch (Exception e) {

				logger.error("Invalid file upload type :" + e.getMessage());
			}

		} catch (Exception e1) {

			logger.error("File Upload :" + e1.getMessage());
		}

        return "login";

	}

    /**
     * Metodo que valida a través de ESAPI la imagen subida
     *
     * @param file
     * @throws IOException
     * @throws ValidationException
     */
    public void validateInputFile(MultipartFile file, String name) throws IOException, ValidationException {

        DefaultValidator validator = new DefaultValidator();

        File tempFile = File.createTempFile(name, TMP_FOLDER);

        validator.assertValidFileUpload(FILE_UPLOAD_CONTEXT, tempFile.getParent(), file.getOriginalFilename(),
                tempFile.getParentFile(), file.getBytes(), TAMANIO_MAXIMO_BYTES, whiteListExtension, true);
    }

}

