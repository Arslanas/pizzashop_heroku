package pizzaShop.utilities;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class CharacterEncodingFilterExtended extends CharacterEncodingFilter {
}
