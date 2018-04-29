package pizzaShop.entity.embedded;

import pizzaShop.utilities.CurrencyConverterJPA;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Embeddable
public class MonetaryAmount {

    @Column(name = "PRICE")
    @NotNull(message = "Назначьте цену")
    @DecimalMin(value = "0.1", message = "Назначьте цену")
    private BigDecimal amount = new BigDecimal(0d);

    @Column(name = "CURRENCY")
    @Convert(converter = CurrencyConverterJPA.class)
    private String currency = String.valueOf(Currency.ROUBLE);

    protected MonetaryAmount() {
    }

    public MonetaryAmount(double value) {
        this.amount = new BigDecimal(value);
    }

    enum Currency {
        ROUBLE() {
            @Override
            public String toString() {
                return "&#8381";
            }
        },
        DOLLAR() {
            @Override
            public String toString() {
                return "$";
            }
        }
    }

    public MonetaryAmount plus(MonetaryAmount other) {
        return new MonetaryAmount(this.amount.add(other.getAmount()).doubleValue());
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal price) {
        this.amount = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonetaryAmount other = (MonetaryAmount) o;

        if (!amount.equals(other.amount)) return false;
        return currency == other.currency;

    }

    @Override
    public int hashCode() {
        int result = 0;
        if (amount != null) result = amount.intValue();
        result = 31 * result + currency.hashCode();
        return result;
    }

    @Override
    public String toString() {
        if(amount == null)return currency;

        if (amount.doubleValue() == amount.longValue() || amount.doubleValue() == 0d)
            return String.format("%d %s", amount.longValue(), currency);
        else
            return String.format("%.2f %s", amount.doubleValue(), currency);
    }

}
