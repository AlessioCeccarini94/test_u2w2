package alessioceccarini.test_beu2w2.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

	@Value("${cloudinary.name}")
	private String cloudinaryName;
	@Value("${CLOUDINARY_API_KEY}")
	private String cloudinaryApiKey;
	@Value("${cloudinary.secret}")
	private String cloudinarySecret;


	@Bean
	public Cloudinary cloudinary() {
		Map<String, String> config = new HashMap<>();
		config.put("cloudinary.name", cloudinaryName);
		config.put("cloudinary.api_key", cloudinaryApiKey);
		config.put("cloudinary.secret", cloudinarySecret);
		return new Cloudinary(config);
	}
}
