package fr.descamps.e_commerce.config;

import fr.descamps.e_commerce.domain.*;
import fr.descamps.e_commerce.repository.*;
import fr.descamps.e_commerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootApp {
    private final IProductRepository productRepository;
    private final ICartRepository cartRepository;
    private final IProductCartRepository productCartRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        Role role = roleRepository.save(
                Role.builder()
                        .name("USER")
                        .build()
        );


        User user = userRepository.save(
                User.builder()
                        .username("Davy")
                        .password(passwordEncoder.encode("password"))
                        .role(role)
                        .build()
        );

        Product citron = productRepository.save(
                Product.builder()
                        .image("https://static.wixstatic.com/media/7885d7_3603545602ba4408b3491dd02b1f2ceb~mv2.png/v1/fill/w_800,h_800,al_c,q_90,enc_avif,quality_auto/7885d7_3603545602ba4408b3491dd02b1f2ceb~mv2.png")
                        .name("Citrons")
                        .type(ProductType.FRUITS)
                        .reference("cit_01")
                        .price(0.99)
                        .paymentTerms(ProductPaymentTerms.UNIT_PRICE)
                        .build()
        );
        Product citronVert = productRepository.save(
                Product.builder()
                        .image("https://www.miam-asso.fr/wp-content/uploads/2017/05/Citron-vert-800x533.jpg")
                        .name("Citrons vert")
                        .type(ProductType.FRUITS)
                        .reference("cit_02")
                        .price(1.99)
                        .paymentTerms(ProductPaymentTerms.UNIT_PRICE)
                        .build()
        );
        Product fraises = productRepository.save(
                Product.builder()
                        .image("https://www.jaimefruitsetlegumes.ca/wp-content/uploads/2019/09/Fraise-700x700.png")
                        .name("Fraises")
                        .type(ProductType.FRUITS)
                        .reference("fra_01")
                        .price(2.99)
                        .paymentTerms(ProductPaymentTerms.PER_KILO)
                        .build()
        );
        Product coconut = productRepository.save(
                Product.builder()
                        .image("https://www.conservation-nature.fr/wp-content/uploads/visuel/fruit/shutterstock_1247015254.jpg")
                        .name("Noix de coco")
                        .type(ProductType.FRUITS)
                        .reference("ndc_01")
                        .price(3.99)
                        .paymentTerms(ProductPaymentTerms.UNIT_PRICE)
                        .build()
        );

        Cart cart = cartRepository.save(
                Cart.builder()
                        .status(CartStatus.PENDING)
                        .user(user)
                        .build()
        );

        ProductCart productCart = productCartRepository.save(
                ProductCart.builder()
                        .cart(cart)
                        .product(citron)
                        .quantity(3.0)
                        .build()
        );

        return args -> {
            RequestMappingHandlerMapping mapping = ctx.getBean(RequestMappingHandlerMapping.class);
            mapping.getHandlerMethods().forEach((key, value) -> System.out.println(key));
            System.out.println("CommandLineRunner exécuté !");
        };
    }
}
