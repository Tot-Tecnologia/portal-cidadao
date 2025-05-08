package br.tec.tot.dardani.portal_cidadao.infrastructure.firebase.config;

import java.util.HashMap;
import java.util.Map;

import com.google.firebase.auth.AuthErrorCode;
import com.google.firebase.auth.FirebaseAuthException;

public class FirebaseAuthErrorTranslator {

	private static final Map<AuthErrorCode, String> ERROR_MESSAGES = new HashMap<>();

	static {
		ERROR_MESSAGES.put(AuthErrorCode.EMAIL_ALREADY_EXISTS,
				"O e-mail fornecido já está em uso por outro usuário.");
		ERROR_MESSAGES.put(AuthErrorCode.USER_DISABLED,
				"A conta do usuário foi desativada por um administrador.");
		ERROR_MESSAGES.put(AuthErrorCode.USER_NOT_FOUND,
				"Não há nenhum usuário correspondente ao e-mail fornecido.");
	}

	public static String translate(FirebaseAuthException ex) {
		AuthErrorCode errorCode = ex.getAuthErrorCode();

		String defaultMessage = "Ocorreu um erro durante a autenticação. Por favor, tente novamente.";

		if (errorCode == null) {
			return defaultMessage;
		}

		return ERROR_MESSAGES.getOrDefault(errorCode, defaultMessage);
	}

}