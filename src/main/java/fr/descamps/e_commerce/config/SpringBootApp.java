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

        Product product = productRepository.save(
                Product.builder()
                        .name("Citron")
                        .type(ProductType.FRUITS)
                        .reference("cit_01")
                        .price(1.0)
                        .paymentTerms(ProductPaymentTerms.PER_KILO)
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
                        .product(product)
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
