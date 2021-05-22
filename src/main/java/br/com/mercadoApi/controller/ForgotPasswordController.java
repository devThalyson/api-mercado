package br.com.mercadoApi.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadoApi.model.Cliente;
import br.com.mercadoApi.service.ClienteService;
import net.bytebuddy.utility.RandomString;

@RestController
public class ForgotPasswordController {
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private ClienteService service;

	public static String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}

	@PostMapping("/forgot_password/{email}")
	public ResponseEntity processForgotPassword(HttpServletRequest request, Model model,
			@PathVariable("email") String email) {

		try {
			Cliente cliente = service.getClienteByEmail(email);

			if (cliente != null) {
				String token = RandomString.make(30);

				service.updateResetPasswordToken(token, email);
				String resetPasswordLink = getSiteURL(request) + "/reset_password?token=" + token;
				sendEmail(email, resetPasswordLink);

				return ResponseEntity.ok().build();
			}

			else {

				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {

			return ResponseEntity.badRequest().build();
		}

	}

	public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("devthalyson@gmail.com", "App Mercado - Suporte");
		helper.setTo(recipientEmail);

		String subject = "Recuperação de senha - App Mercado";

		String content = "<p>Olá,</p>" + "<p>Você solicitou a alteração de sua senha.</p>"
				+ "<p>Clique no link para prosseguir com a alteração de sua senha:</p>" + "<p><a href=\"" + link
				+ "\">Alterar minha senha</a></p>" + "<br>"
				+ "<p>Ignore esse email caso você tenha lembrado de sua senha"
				+ " ou caso não tenha sido você que solicitou a troca de senha.</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}

	@GetMapping("/reset_password")
	public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
		Cliente cliente = service.getByResetPasswordToken(token);
		model.addAttribute("token", token);

		if (cliente == null) {
			model.addAttribute("message", "Invalid Token");
			return "Erro: Token Inválido";
		}

		return "<!DOCTYPE html >\r\n" + "<html xmlns:th=\"http://www.thymeleaf.org\"\r\n"
				+ "    xmlns:sec=\"https://www.thymeleaf.org/thymeleaf-extras-springsecurity5\">\r\n" + "<head>\r\n"
				+ "<meta charset=\"ISO-8859-1\">\r\n" + "<title>Alterar Senha</title>\r\n" + "</head>\r\n"
				+ "<body>\r\n" + "\r\n" + "<div>\r\n" + "    <h2>Altere sua senha</h2>\r\n" + "</div>\r\n"
				+ "         \r\n"
				+ "<form th:action=\"@{/reset_password}\" method=\"post\" style=\"max-width: 350px; margin: 0 auto;\">\r\n"
				+ "    <input type=\"hidden\" name=\"token\" th:value=\"${token}\" />\r\n"
				+ "<div class=\"border border-secondary rounded p-3\">\r\n" + "    <div>\r\n" + "        <p>\r\n"
				+ "            <input type=\"password\" name=\"password\" id=\"password\" class=\"form-control\"\r\n"
				+ "                placeholder=\"Digite sua nova senha\" required autofocus />\r\n"
				+ "        </p>         \r\n" + "        <p>\r\n"
				+ "            <input type=\"password\" class=\"form-control\" placeholder=\"Confirme sua nova senha\"\r\n"
				+ "                id=\"confirm_password\" required autofocus />\r\n" + "        </p>         \r\n"
				+ "        <p class=\"text-center\">\r\n"
				+ "            <input type=\"submit\" value=\"Confirmar\" class=\"btn btn-primary\" onclick=\"checkPasswordMatch()\"/>\r\n"
				+ "        </p>\r\n" + "    </div>\r\n" + "</div>\r\n" + "\r\n" + "</form>\r\n" + "\r\n" + "</body>\r\n"
				+ "\r\n" + "\r\n" + "<script type=\"text/javascript\">\r\n" + "\r\n"
				+ "function checkPasswordMatch() {\r\n" + "	var password = document.getElementById(\"password\")\r\n"
				+ "	  , confirm_password = document.getElementById(\"confirm_password\");\r\n" + "\r\n"
				+ "	function validatePassword(){\r\n" + "	  if(password.value != confirm_password.value) {\r\n"
				+ "	    confirm_password.setCustomValidity(\"Senhas diferentes!\");\r\n" + "	  } else {\r\n"
				+ "	    confirm_password.setCustomValidity('');\r\n" + "	  }\r\n" + "	}\r\n" + "\r\n"
				+ "	password.onchange = validatePassword;\r\n" + "	confirm_password.onkeyup = validatePassword;\r\n"
				+ "}\r\n" + "\r\n" + "</script>\r\n" + "\r\n" + "</html>\r\n" + "\r\n" + "\r\n" + "";
	}

	@PostMapping("/reset_password")
	public String processResetPassword(HttpServletRequest request, Model model) {
		String token = request.getParameter("token");
		String password = request.getParameter("password");

		Cliente cliente = service.getByResetPasswordToken(token);
		model.addAttribute("title", "Alterar sua senha");

		if (cliente == null) {
			// model.addAttribute("message", "Token Invalido");
			return "Token Inválido";
		} else {
			service.updatePassword(cliente, password);

			// model.addAttribute("message", "Senha alterada com sucesso!.");
			return "Senha alterada com sucesso!";
		}

	}
}
