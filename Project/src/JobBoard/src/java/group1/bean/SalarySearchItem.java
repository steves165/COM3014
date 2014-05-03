package group1.bean;

/**
 * Class to hold a single salary search criteria.
 *
 * @author Daniel Searle <ds00148@surrey.ac.uk>
 */
public class SalarySearchItem {
   /**
    * Boolean to define if this salary is the upper bound
    */
   private boolean upper;

   /**
    * Integer to define the salary of the search term
    */
   private int salary;

   /**
    * Constructor for the salary search terms
    * @param salary Integer salary search figure to use.
    * @param isUpperLimit Set to true if this salary is the upper limit.
    * False means the salary is the lower limit.
    */
   public SalarySearchItem(int salary, boolean isUpperLimit) {
       this.salary = salary;
       this.upper = isUpperLimit;
   }
   /**
    * Constructor for the salary search term, defaulting as the lower limit.
    * @param salary Integer salary search figure to use.
    */
   public SalarySearchItem(int salary) {
       this(salary, false);
   }

   /**
    * Get the salary search figure.
    * @return Integer of the min/max salary on the job listing
    */
   public int getSalary() {
       return salary;
   }

   /**
    * Accessor for checking the search type
    * @return true if this entity represents the upper salary limit,
    * false if the entity represents the lower limit.
    */
   public boolean isUpperLimit() {
       return upper;
   }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.upper ? 1 : 0);
        hash = 53 * hash + this.salary;
        return hash;
    }

    @Override
    @SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SalarySearchItem other = (SalarySearchItem) obj;
        if (this.upper != other.upper) {
            return false;
        }
        if (this.salary != other.salary) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SalarySearchItem{" + "upper=" + upper + ", salary=" + salary + '}';
    }

}
