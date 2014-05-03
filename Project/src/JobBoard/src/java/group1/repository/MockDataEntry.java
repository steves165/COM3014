package group1.repository;

import group1.bean.Company;
import group1.bean.Job;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

/**
 * Class to insert the required mock data into the repositories.
 * @author dan
 */
@Component
public class MockDataEntry implements ServletContextAware {

    /**
     * Static method to insert the example jobs into the database.
     * @param jobRepository Repository to add the jobs to.
     * @param companyRepo Repository of the companies to associate the jobs with.
     */
    public static void insertJobs(IJobRepository jobRepository, ICompanyRepo companyRepo) {
        Job job1 = new Job();
        job1.setId(1);
        job1.setCompanyId(1);
        job1.setDepartment("IT");
        job1.setLocation("London");
        job1.setSalary(100000);
        job1.setDescription("IT Job");
        job1.setTitle("Programmer");
        job1.addRequiredSkill("Java");
        job1.addRequiredSkill("Java");
        job1.setCreateUser("com3014");
        job1.setCompanyRepo(companyRepo);

        Job job2 = new Job();
        job2.setId(2);
        job2.setCompanyId(1);
        job2.setDepartment("IT");
        job2.setLocation("Guildford");
        job2.setSalary(10000);
        job2.setDescription("Senior | Lead Technical Consultant | Technical Architect (Java, J2EE, Web, CMS, WCM, CEM, ECM, Mobile)\n" +
"\n" +
"Senior level, technically hands on & experienced client facing Technical Consultant | Architect required by one of the leading software brands on the planet.\n" +
"\n" +
"With an exceptional technology stack & in demand, modern, cutting edge software solution this area of the business is growing at a rapid pace. So much so, that your bonus is guaranteed due to client demand already this year!\n" +
"\n" +
"Mature, technically passionate & adult environment lead by a world class management team that remains hands on & fully involved make this an excellent working environment.\n" +
"\n" +
"Not one to be missed.");
        job2.setTitle("Technical Lead Consultant / Developer ");
        job2.addRequiredSkill("blancmange");
        job2.addDesiredSkill("Java");
        job2.addDesiredSkill("C++");
        job2.setCompanyRepo(companyRepo);

        jobRepository.saveJob(job2);
        jobRepository.saveJob(job1);

        Job accentureJavaDev = new Job("Java Developer", 2,
                "Technology Solutions",
                "Java Technology Platform professionals develop applications using Java technology, a bundle of related technologies from Oracle that allow for developing and running operating system-independent programs written in Java programming language.\n\n" +
                "Key features of the role:\n" +
                "JEE professionals develop applications using JEE (Java Platform, Enterprise Edition) that are based largely on modular software components running on an application server.\n\n" +
                "Key responsibilities may include:\n" +
                "- Developing and Integrating application components using Java to meet business requirements\n" +
                "- Understanding and experience of object oriented analysis and design\n" +
                "- Designing, coding and testing of Java Applications that meet design specifications\n" +
                "- Good understanding of overall delivery lifecycle and supporting methodologies and processes (RUP, Agile, Waterfall)\n" +
                "- Creation, execution and documentation of tests necessary to ensure that an application or technical environment meets performance requirements (technical, functional and user interface)\n" +
                "- Identifying and maintaining configuration changes to applications so that they meet business process requirements\n" +
                "- Implementing all actions required to deploy an application; ensuring that the application meets both technical and business needs\n" +
                "- Maintain, repair and tune Java applications in order to keep them performing according to technical and functional specifications; directing user support activities; managing preventive maintenance activities",
                new String[] {
                    "Java", "REST", "SOAP", "SQL"
                },
                new String[] {
                    "HTML", "XHTML", "CSS", "JavaScript"
                },
                "Manchester",
                30790,
                "Permanent");

        accentureJavaDev.setCreateUser("com3014");
        jobRepository.saveJob(accentureJavaDev);
    }

