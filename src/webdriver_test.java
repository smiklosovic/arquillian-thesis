@RunWith(Arquillian.class) @RunAsClient
public class WebDriverTest {
  @Deployment(name ="jboss")
  @TargetsContainer("jbossas")
  public static WebArchive createDeployment() {
    // By ShrinkWrap, assembly web app into WAR
  }

  @Drone WebDriver driver;
  @ArquillianResource URL deploymentUrl;

  @Test @OperatesOnDeployment("jboss")
  public void test01() {
    driver.get(deploymentUrl); //tests
  }
}
