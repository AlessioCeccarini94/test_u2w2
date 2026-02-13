package alessioceccarini.test_beu2w2.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

	@Value("${CLOUDINARY_NAME}")
	private String cloudinaryName;
	@Value("${CLOUDINARY_API_KEY}")
	private String cloudinaryApiKey;
	@Value("${CLOUDINARY_SECRET}")
	private String cloudinarySecret;


	@Bean
	public Cloudinary cloudinary() {
		Map<String, String> config = new HashMap<>();
		config.put("cloud_name", cloudinaryName);
		config.put("api_key", cloudinaryApiKey);
		config.put("api_secret", cloudinarySecret);
		System.out.println(cloudinaryApiKey);
		return new Cloudinary(config);
	}
}
