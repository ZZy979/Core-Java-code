package retire;

/**
 * The information required to compute retirement income data.
 */
public class RetireInfo {
    private double savings;
    private double contrib;
    private double income;
    private int currentAge;
    private int retireAge;
    private int deathAge;
    private double inflationPercent;
    private double investPercent;
    private int age;
    private double balance;

    /**
     * Gets the available balance for a given year.
     * @param year the year for which to compute the balance
     * @return the amount of money available (or required) in that year
     */
    public double getBalance(int year) {
        if (year < currentAge)
            return 0;
        else if (year == currentAge) {
            age = year;
            balance = savings;
            return balance;
        }
        else if (year == age)
            return balance;

        if (year != age + 1)
            getBalance(year - 1);
        age = year;
        if (age < retireAge)
            balance += contrib;
        else
            balance -= income;
        balance *= 1 + (investPercent - inflationPercent);
        return balance;
    }

    /**
     * Gets the amount of prior savings.
     * @return the savings amount
     */
    public double getSavings() {
        return savings;
    }

    /**
     * Sets the amount of prior savings.
     * @param newValue the savings amount
     */
    public void setSavings(double newValue) {
        savings = newValue;
    }

    /**
     * Gets the annual contribution to the retirement account.
     * @return the contribution amount
     */
    public double getContrib() {
        return contrib;
    }

    /**
     * Sets the annual contribution to the retirement account.
     * @param newValue the contribution amount
     */
    public void setContrib(double newValue) {
        contrib = newValue;
    }

    /**
     * Gets the annual income.
     * @return the income amount
     */
    public double getIncome() {
        return income;
    }

    /**
     * Sets the annual income.
     * @param newValue the income amount
     */
    public void setIncome(double newValue) {
        income = newValue;
    }

    /**
     * Gets the current age.
     * @return the age
     */
    public int getCurrentAge() {
        return currentAge;
    }

    /**
     * Sets the current age.
     * @param newValue the age
     */
    public void setCurrentAge(int newValue) {
        currentAge = newValue;
    }

    /**
     * Gets the desired retirement age.
     * @return the age
     */
    public int getRetireAge() {
        return retireAge;
    }

    /**
     * Sets the desired retirement age.
     * @param newValue the age
     */
    public void setRetireAge(int newValue) {
        retireAge = newValue;
    }

    /**
     * Gets the expected age of death.
     * @return the age
     */
    public int getDeathAge() {
        return deathAge;
    }

    /**
     * Sets the expected age of death.
     * @param newValue the age
     */
    public void setDeathAge(int newValue) {
        deathAge = newValue;
    }

    /**
     * Gets the estimated percentage of inflation.
     * @return the percentage
     */
    public double getInflationPercent() {
        return inflationPercent;
    }

    /**
     * Sets the estimated percentage of inflation.
     * @param newValue the percentage
     */
    public void setInflationPercent(double newValue) {
        inflationPercent = newValue;
    }

    /**
     * Gets the estimated yield of the investment.
     * @return the percentage
     */
    public double getInvestPercent() {
        return investPercent;
    }

    /**
     * Sets the estimated yield of the investment.
     * @param newValue the percentage
     */
    public void setInvestPercent(double newValue) {
        investPercent = newValue;
    }
}