    /**
     * Insert example companies into the company list.
     * @param companyRepository Repository to ad the companies to.
     */
    public void insertCompanies(ICompanyRepo companyRepository) {
        Company comp = new Company();
        comp.setId(1);
        comp.setCompanyName("Google");
        comp.setIndustry("IT");
        comp.setScale("Large");
        comp.setWebsite("www.google.com");
        comp.setContactEmail("someone@gmail.com");
        comp.setContactTel("+1 650-253-0000");
        comp.setContactAddress("1600 Amphitheatre Parkway\n" + "Mountain View, CA 94043, USA");
        comp.addLocation("201 S. Division St.\n" + "Suite 500\n" + "Ann Arbor, MI 48104, USA");
        comp.addLocation("Millennium at Midtown\n" + "10 10th Street NE\n" + "Suite 600\n" + "Atlanta, GA 30309, USA");
        comp.addLocation("9606 North MoPac Expressway\n" + "Suite 700\n" + "Austin, TX 78759, USA");
        comp.setCreateUser("com3014");
        loadImage("/static/google.jpg", comp);
        companyRepository.saveComp(comp);

        Company accenture = new Company();
        accenture.setId(2);
        accenture.setCompanyName("Accenture");
        accenture.setIndustry("IT");
        accenture.setScale("Large");
        accenture.setWebsite("www.accenture.co.uk");
        accenture.setContactEmail("tech@accenture.co.uk");
        accenture.setContactTel("0808 1011169");
        accenture.setContactAddress("London (Fenchurch Street)\n" +
        "1 Plantation Place\n" +
        "30 Fenchurch Street\n" +
        "London EC3M 3BD\n" +
        "Tel.: +44 (0) 20 7844 4000");
        loadImage("/static/accenture.jpg", accenture);
        accenture.setCreateUser("com3014");
        companyRepository.saveComp(accenture);
        
        Company BP = new Company();
        BP.setId(3);
        BP.setCompanyName("BP");
        BP.setIndustry("Oil");
        BP.setScale("Large");
        BP.setWebsite("www.bp.com");
        BP.setContactEmail("info@bp.co.uk");
        BP.setContactTel("0808 1456546");
        BP.setContactAddress("London \n" +
        "1 St James's Square\n" +
        
        "London SW1Y 4PD\n" +
        "Tel.: +44 20 7496 4000");
        loadImage("/static/BP.png", BP);
        BP.setCreateUser("com3014");
        companyRepository.saveComp(BP);
        
        
        Company ibm = new Company();
        ibm.setId(3);
        ibm.setCompanyName("IBM");
        ibm.setIndustry("IT");
        ibm.setScale("Large");
        ibm.setWebsite("www.ibm.com");
        ibm.setContactEmail("info@ibm.co.uk");
        ibm.setContactTel("0808 1423548");
        ibm.setContactAddress("London \n" +
        "76/78 Upper Ground, South Bank\n" +
        
        "London SE1 9PZ\n" +
        "Tel.: +44 020 7202 3000");
        loadImage("/static/IBM.png", ibm);
        ibm.setCreateUser("com3014");
        companyRepository.saveComp(ibm);
    }

    /**
     * Load an image from the disk so we can provide logos for the example jobs.
     * @param filename Relative filename to read in.
     * @param comp Company to add the image to.
     */
    private void loadImage(String filename, Company comp) {
        FileInputStream logoFile = null;
        try {
            String logo = context.getRealPath(filename);
            File file = new File(logo);
            logoFile = new FileInputStream(file);
            byte[] img = new byte[(int)file.length()];
            if (logoFile.read(img) == img.length) {
                comp.setImage(img);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MockDataEntry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MockDataEntry.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            if (logoFile != null) {
                try {
                    logoFile.close();
                } catch (IOException ex) {
                    Logger.getLogger(MockDataEntry.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Context used by the loadImage method to get the real path of a relative
     * path.
     */
    private ServletContext context;

    /**
     * Override method to allow spring to insert the servlet context.
     * @param sc Current servlet context.
     */
    @Override
    public void setServletContext(ServletContext sc) {
        this.context = sc;
    }
}
