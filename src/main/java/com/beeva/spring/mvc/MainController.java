/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beeva.spring.mvc;


import com.beeva.mongodb.form.RegisterForm;
import com.beeva.mongodb.model.Campaign;
import com.beeva.mongodb.service.DataService;
import org.apache.log4j.Logger;
import org.owasp.esapi.errors.ValidationException;
import org.owasp.esapi.reference.DefaultValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
 * @author anjana.m
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



    //private DataService dataService;

//	@Autowired
//	public MainController(DataService dataService) {
//		this.dataService = dataService;
//	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultPage(ModelMap map) {
		return "redirect:/menu";
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
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "denied";
	}
	
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String menu(ModelMap map) {
		
		return "menu";
	}
	
	@RequestMapping(value = "/listUsers", method = RequestMethod.GET)
	public String listUsers(ModelMap map) {
		
		//Iterable<User> users= userRepositoryDao.findAll();
		//map.addAttribute("users", users);
		
		return "listUsers";
	}
	
	@RequestMapping(value = "/listCampaigns", method = RequestMethod.GET)
	public String listCampaigns(ModelMap map) {
		
		map.addAttribute("new_campaign", new Campaign());
		//Iterable<Campaign> campaings= campaignRepositoryDao.findAll();
		//map.addAttribute("campaigns",campaings);
		
		return "listCampaigns";
	}
	
	@PreAuthorize("hasRole('ROLE_CAMPAIGN')")
	@RequestMapping(value = "/addCampaing", method = RequestMethod.POST)
	public String addCampaign(@ModelAttribute(value = "new_campaign") Campaign new_campaign,
			BindingResult result){
		
		//new_campaign.setId(UUID.randomUUID().toString());
		//campaignRepositoryDao.save(new_campaign);
		
		return "redirect:/listCampaigns";
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
                              @ModelAttribute("registerForm") @Valid RegisterForm inputData,
                              @RequestParam("file") MultipartFile file)
                              throws MethodArgumentNotValidException {

		HttpStatus status = HttpStatus.OK;
		String name;

		try {

			if (file.isEmpty()) {

			}
			//name = SecUtils.encryptSHA1(file.getOriginalFilename() + System.currentTimeMillis());
            name = file.getOriginalFilename();
			// Se valida la imagen subida a traves de OWAPS
			validateInputFile(file, name);

			try {
				BufferedImage image = ImageIO.read(file.getInputStream());

				// It's an image (only BMP, GIF, JPG and PNG are recognized).
				if (image == null) {

					status = HttpStatus.BAD_REQUEST;

				} else {

				}
			} catch (Exception e) {

				logger.error("Invalid file upload type :" + e.getMessage());

				status = HttpStatus.BAD_REQUEST;
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

        List<String> whiteListExtension = new ArrayList<>();

        whiteListExtension.add(PNG_IMAGE_EXTENSION);
        whiteListExtension.add(JPG_IMAGE_EXTENSION);
        whiteListExtension.add(JPEG_IMAGE_EXTENSION);
        whiteListExtension.add(GIF_IMAGE_EXTENSION);

        File tempFile = File.createTempFile(name, TMP_FOLDER);

        validator.assertValidFileUpload(FILE_UPLOAD_CONTEXT, tempFile.getParent(), file.getOriginalFilename(),
                tempFile.getParentFile(), file.getBytes(), TAMANIO_MAXIMO_BYTES, whiteListExtension, true);
    }

}

